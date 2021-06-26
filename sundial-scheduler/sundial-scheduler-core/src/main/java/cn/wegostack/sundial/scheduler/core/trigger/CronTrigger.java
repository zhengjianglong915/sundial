package cn.wegostack.sundial.scheduler.core.trigger;


import cn.wegostack.sundial.common.model.JobItem;

/**
 * @author zhengjianglong
 * @since 2021-06-22
 */
public class CronTrigger implements ITrigger {

    @Override
    public boolean add(JobItem job) {
        return false;
    }

    @Override
    public boolean remove(JobItem job) {
        return false;
    }

    @Override
    public boolean refresh(JobItem job) {
        return false;
    }
}
