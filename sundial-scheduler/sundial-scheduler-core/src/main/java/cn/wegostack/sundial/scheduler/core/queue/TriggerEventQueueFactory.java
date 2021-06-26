package cn.wegostack.sundial.scheduler.core.queue;


import cn.wegostack.sundial.scheduler.core.queue.impl.LocalQueue;

/**
 * @author zhengjianglong
 * @since 2021-06-24
 */
public class TriggerEventQueueFactory {
    private static TriggerEventQueue queue = new LocalQueue();

    public static TriggerEventQueue getQueue() {
        return queue;
    }
}
