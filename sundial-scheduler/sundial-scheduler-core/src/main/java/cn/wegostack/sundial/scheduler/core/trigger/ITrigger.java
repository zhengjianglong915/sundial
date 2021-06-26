package cn.wegostack.sundial.scheduler.core.trigger;


import cn.wegostack.sundial.common.model.JobItem;

/**
 * @author zhengjianglong
 * @since 2021-06-22
 */
public interface ITrigger {

    boolean add(JobItem job);

    boolean remove(JobItem job);


    boolean refresh(JobItem job);

}
