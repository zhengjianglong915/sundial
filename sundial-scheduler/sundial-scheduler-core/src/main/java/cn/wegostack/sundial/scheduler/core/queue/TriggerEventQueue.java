package cn.wegostack.sundial.scheduler.core.queue;


/**
 * @author zhengjianglong
 * @since 2021-06-24
 */
public interface TriggerEventQueue {

    boolean offer(TriggerEvent event);

    /**
     * @return
     * @throws InterruptedException
     */
    TriggerEvent take() throws InterruptedException;

    int size();
}
