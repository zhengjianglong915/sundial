package cn.wegostack.sundial.registry.client.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zhengjianglong
 * @since 2021-06-27
 */
@Data
public class Register implements Serializable {
    private String appName;

    private String ip;
}
