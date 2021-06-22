package cn.wegostack.sundial.scheduler.core.trigger;

import cn.wegostack.sundial.scheduler.core.sundial.common.model.Job;

/**
 * @author zhengjianglong
 * @since 2021-06-22
 */
public interface ITrigger {

    boolean add(Job job);


    boolean remove(Job job);


    boolean refrash(Job job);
}
