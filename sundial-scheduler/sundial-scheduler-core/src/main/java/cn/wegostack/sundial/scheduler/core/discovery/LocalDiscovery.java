package cn.wegostack.sundial.scheduler.core.discovery;

import cn.wegostack.sundial.common.model.Worker;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
public class LocalDiscovery implements IDiscovery {

    @Override
    public <T> List<Publisher<T>> discovery(String namespace, String workspaceId, String dataId) {
        List<Publisher<T>> publishers = Lists.newArrayList();
        Publisher<T> publisher = new Publisher();
        publisher.setDataId(dataId);

        Worker worker = new Worker();
        worker.setOriginIp("127.0.0.1");
        worker.setRemoteIp("127.0.0.1");

        T val = (T) worker;
        publisher.setValue(val);
        publishers.add(publisher);
        return publishers;
    }
}
