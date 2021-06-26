package cn.wegostack.sundial.scheduler.core.trigger.queue.impl;


import cn.wegostack.sundial.scheduler.core.trigger.queue.TriggerEvent;
import cn.wegostack.sundial.scheduler.core.trigger.queue.TriggerEventQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author zhengjianglong
 * @since 2021-06-24
 */
public class LocalQueue implements TriggerEventQueue {
    private static ArrayBlockingQueue<TriggerEvent> queue = new ArrayBlockingQueue(10000);

    @Override
    public boolean offer(TriggerEvent event) {
        return queue.offer(event);
    }

    @Override
    public TriggerEvent take() throws InterruptedException {
        return queue.take();
    }

    @Override
    public int size() {
        return queue.size();
    }
}
