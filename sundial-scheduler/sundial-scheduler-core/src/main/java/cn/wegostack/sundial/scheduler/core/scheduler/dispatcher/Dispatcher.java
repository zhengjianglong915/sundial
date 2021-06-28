package cn.wegostack.sundial.scheduler.core.scheduler.dispatcher;



import cn.wegostack.sundial.common.enums.JobTypeEnums;
import cn.wegostack.sundial.common.model.JobItem;
import cn.wegostack.sundial.common.model.ScheduleContext;
import cn.wegostack.sundial.common.model.Worker;
import cn.wegostack.sundial.common.utils.LogUtils;
import cn.wegostack.sundial.scheduler.core.discovery.IDiscovery;
import cn.wegostack.sundial.scheduler.core.invoker.IInvoker;
import cn.wegostack.sundial.scheduler.core.loadbalance.ILoadBalance;
import cn.wegostack.sundial.scheduler.core.loadbalance.LoadBalanceContext;
import cn.wegostack.sundial.scheduler.core.router.IRouter;
import cn.wegostack.sundial.scheduler.core.router.RouterContext;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
            JobItem jobItem = context.getJobItem();
            LogUtils.info("[%s][worker] %s was triggered", context.getTriggerId(), jobItem.getName());
            // 1. find cluster
            List<Worker> workers = discovery(jobItem);

            // 2. route
            workers = route(context, jobItem, workers);

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

    private List<Worker> discovery(JobItem jobItem) {
        String dataId = Publisher.genDataId("Worker", jobItem.getWorkspaceId(), jobItem.getAppName());

        List<Publisher<Worker>> publishers = this.discovery.discovery(jobItem.getNamespace(),
                jobItem.getWorkspaceId(), dataId);
        if (CollectionUtils.isEmpty(publishers)) {
            throw new RuntimeException(String.format("The instance of %s does not found",
                    jobItem.getAppName()));
        }

        List<Worker> workerList = publishers.stream().map(Publisher::getValue)
                .collect(Collectors.toList());
        return workerList;
    }

    private List<Worker> route(ScheduleContext context, JobItem jobItem, List<Worker> workers) {
        RouterContext routerContext = new RouterContext();
        routerContext.setWorkerGroup(jobItem.getWorkerGroup());
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
