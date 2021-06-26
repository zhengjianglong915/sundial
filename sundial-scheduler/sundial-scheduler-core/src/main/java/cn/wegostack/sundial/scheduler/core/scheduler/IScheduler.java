package cn.wegostack.sundial.scheduler.core.scheduler;


import cn.wegostack.sundial.common.model.ScheduleContext;

/**
 * @author zhengjianglong
 * @since 2021-06-24
 */
public interface IScheduler {

    /**
     * Scheduler job
     * @param context
     */
    void schedule(ScheduleContext context);

}
