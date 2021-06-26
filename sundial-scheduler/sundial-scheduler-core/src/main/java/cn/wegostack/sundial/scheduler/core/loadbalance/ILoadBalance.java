package cn.wegostack.sundial.scheduler.core.loadbalance;


import cn.wegostack.sundial.common.enums.LoadBalanceType;
import cn.wegostack.sundial.common.model.Worker;

import java.util.List;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
public interface ILoadBalance {

    LoadBalanceType type();

    List<Worker> select(List<Worker> workers, LoadBalanceContext context);

}
