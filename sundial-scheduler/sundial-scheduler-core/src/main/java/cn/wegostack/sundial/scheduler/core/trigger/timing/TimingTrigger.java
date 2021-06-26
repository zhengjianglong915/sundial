package cn.wegostack.sundial.scheduler.core.trigger.timing;

import cn.wegostack.sundial.common.model.JobItem;
import cn.wegostack.sundial.common.utils.DateUtils;
import cn.wegostack.sundial.common.utils.Generator;
import cn.wegostack.sundial.common.utils.LogUtils;
import cn.wegostack.sundial.scheduler.core.queue.TriggerEvent;
import cn.wegostack.sundial.scheduler.core.queue.TriggerEventQueue;
import cn.wegostack.sundial.scheduler.core.queue.TriggerEventQueueFactory;
import cn.wegostack.sundial.scheduler.core.trigger.ITrigger;
import org.assertj.core.util.Maps;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengjianglong
 * @since 2021-06-22
 */
public class TimingTrigger implements ITrigger {

    private ScheduledExecutorService scheduledExecutorService;

    /**
     * The Timing job are always executed once, and they are triggered at a specific time
     */
    private Queue<JobItem> jobQueue = new PriorityQueue(new TimeComparator());

    private Map<String, JobItem> cache = Maps.newWeakHashMap();


    @PostConstruct
    public void init() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            schedule();
        }, 1, 1, TimeUnit.SECONDS);
    }

    private void schedule() {
        if (jobQueue.size() == 0) {
            return;
        }

        Date current = new Date();
        JobItem peek = jobQueue.peek();
        while (checkTriggerCondition(peek, current)) {
            JobItem jobItem = jobQueue.poll();
            LogUtils.info("[TimingTrigger] The job [%s] is meet the trigger condition.", jobItem.getId());

            // Get queue
            TriggerEventQueue queue = TriggerEventQueueFactory.getQueue();

            // Build trigger event
            TriggerEvent event = new TriggerEvent();
            event.setJobItem(jobItem);
            Date expectTriggerTime = DateUtils.parse(peek.getTriggerExp());
            event.setExpTriggerTime(expectTriggerTime);
            event.setTriggerTime(new Date());
            event.setTriggerId(Generator.genTriggerId(jobItem.getJobId()));

            // Offer the jobs that meet the trigger condition into queue, waiting to be scheduled.
            queue.offer(event);

            // Gets the next job that may meet the trigger condition
            peek = jobQueue.peek();
        }
    }

    private boolean checkTriggerCondition(JobItem peek, Date current) {
        if (peek == null) {
            return false;
        }

        // check if time is ready
        Date triggerDate = DateUtils.parse(peek.getTriggerExp());
        return triggerDate.before(current);
    }

    @Override
    public synchronized boolean add(JobItem jobItem) {
        String key = jobItem.getJobId();
        if (!cache.containsKey(key)) {
            jobQueue.offer(jobItem);
            cache.put(key, jobItem);
        }
        return false;
    }

    @Override
    public synchronized boolean remove(JobItem job) {
        JobItem remove = cache.remove(job.getJobId());
        jobQueue.remove(remove);
        return true;
    }

    @Override
    public synchronized boolean refresh(JobItem jobItem) {
        // remove from queue
        JobItem remove = cache.get(jobItem.getId());
        jobQueue.remove(remove);

        // offer to queue
        cache.put(jobItem.getJobId(), jobItem);
        jobQueue.offer(jobItem);
        return true;
    }

    /**
     *
     */
    private class TimeComparator implements Comparator<JobItem> {
        /**
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(JobItem o1, JobItem o2) {
            Date date1 = DateUtils.parse(o1.getTriggerExp());
            Date date2 = DateUtils.parse(o2.getTriggerExp());
            if (date1.getTime() < date2.getTime()) {
                return -1;
            } else if (date1.getTime() > date2.getTime()) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
