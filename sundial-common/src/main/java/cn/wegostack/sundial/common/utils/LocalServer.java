package cn.wegostack.sundial.common.utils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author zhengjianglong
 * @since 2021-07-11
 */
public class LocalServer {
    private static String ip;
    private static String cluster;

    private static AtomicBoolean isReady = new AtomicBoolean(false);

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

    public static boolean isReady() {
        return isReady.get();
    }

    public static void setIsReady(boolean isReady) {
        LocalServer.isReady.set(isReady);
    }
}
