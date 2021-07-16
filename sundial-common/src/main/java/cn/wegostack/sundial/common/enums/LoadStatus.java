package cn.wegostack.sundial.common.enums;

import org.apache.commons.lang3.StringUtils;

public enum LoadStatus {
    INIT,

    LOAD;

    /**
     * convect
     *
     * @param name
     * @return
     */
    public static LoadStatus of(String name) {
        if (StringUtils.isEmpty(name)) {
            return null;
        }

        for (LoadStatus status : values()) {
            if (status.name().equals(name)) {
                return status;
            }
        }
        return null;
    }
}
