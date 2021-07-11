package cn.wegostack.sundial.scheduler.core.cluster;

/**
 * @author zhengjianglong
 * @since 2021-07-10
 */
public interface Cluster {
    /**
     * join into cluster
     *
     * @param cluster
     */
    void join(String cluster);

    /**
     * exit form cluster
     * 
     * @param cluster
     */
    void exit(String cluster);
}
