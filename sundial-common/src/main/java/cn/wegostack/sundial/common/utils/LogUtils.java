package cn.wegostack.sundial.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhengjianglong
 * @since 2021-06-24
 */
public class LogUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(LogUtils.class);

    public static void info(String msg, Object... objects) {
        System.out.println(String.format(msg, objects));
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(msg, objects);
        }
    }

    public static void error(String msg, Object... objects) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(msg, objects);
        }
    }

    public static void error(String msg, Throwable t) {
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error(msg, t);
        }
    }
}
