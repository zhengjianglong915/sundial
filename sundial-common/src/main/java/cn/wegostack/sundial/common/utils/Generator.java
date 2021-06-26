package cn.wegostack.sundial.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Date;

/**
 * @author zhengjianglong
 * @since 2021-06-25
 */
public class Generator {

    /**
     * Generate job id
     *
     * @return
     */
    public static String genJobId() {
        // todo
        String random = RandomStringUtils.random(8);
        return "JB-" + random;
    }

    /**
     * Build trigger id
     *
     * @return
     */
    public static String genTriggerId(String jobId) {
        return String.format("T-%s-%s", jobId, DateUtils.format(new Date()));
    }
}
