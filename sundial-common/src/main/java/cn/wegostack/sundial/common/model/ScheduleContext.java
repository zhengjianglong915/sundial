package cn.wegostack.sundial.common.model;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @author zhengjianglong
 * @since 2021-06-24
 */
@Data
public class ScheduleContext {
    //
    private String triggerId;

    /**
     * time
     */
    private Date expTriggerTime;

    private Date triggerTime;

    private Date schedulerTime;

    /**
     * Job meta
     */
    private JobItem jobItem;

    /**
     * Set biz tags
     */
    private Map<String, Object> tags = Maps.newConcurrentMap();
}
