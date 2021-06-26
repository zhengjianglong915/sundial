package cn.wegostack.sundial.scheduler.core.invoker;


import cn.wegostack.sundial.common.model.ScheduleContext;
import cn.wegostack.sundial.common.model.Worker;

import java.util.List;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
public interface IInvoker {

    boolean invoke(List<Worker> workers, ScheduleContext context);
}
