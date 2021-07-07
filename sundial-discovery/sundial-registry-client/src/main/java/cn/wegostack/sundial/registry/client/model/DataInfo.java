package cn.wegostack.sundial.registry.client.model;

import lombok.Data;

import java.util.List;

/**
 * @author zhengjianglong
 * @since 2021-06-27
 */
@Data
public class DataInfo {
    private String dataId;

    private List<Publisher> publishers;
}
