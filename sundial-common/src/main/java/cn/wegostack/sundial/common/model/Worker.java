package cn.wegostack.sundial.common.model;

import lombok.Data;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
@Data
public class Worker {
    // host name
    private String hostName;

    private String originIp;

    private String remoteIp;

    private String region;

    private String group;

}
