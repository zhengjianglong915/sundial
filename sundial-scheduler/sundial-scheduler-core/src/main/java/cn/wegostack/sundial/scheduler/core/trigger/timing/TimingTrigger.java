package cn.wegostack.sundial.scheduler.core.trigger.timing;

import cn.wegostack.sundial.common.model.JobItem;
import cn.wegostack.sundial.common.threadpool.ScheduledService;
import cn.wegostack.sundial.common.utils.DateUtils;
import cn.wegostack.sundial.common.utils.Generator;
import cn.wegostack.sundial.common.utils.LogUtils;
import cn.wegostack.sundial.scheduler.core.trigger.ITrigger;
import cn.wegostack.sundial.scheduler.core.trigger.queue.TriggerEvent;
import cn.wegostack.sundial.scheduler.core.trigger.queue.TriggerEventQueue;
import cn.wegostack.sundial.scheduler.core.trigger.queue.TriggerEventQueueFactory;
import org.assertj.core.util.Maps;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengjianglong
 * @since 2021-06-22
 */
public class TimingTrigger implements ITrigger {

    private ScheduledService scheduledService;

    /**
     * The Timing job are always executed once, and they are triggered at a specific time
     */
    private Queue<JobItem> jobQueue = new PriorityQueue(new TimeComparator());

    private Map<String, JobItem> cache = Maps.newWeakHashMap();


    @PostConstruct
    public void init() {
        scheduledService = new ScheduledService("TimingTrigger", () -> {
            trigger();
        }, 1, 1, TimeUnit.SECONDS);
        scheduledService.start();
    }

    /**
     * Check whether there are jobs in the job queue that meet the trigger conditions.
     * If they meet the trigger conditions, they will be triggered immediately.
     */
    private void trigger() {
        if (jobQueue.size() == 0) {
            return;
        }

        Date current = new Date();
        JobItem peek = jobQueue.peek();

        // Get Job queue
        TriggerEventQueue queue = TriggerEventQueueFactory.getQueue();

        while (checkTriggerCondition(peek, current)) {
            JobItem jobItem = jobQueue.poll();
            LogUtils.info("[%s][TimingTrigger] The job [%s] is meet the trigger condition.",
                    jobItem.getId(), jobItem.getName());

            // Build trigger event
            TriggerEvent event = buildTriggerEvent(peek, jobItem);

            // Offer the jobs that meet the trigger condition into queue,
            // waiting to be scheduled.
            queue.offer(event);

            // Gets the next job that may meet the trigger condition
            peek = jobQueue.peek();
        }
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

    private boolean checkTriggerCondition(JobItem peek, Date current) {
        if (peek == null) {
            return false;
        }

        // check if time is ready
        Date triggerDate = DateUtils.parse(peek.getTriggerExp());
        return triggerDate.before(current);
    }

    private TriggerEvent buildTriggerEvent(JobItem peek, JobItem jobItem) {
        TriggerEvent event = new TriggerEvent();
        event.setJobItem(jobItem);
        Date expectTriggerTime = DateUtils.parse(peek.getTriggerExp());
        event.setExpTriggerTime(expectTriggerTime);
        event.setTriggerTime(new Date());
        event.setTriggerId(Generator.genTriggerId(jobItem.getJobId()));
        return event;
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
