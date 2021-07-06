package cn.wegostack.sundial.scheduler.core.scheduler;

import cn.wegostack.sundial.common.model.ScheduleContext;
import cn.wegostack.sundial.common.utils.DateUtils;
import cn.wegostack.sundial.common.utils.LogUtils;
import cn.wegostack.sundial.scheduler.core.discovery.IDiscovery;
import cn.wegostack.sundial.scheduler.core.discovery.SundialDiscovery;
import cn.wegostack.sundial.scheduler.core.invoker.IInvoker;
import cn.wegostack.sundial.scheduler.core.invoker.RpcInvoker;
import cn.wegostack.sundial.scheduler.core.loadbalance.ILoadBalance;
import cn.wegostack.sundial.scheduler.core.loadbalance.RandomLoadBalance;
import cn.wegostack.sundial.scheduler.core.router.IRouter;
import cn.wegostack.sundial.scheduler.core.router.WorkerGroupRouter;
import cn.wegostack.sundial.scheduler.core.scheduler.dispatcher.Dispatcher;
import cn.wegostack.sundial.scheduler.core.scheduler.dispatcher.IDispatcher;
import cn.wegostack.sundial.scheduler.core.trigger.queue.TriggerEvent;
import cn.wegostack.sundial.scheduler.core.trigger.queue.TriggerEventQueue;
import cn.wegostack.sundial.scheduler.core.trigger.queue.TriggerEventQueueFactory;
import org.assertj.core.util.Lists;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengjianglong
 * @since 2021-06-24
 */
public class SundialScheduler {

    public void init() {
        ExecutorService executorService = new ThreadPoolExecutor(10, 10,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10));

        TriggerEventQueue queue = TriggerEventQueueFactory.getQueue();
        while (true) {
            try {
                TriggerEvent event = queue.take();

                // Build schedule context
                ScheduleContext context = buildContext(event);

                // Create worker by job type
                IDiscovery discovery = new SundialDiscovery();
                List<IRouter> routers = Lists.newArrayList(new WorkerGroupRouter());
                ILoadBalance loadBalance = new RandomLoadBalance();
                IInvoker invoker = new RpcInvoker();

                IDispatcher dispatcher = new Dispatcher(context, discovery, routers, loadBalance, invoker);
                executorService.submit(dispatcher);

                // log event
                long scheduleDelay = DateUtils.subtract(context.getSchedulerTime(),
                        context.getExpTriggerTime());
                long triggerDelay = DateUtils.subtract(context.getSchedulerTime(),
                        context.getTriggerTime());
                LogUtils.info("[{}] {} was scheduled,{},{}", context.getTriggerId(),
                        event.getJobItem().getJobId(), scheduleDelay, triggerDelay);
            } catch (Throwable e) {
                LogUtils.error("[scheduler] The exception is throw when scheduler job.", e);
            }
        }
    }

    private ScheduleContext buildContext(TriggerEvent event) {
        ScheduleContext context = new ScheduleContext();
        context.setJobItem(event.getJobItem());
        context.setTriggerId(event.getTriggerId());
        context.setTriggerTime(event.getTriggerTime());
        context.setSchedulerTime(new Date());
        return context;
    }
}
