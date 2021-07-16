package cn.wegostack.sundial.scheduler.core.scheduler.dispatcher;


import cn.wegostack.sundial.common.enums.JobType;
import cn.wegostack.sundial.common.model.ScheduleContext;

import java.util.Set;

/**
 * @author zhengjianglong
 * @since 2021-06-25
 */
public interface IDispatcher extends Runnable {

    Set<JobType> type();

    void action(ScheduleContext context);

}
