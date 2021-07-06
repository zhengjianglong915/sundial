package cn.wegostack.sundial.registry.client.api;



import cn.wegostack.sundial.registry.client.model.DataInfo;

import java.util.List;

/**
 * The interface of listener
 *
 * @author zhengjianglong
 * @since 2021-06-26
 */
public interface SubscribeDataListener {

    /**
     * Handle
     *
     * @param dataId
     * @param publishers
     */
    void notify(String dataId, List<DataInfo> publishers);
}
