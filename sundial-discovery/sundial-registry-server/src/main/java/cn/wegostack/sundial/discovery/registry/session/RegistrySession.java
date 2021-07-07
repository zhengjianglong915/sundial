package cn.wegostack.sundial.discovery.registry.session;


import cn.wegostack.sundial.discovery.registry.model.Instance;
import cn.wegostack.sundial.discovery.registry.model.RegistryClient;
import cn.wegostack.sundial.discovery.registry.store.DBRegistryStore;
import cn.wegostack.sundial.discovery.registry.store.RegistryStore;
import cn.wegostack.sundial.registry.client.ReplyStatus;
import cn.wegostack.sundial.registry.client.enums.ReplyType;
import cn.wegostack.sundial.registry.client.model.DataInfo;
import cn.wegostack.sundial.registry.client.model.Publisher;
import cn.wegostack.sundial.registry.client.proto.SundialRegistryProto.*;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.grpc.stub.StreamObserver;
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

        dbRegistryStore.registerPublisher(dataId, instance);

        // subscribe
        notify(dataId);
    }

    public void notify(String dataId) {
        List<RegistryClient> registryClients = subscriberRegistrationMap.get(dataId);
        if (CollectionUtils.isEmpty(registryClients)) {
            return;
        }

        RegistryReply reply = getNotifyReply(dataId);
        if (null == reply) {
            return;
        }

        for (RegistryClient registryClient : registryClients) {
            StreamObserver observer = registryClient.getObserver();
            observer.onNext(reply);
        }
    }

    public RegistryReply getNotifyReply(String dataId) {
        List<Instance> instanceList = dbRegistryStore.queryPublisher(dataId);
        if (CollectionUtils.isEmpty(instanceList)) {
            return null;
        }

        DataInfo dataInfo = new DataInfo();
        dataInfo.setDataId(dataId);
        List<Publisher> publisherList = Lists.newArrayList();
        for (Instance instance : instanceList) {
            Publisher publisher = new Publisher();
            publisher.setAppName(instance.getAppName());
            publisher.setIp(instance.getIp());
            publisherList.add(publisher);
        }
        dataInfo.setPublishers(publisherList);

        return RegistryReply.newBuilder()
                .setStatus(ReplyStatus.OK.name())
                .setType(ReplyType.NOTIFY.name())
                .setContent(JSON.toJSONString(dataInfo))
                .build();
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

    private static boolean isExpired(Long currentTime, Long heartbeat) {
        long diff = currentTime - heartbeat;
        long seconds = diff / 1000;
        if (seconds >= EXPIRED_TIME) {
            return true;
        }
        return false;
    }
}
