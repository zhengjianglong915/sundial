package cn.wegostack.sundial.scheduler.core.trigger.timing;

import cn.wegostack.sundial.common.model.JobMeta;
import cn.wegostack.sundial.common.model.JobTrigger;
import cn.wegostack.sundial.common.threadpool.ScheduledService;
import cn.wegostack.sundial.common.utils.DateUtils;
import cn.wegostack.sundial.common.utils.Generator;
import cn.wegostack.sundial.common.utils.LogUtils;
import cn.wegostack.sundial.scheduler.core.trigger.ITriggerManager;
import cn.wegostack.sundial.scheduler.core.trigger.queue.TriggerEvent;
import cn.wegostack.sundial.scheduler.core.trigger.queue.TriggerEventQueue;
import cn.wegostack.sundial.scheduler.core.trigger.queue.TriggerEventQueueFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengjianglong
 * @since 2021-06-22
 */
public class TimingTriggerManager implements ITriggerManager {

    private ScheduledService scheduledService;

    /**
     * The Timing job are always executed once, and they are triggered at a specific time
     */
    private Queue<JobTrigger> jobQueue = new PriorityQueue(new TimeComparator());

    private Map<String, JobTrigger> cache = new ConcurrentHashMap<>();


    public void TimingTrigger() {
        scheduledService = new ScheduledService("TimingTrigger", () -> {
            trigger();
        }, 0, 500, TimeUnit.MILLISECONDS);
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
        JobTrigger peek = jobQueue.peek();

        // Get Job queue
        TriggerEventQueue queue = TriggerEventQueueFactory.getQueue();

        while (checkTriggerCondition(peek, current)) {
            JobTrigger jobTrigger = jobQueue.poll();
            JobMeta jobMeta = jobTrigger.getJobMeta();
            LogUtils.info("[%s][TimingTrigger] The job [%s] is meet the trigger condition.",
                    jobMeta.getId(), jobMeta.getName());

            // Build trigger event
            TriggerEvent event = buildTriggerEvent(jobTrigger);

            // Offer the jobs that meet the trigger condition into queue,
            // waiting to be scheduled.
            queue.offer(event);

            // Gets the next job that may meet the trigger condition
            peek = jobQueue.peek();
        }
    }

    @Override
    public synchronized boolean add(JobTrigger jobTrigger) {
        JobMeta jobMeta = jobTrigger.getJobMeta();
        String key = jobMeta.getJobId();
        if (!cache.containsKey(key)) {
            jobQueue.offer(jobTrigger);
            cache.put(key, jobTrigger);
        }
        return false;
    }

    @Override
    public synchronized boolean remove(JobTrigger jobTrigger) {
        JobMeta jobMeta = jobTrigger.getJobMeta();
        JobTrigger remove = cache.remove(jobMeta.getJobId());
        jobQueue.remove(remove);
        return true;
    }

    @Override
    public synchronized boolean refresh(JobTrigger jobTrigger) {
        JobMeta jobMeta = jobTrigger.getJobMeta();
        // remove from queue
        JobTrigger remove = cache.get(jobMeta.getId());
        jobQueue.remove(remove);

        // offer to queue
        cache.put(jobMeta.getJobId(), jobTrigger);
        jobQueue.offer(jobTrigger);
        return true;
    }

    private boolean checkTriggerCondition(JobTrigger peek, Date current) {
        if (peek == null) {
            return false;
        }

        JobMeta jobMeta = peek.getJobMeta();
        // check if time is ready
        Date triggerDate = DateUtils.parse(jobMeta.getTriggerExp());
        return triggerDate.before(current);
    }

    private TriggerEvent buildTriggerEvent(JobTrigger jobTrigger) {
        JobMeta jobMeta = jobTrigger.getJobMeta();
        TriggerEvent event = new TriggerEvent();
        event.setJobTrigger(jobTrigger);
        Date expectTriggerTime = DateUtils.parse(jobMeta.getTriggerExp());
        event.setExpTriggerTime(expectTriggerTime);
        event.setTriggerTime(new Date());
        event.setTriggerId(Generator.genTriggerId(jobMeta.getJobId()));
        return event;
    }

    /**
     *
     */
    private class TimeComparator implements Comparator<JobTrigger> {
        /**
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(JobTrigger o1, JobTrigger o2) {
            JobMeta jobMeta1 = o1.getJobMeta();
            JobMeta jobMeta2 = o2.getJobMeta();
            Date date1 = DateUtils.parse(jobMeta1.getTriggerExp());
            Date date2 = DateUtils.parse(jobMeta2.getTriggerExp());
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
