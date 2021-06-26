package cn.wegostack.sundial.scheduler.core.router;



import cn.wegostack.sundial.common.model.Worker;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Find worker by group of instance.
 *
 * @author zhengjianglong
 * @since 2021-06-26
 */
public class WorkerGroupRouter implements IRouter {

    @Override
    public List<Worker> route(List<Worker> workers, RouterContext context) {
        String workerGroup = context.getWorkerGroup();
        if (StringUtils.isEmpty(workerGroup)) {
            return workers;
        }

        List<Worker> result = Lists.newArrayList();
        for (Worker worker : workers) {
            if (workerGroup.equals(workerGroup)) {
                result.add(worker);
            }
        }
        return result;
    }

}
