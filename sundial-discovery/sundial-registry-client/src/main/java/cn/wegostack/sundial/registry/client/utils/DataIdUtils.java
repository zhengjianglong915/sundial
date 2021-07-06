package cn.wegostack.sundial.registry.client.utils;

/**
 * @author zhengjianglong
 * @since 2021-06-27
 */
public class DataIdUtils {
    private static String SPLIT = "#@#";

    public static String genDataId(String... keys) {
        String dataId = keys[0];
        for (int i = 1; i < keys.length; i++) {
            dataId += SPLIT + keys[i];
        }

        return dataId;
    }
}
