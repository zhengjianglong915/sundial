package cn.wegostack.sundial.common.model;

import cn.wegostack.sundial.common.enums.TriggerType;
import lombok.Data;

import java.util.Date;

/**
 * Meta of job
 *
 * @author zhengjianglong
 * @since 2021-06-22
 */
@Data
public class JobMeta {
    private long id;

    private String jobId;

    private String name;

    private String appName;

    private String type;

    private TriggerType triggerType;

    private String handler;

    private String workerGroup;

    /**
     * time, cron or period
     */
    private String triggerExp;

    private String version;

    /**
     * Namespace info
     */
    private String namespace;

    private String workspaceId;

    /**
     * Time
     */
    private Date createTime;

    private Date modifyTime;
}
