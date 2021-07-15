package cn.wegostack.sundial.scheduler.core.scheduler.dispatcher;



import cn.wegostack.sundial.common.enums.JobTypeEnums;
import cn.wegostack.sundial.common.model.JobMeta;
import cn.wegostack.sundial.common.model.JobTrigger;
import cn.wegostack.sundial.common.model.ScheduleContext;
import cn.wegostack.sundial.common.model.Worker;
import cn.wegostack.sundial.common.utils.LogUtils;
import cn.wegostack.sundial.registry.client.model.Publisher;
import cn.wegostack.sundial.scheduler.core.discovery.IDiscovery;
import cn.wegostack.sundial.scheduler.core.invoker.IInvoker;
import cn.wegostack.sundial.scheduler.core.loadbalance.ILoadBalance;
import cn.wegostack.sundial.scheduler.core.loadbalance.LoadBalanceContext;
import cn.wegostack.sundial.scheduler.core.router.IRouter;
import cn.wegostack.sundial.scheduler.core.router.RouterContext;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * @author zhengjianglong
 * @since 2021-06-25
 */
public class Dispatcher implements IDispatcher {

    private ScheduleContext context;

    private IDiscovery discovery;

    private List<IRouter> routers;

    private IInvoker invoker;

    private ILoadBalance loadBalance;


    public Dispatcher(ScheduleContext context, IDiscovery discovery, List<IRouter> routers,
                      ILoadBalance loadBalance, IInvoker invoker) {
        this.context = context;
        this.discovery = discovery;
        this.routers = routers;
        this.invoker = invoker;
        this.loadBalance = loadBalance;
    }

    @Override
    public Set<JobTypeEnums> type() {
        return null;
    }

    @Override
    public void action(ScheduleContext context) {
        try {
            JobTrigger jobTrigger = context.getJobTrigger();
            JobMeta jobMeta = jobTrigger.getJobMeta();

            LogUtils.info("[%s][worker] %s was triggered", context.getTriggerId(), jobMeta.getName());
            // 1. find cluster
            List<Worker> workers = discovery(jobTrigger);

            // 2. route
            workers = route(context, jobTrigger, workers);

            // 3. load balance
            workers = loadBalance(workers);

            // 4. invoke
            invoker.invoke(workers, context);

            // 5. result process
        } catch (Exception e) {
            e.printStackTrace();
            // Failed event
        }
    }

    private List<Worker> discovery(JobTrigger jobTrigger) {
        JobMeta jobMeta = jobTrigger.getJobMeta();
        String dataId = jobMeta.getAppName();
        List<Publisher> publishers = this.discovery.discovery(dataId);
        if (CollectionUtils.isEmpty(publishers)) {
            throw new RuntimeException(String.format("The instance of %s does not found",
                    jobMeta.getAppName()));
        }

        List<Worker> workerList = Lists.newArrayList();
        for (Publisher publisher : publishers) {
            Worker worker = new Worker();
            worker.setRemoteIp(publisher.getIp());
            worker.setOriginIp(publisher.getIp());
            workerList.add(worker);
        }
        return workerList;
    }

    private List<Worker> route(ScheduleContext context, JobTrigger jobTrigger,
                               List<Worker> workers) {
        JobMeta jobMeta = jobTrigger.getJobMeta();
        RouterContext routerContext = new RouterContext();
        routerContext.setWorkerGroup(jobMeta.getWorkerGroup());
        routerContext.setTags(context.getTags());

        for (IRouter router : routers) {
            workers = router.route(workers, routerContext);
        }
        return workers;
    }

    private List<Worker> loadBalance(List<Worker> workers) {
        if (CollectionUtils.isEmpty(workers)) {
            throw new RuntimeException(String.format("There are not worker to load balance"));
        }

        LoadBalanceContext loadBalanceContext = new LoadBalanceContext();
        workers = loadBalance.select(workers, loadBalanceContext);
        return workers;
    }

    @Override
    public void run() {
        action(context);
    }
}
