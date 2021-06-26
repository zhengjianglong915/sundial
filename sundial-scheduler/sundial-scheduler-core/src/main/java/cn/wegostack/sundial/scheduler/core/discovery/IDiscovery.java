package cn.wegostack.sundial.scheduler.core.discovery;


import java.util.List;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
public interface IDiscovery {

    <T> List<Publisher<T>> discovery(String namespace, String workspaceId, String dataId);

}
