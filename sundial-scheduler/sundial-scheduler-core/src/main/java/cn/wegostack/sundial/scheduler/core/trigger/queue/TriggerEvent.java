package cn.wegostack.sundial.scheduler.core.trigger.queue;

import cn.wegostack.sundial.common.model.JobTrigger;
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

    private JobTrigger jobTrigger;

}
