package cn.wegostack.sundial.scheduler.core.discovery;


import cn.wegostack.sundial.registry.client.model.Publisher;

import java.util.List;

/**
 * @author zhengjianglong
 * @since 2021-07-04
 */
public interface IDiscovery {

    List<Publisher> discovery(String dataId);
}
