package cn.wegostack.sundial.scheduler.core.loadbalance;

import cn.wegostack.sundial.common.enums.LoadBalanceType;
import cn.wegostack.sundial.common.model.Worker;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
public class RandomLoadBalance implements ILoadBalance {
    private static Random random = new Random(System.currentTimeMillis());

    @Override
    public LoadBalanceType type() {
        return LoadBalanceType.RANDMON;
    }

    @Override
    public List<Worker> select(List<Worker> workers, LoadBalanceContext context) {
        if (CollectionUtils.isEmpty(workers)) {
            return workers;
        }

        List<Worker> result = Lists.newArrayList();
        if (workers.size() < context.getSelectCount()) {
            int randomIdx = random.nextInt(workers.size());
            result.add(workers.get(randomIdx));
        } else {
            Collections.shuffle(workers);
            for (int i = 0; i < context.getSelectCount(); i++) {
                result.add(workers.get(i));
            }
        }
        return result;
    }
}
