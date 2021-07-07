package cn.wegostack.sundial.scheduler.core.discovery;

import cn.wegostack.sundial.registry.client.api.SubscribeDataListener;
import cn.wegostack.sundial.registry.client.model.Publisher;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * @author zhengjianglong
 * @since 2021-07-04
 */
public class SundialDiscovery implements IDiscovery, SubscribeDataListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(SundialDiscovery.class);

    /**
     * dataId -> publishers
     */
    private Map<String, List<Publisher>> publisherMap = Maps.newConcurrentMap();

    @Override
    public void notify(String dataId, List<Publisher> publishers) {
        LOGGER.info("received data, dataId={}, publishers={}", dataId, JSON.toJSON(publishers));
        publisherMap.put(dataId, publishers);
    }

    @Override
    public List<Publisher> discovery(String dataId) {
        return publisherMap.get(dataId);
    }
}
