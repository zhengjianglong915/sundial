package cn.wegostack.sundial.scheduler.core.trigger;


import cn.wegostack.sundial.common.model.JobTrigger;

/**
 * @author zhengjianglong
 * @since 2021-06-22
 */
public interface ITriggerManager {

    boolean add(JobTrigger job);

    boolean remove(JobTrigger job);

    boolean refresh(JobTrigger job);
}
