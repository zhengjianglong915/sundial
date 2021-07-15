package cn.wegostack.sundial.common.utils;

/**
 * @author zhengjianglong
 * @since 2021-07-11
 */
public class LocalServer {
    private static String ip;
    private static String cluster;

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        LocalServer.ip = ip;
    }

    public static String getCluster() {
        return cluster;
    }

    public static void setCluster(String cluster) {
        LocalServer.cluster = cluster;
    }
}
