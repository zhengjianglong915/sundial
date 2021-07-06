package cn.wegostack.sundial.scheduler.core.discovery;

import cn.wegostack.sundial.discovery.registry.model.Instance;
import cn.wegostack.sundial.discovery.registry.session.RegistrySession;
import cn.wegostack.sundial.registry.client.api.SubscribeDataListener;
import cn.wegostack.sundial.registry.client.model.DataInfo;
import cn.wegostack.sundial.registry.client.model.Publisher;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhengjianglong
 * @since 2021-07-04
 */
@Service
public class SundialDiscovery implements IDiscovery, SubscribeDataListener {

    @Autowired
    private RegistrySession registrySession;

    private Map<String, List<Publisher>> publisherMap = Maps.newConcurrentMap();

    @Override
    public void notify(String dataId, List<DataInfo> dataInfos) {
        List<Publisher> publisherList = Lists.newArrayList();
        for (DataInfo publisher : dataInfos) {
            publisherList.add(publisher.getPublisher());
        }

        publisherMap.put(dataId, publisherList);
    }

    @Override
    public List<Instance> discovery(String appName) {
        List<Instance> instanceList = registrySession.queryPublisher(appName);
        if (CollectionUtils.isEmpty(instanceList)) {
            return Lists.newArrayList();
        }
        return instanceList;
    }
}
