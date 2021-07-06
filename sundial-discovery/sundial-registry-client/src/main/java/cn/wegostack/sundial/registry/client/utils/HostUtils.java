package cn.wegostack.sundial.registry.client.utils;

import java.net.InetAddress;

/**
 * @author zhengjianglong
 * @since 2021-06-28
 */
public class HostUtils {

    /**
     * Get local ip
     *
     * @return
     */
    public static String getHostIp() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Get hostname
     * @return
     */
    public static String getHostname() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostName();
        } catch (Exception e) {
            return null;
        }
    }
}
