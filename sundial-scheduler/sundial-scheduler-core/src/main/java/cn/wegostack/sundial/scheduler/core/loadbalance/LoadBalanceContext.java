package cn.wegostack.sundial.scheduler.core.loadbalance;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
@Data
@NoArgsConstructor
public class LoadBalanceContext {
    /**
     * The count of workers selected to execute job, which default is 1.
     */
    private int selectCount = 1;

}
