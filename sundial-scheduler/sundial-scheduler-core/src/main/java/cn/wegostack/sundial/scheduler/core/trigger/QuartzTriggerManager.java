package cn.wegostack.sundial.scheduler.core.trigger;

import cn.wegostack.sundial.common.model.JobMeta;
import cn.wegostack.sundial.common.model.JobTrigger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhengjianglong
 * @since 2021-07-08
 */
public class QuartzTriggerManager implements ITriggerManager {

    private Logger LOGGER = LoggerFactory.getLogger(QuartzTriggerManager.class);

    private static Scheduler scheduler;

    public QuartzTriggerManager() {
        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            LOGGER.info("[Trigger] QuartzScheduler was inited");
        } catch (Exception e) {
            throw new RuntimeException("[Trigger] QuartzScheduler init exception.", e);
        } finally {
        }
    }

    @Override
    public boolean add(JobTrigger jobTrigger) {
        try {
            JobMeta jobMeta = jobTrigger.getJobMeta();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobMeta.getJobId());
            JobDetail jobDetail = JobBuilder.newJob(JobProxy.class)
                    .withIdentity(jobMeta.getJobId())
                    .build();

            Trigger trigger = null;
            switch (jobMeta.getTriggerType()) {
                case CRON:
                    CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(jobMeta.getTriggerExp());
                    trigger = TriggerBuilder.newTrigger()
                            .withIdentity(triggerKey)
                            .withSchedule(cronScheduleBuilder)
                            .build();
                    break;
            }

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean remove(JobTrigger jobTrigger) {
        JobMeta jobMeta = jobTrigger.getJobMeta();
        JobKey jobKey = JobKey.jobKey(jobMeta.getJobId());
        // return scheduler.deleteJob(jobKey);
        return false;
    }

    @Override
    public boolean refresh(JobTrigger job) {
        return false;
    }
}
