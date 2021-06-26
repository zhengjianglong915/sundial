package cn.wegostack.sundial.scheduler.core.trigger.queue;

import cn.wegostack.sundial.common.model.JobItem;
import lombok.Data;

import java.util.Date;

/**
 * @author zhengjianglong
 * @since 2021-06-25
 */
@Data
public class TriggerEvent {
    private Date expTriggerTime;

    private Date triggerTime;

    private String triggerId;

    private JobItem jobItem;

}
