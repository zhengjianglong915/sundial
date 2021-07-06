package cn.wegostack.sundial.discovery.registry.store;

import cn.wegostack.sundial.discovery.registry.model.Instance;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.collections.MapUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author zhengjianglong
 * @since 2021-06-29
 */
public class RegistryStore {

    private final static Map<String, Map<String, Instance>> publishersCache = Maps.newConcurrentMap();

    private final static int EXPIRED_TIME = 90;

    private static volatile boolean inited = false;

    /**
     * init
     */
    public static synchronized void init() {
        if (inited) {
            return;
        }

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new ThreadFactoryBuilder().setNameFormat("RegistryStore-").setDaemon(true).build());
        executorService.submit(() -> {
            clearExpiredInstance();
        });
        inited = true;
    }


    public static synchronized void registryPublisher(String dataId, Instance instance) {
        Map<String, Instance> instanceMap = publishersCache.get(dataId);
        if (null == instanceMap) {
            instanceMap = Maps.newConcurrentMap();
            publishersCache.putIfAbsent(dataId, instanceMap);
        }

        instanceMap.put(instance.getIp(), instance);
    }

    public static List<Instance> getPublishers(String dataId) {
        Map<String, Instance> instanceMap = publishersCache.get(dataId);
        if (MapUtils.isEmpty(instanceMap)) {
            return Lists.newArrayList();
        }

        List<Instance> instances = Lists.newArrayList();
        instanceMap.forEach((key, val) -> {
            instances.add(val);
        });
        return instances;
    }

    public static void clearExpiredInstance() {
        long currentTime = System.currentTimeMillis();
        Iterator<Map.Entry<String, Map<String, Instance>>> iterator = publishersCache.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Map<String, Instance>> entry = iterator.next();
            String dataId = entry.getKey();
            Map<String, Instance> valMap = entry.getValue();
            Iterator<Map.Entry<String, Instance>> iter = valMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, Instance> next = iter.next();
                String nodeId = next.getKey();
                Instance instance = next.getValue();
                Long heartbeat = instance.getHeartbeat();
                if (isExpired(currentTime, heartbeat)) {
                    valMap.remove(nodeId);
                }
            }

            if (MapUtils.isEmpty(valMap)) {
                // 清理
                publishersCache.remove(dataId);
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
