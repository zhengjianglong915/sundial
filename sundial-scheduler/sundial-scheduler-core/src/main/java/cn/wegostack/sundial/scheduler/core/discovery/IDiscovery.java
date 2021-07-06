package cn.wegostack.sundial.scheduler.core.discovery;


import cn.wegostack.sundial.discovery.registry.model.Instance;

import java.util.List;

/**
 * @author zhengjianglong
 * @since 2021-07-04
 */
public interface IDiscovery {

    List<Instance> discovery(String appName);
}
