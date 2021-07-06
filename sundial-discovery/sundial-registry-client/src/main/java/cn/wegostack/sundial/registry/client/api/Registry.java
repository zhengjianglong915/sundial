package cn.wegostack.sundial.registry.client.api;


/**
 * The interface of Registry client.
 *
 * @author zhengjianglong
 * @since 2021-06-26
 */
public interface Registry {
    /**
     * Publish data
     *
     * @return
     */
    boolean publish(String dataId);

    /**
     * UnPublish data
     *
     * @param dataId
     * @return
     */
    boolean unPublish(String dataId);

    /**
     * Subscribe data
     *
     * @return
     */
    boolean subscribe(String dataId, SubscribeDataListener subscribeDataListener);

    /**
     * UnSubscribe data
     *
     * @param dataId
     * @return
     */
    boolean unSubscribe(String dataId);
}
