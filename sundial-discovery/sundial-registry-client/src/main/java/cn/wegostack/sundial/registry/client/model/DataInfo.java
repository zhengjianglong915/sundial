package cn.wegostack.sundial.registry.client.model;

import lombok.Data;

/**
 * @author zhengjianglong
 * @since 2021-06-27
 */
@Data
public class DataInfo {
    private String registerId;

    private String dataId;

    private String data;

    private Long timestamp;

    private Publisher publisher;
}
