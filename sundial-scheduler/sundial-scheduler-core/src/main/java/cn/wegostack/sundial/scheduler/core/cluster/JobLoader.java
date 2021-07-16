package cn.wegostack.sundial.scheduler.core.cluster;

import cn.wegostack.sundial.common.model.JobMeta;
import cn.wegostack.sundial.common.model.JobTrigger;
import cn.wegostack.sundial.common.threadpool.ScheduledService;
import cn.wegostack.sundial.common.utils.LocalServer;
import cn.wegostack.sundial.scheduler.dal.entity.JobTriggerDO;
import cn.wegostack.sundial.scheduler.dal.service.JobService;
import cn.wegostack.sundial.scheduler.dal.service.JobTriggerService;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhengjianglong
 * @since 2021-07-11
 */
@Service
public class JobLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobLoader.class);

    private ScheduledService scheduledService;

    @Autowired
    private JobTriggerService jobTriggerService;

    @Autowired
    private JobService jobService;

    private Map<String, JobTrigger> triggerDOMap = Maps.newConcurrentMap();

    public JobLoader() {
        scheduledService = new ScheduledService("Sundial-JobLoader", () -> {
            if (LocalServer.isReady()) {
                loadJob();
            }
        }, 1, 1, TimeUnit.SECONDS);
        scheduledService.start();
    }

    private void loadJob() {
        // query all need load trigger
        List<JobTrigger> needLoadTriggers = jobTriggerService.getNeedLoadTriggers(LocalServer.getCluster(),
                LocalServer.getIp());
        if (CollectionUtils.isEmpty(needLoadTriggers)) {
            return;
        }

        for (JobTrigger trigger : needLoadTriggers) {
            JobMeta jobMeta = trigger.getJobMeta();
            String jobId = jobMeta.getJobId();
            try {
                String key = jobId + "#" + trigger.getTriggerCell();
                if (triggerDOMap.containsKey(key)) {
                    continue;
                }

                jobMeta = jobService.findByJobId(jobId);

//                JobMeta jobMeta = new JobMeta();
//                BeanUtils.copyProperties(jobDO, jobMeta);
//                jobMeta.setTriggerType(TriggerType.valueOf(jobDO.getTriggerType()));
//                jobTrigger.setJobMeta(jobMeta);
//                ITriggerManager triggerManager = TriggerManagerFactory.getTriggerManager(jobMeta.getTriggerType());
//                triggerManager.add(jobTrigger);

                jobTriggerService.loadTrigger(trigger);
            } catch (Exception e) {
                LOGGER.error(String.format("[JobLoader] Failed to load job trigger %s", jobId), e);
            }
        }
    }
}
