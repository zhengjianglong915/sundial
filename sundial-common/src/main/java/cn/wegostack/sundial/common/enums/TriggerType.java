package cn.wegostack.sundial.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhengjianglong
 * @since 2021-06-22
 */
public enum TriggerType {
    CRON,

    PERIOD,

    TIMING,

    EVENT;

    /**
     * Get type
     *
     * @param name
     * @return
     */
    public static TriggerType of(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        for (TriggerType value : values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }

        return null;
    }
}
