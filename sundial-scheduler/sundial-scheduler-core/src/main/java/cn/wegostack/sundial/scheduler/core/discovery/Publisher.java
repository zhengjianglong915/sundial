package cn.wegostack.sundial.scheduler.core.discovery;

import com.sun.tools.javac.util.Assert;
import lombok.Data;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
@Data
public class Publisher<T> {
    private static String SPLIT = "#@#";
    private String dataId;

    private T value;

    public static String genDataId(String... keys) {
        // Assert.check(null == keys || keys.length == 0, "The keys should not be empty when generate dataId");
        String dataId = keys[0];
        for (int i = 1; i < keys.length; i++) {
            dataId += SPLIT + keys[i];
        }

        return dataId;
    }
}
