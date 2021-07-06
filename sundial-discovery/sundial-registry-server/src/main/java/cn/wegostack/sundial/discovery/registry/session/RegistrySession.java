package cn.wegostack.sundial.discovery.registry.session;


import cn.wegostack.sundial.discovery.registry.model.Instance;
import cn.wegostack.sundial.discovery.registry.model.RegistryClient;
import cn.wegostack.sundial.discovery.registry.store.DBRegistryStore;
import cn.wegostack.sundial.discovery.registry.store.RegistryStore;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author zhengjianglong
 * @since 2021-06-29
 */
@Service
public class RegistrySession {
    private final static int EXPIRED_TIME = 90;

    @Autowired
    private DBRegistryStore dbRegistryStore;

    /**
     * dataId -> subscribers
     */
    private static final Map<String, List<RegistryClient>> subscriberRegistrationMap = Maps.newConcurrentMap();

    static {
        RegistryStore.init();

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new ThreadFactoryBuilder().setNameFormat("RegistryStore-").setDaemon(true).build());
        executorService.submit(() -> {
            clearExpiredInstance();
        });
    }

    /**
     * registry subscribe
     *
     * @param dataId
     * @param subscriber
     */
    public void registrySubscriber(String dataId, RegistryClient subscriber) {
        List<RegistryClient> subscribers = subscriberRegistrationMap.get(dataId);
        List<RegistryClient> newSubscribers = null;
        if (null == subscribers) {
            newSubscribers = Lists.newArrayList();
            subscribers = subscriberRegistrationMap.putIfAbsent(dataId, newSubscribers);
        }

        if (null == subscribers) {
            subscribers = newSubscribers;
        }

        subscribers.add(subscriber);

        Instance instance = new Instance();
        instance.setAppName(subscriber.getAppName());
        instance.setIp(subscriber.getIp());
        instance.setHeartbeat(subscriber.getHeartbeat());
        dbRegistryStore.registerPublisher(dataId, instance);
    }


    /**
     * registry publisher
     *
     * @param dataId
     * @param publisher
     */
    public void registryPublisher(String dataId, RegistryClient publisher) {
        // registry
        Instance instance = new Instance();
        instance.setAppName(publisher.getAppName());
        instance.setIp(publisher.getIp());
        instance.setHeartbeat(publisher.getHeartbeat());
        instance.setHostname(publisher.getHostname());

        //  RegistryStore.registryPublisher(dataId, instance);
        dbRegistryStore.registerPublisher(dataId, instance);
    }

    /**
     * 根据应用粒度查询
     *
     * @param appName
     */
    public List<Instance> queryPublisher(String appName) {
        return dbRegistryStore.queryPublisher(appName);
    }

    public static void clearExpiredInstance() {
        long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<String, List<RegistryClient>>> iterator = subscriberRegistrationMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<RegistryClient>> entry = iterator.next();
            String dataId = entry.getKey();
            List<RegistryClient> clientList = entry.getValue();
            Iterator<RegistryClient> iter = clientList.iterator();
            while (iter.hasNext()) {
                RegistryClient instance = iter.next();
                Long heartbeat = instance.getHeartbeat();
                if (isExpired(currentTime, heartbeat)) {
                    clientList.remove(instance);
                }
            }

            if (CollectionUtils.isEmpty(clientList)) {
                subscriberRegistrationMap.remove(dataId);
            }
        }
    }

    private static boolean isExpired(long currentTime, Long heartbeat) {
        long diff = currentTime - heartbeat;
        long seconds = diff / 1000;
        if (seconds >= EXPIRED_TIME) {
            return true;
        }
        return false;
    }
}
