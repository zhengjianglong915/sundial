package cn.wegostack.sundial.scheduler.core.router;


import cn.wegostack.sundial.common.model.Worker;

import java.util.List;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
public interface IRouter {

    /**
     * @param workers
     * @param context
     * @return
     */
    List<Worker> route(List<Worker> workers, RouterContext context);
}