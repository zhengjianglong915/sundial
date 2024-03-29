package cn.wegostack.sundial.common.enums;

import org.apache.commons.lang3.StringUtils;

public enum TriggerStatus {

    OPEN,

    CLOSED;

    public static TriggerStatus of(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        for (TriggerStatus value : values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }

        return null;
    }
}
