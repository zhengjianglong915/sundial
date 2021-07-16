package cn.wegostack.sundial.common.enums;

import org.apache.commons.lang3.StringUtils;

public enum ServerStatus {
    RUNNING,
    TIMEOUT,
    DOWN;

    public static ServerStatus of(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        for (ServerStatus value : values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return null;
    }
}
