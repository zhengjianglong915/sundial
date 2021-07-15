package cn.wegostack.sundial.scheduler.model.request;

import lombok.Data;

/**
 * @author zhengjianglong
 * @since 2021-07-11
 */
@Data
public class AddJobRequest {
    private String name;

    private String appName;

    private String triggerType;

    private String triggerExp;
}
