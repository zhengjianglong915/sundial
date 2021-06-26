package cn.wegostack.sundial.scheduler.core.router;

import lombok.Data;

import java.util.Map;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
@Data
public class RouterContext {

    private String workerGroup;

    private String version;

    private Map<String, Object> tags;
}
