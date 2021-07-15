package cn.wegostack.sundial.scheduler.core.cluster;

import cn.wegostack.sundial.common.enums.TriggerType;
import cn.wegostack.sundial.common.model.JobMeta;
import cn.wegostack.sundial.common.model.JobTrigger;
import cn.wegostack.sundial.common.threadpool.ScheduledService;
import cn.wegostack.sundial.common.utils.LocalServer;
import cn.wegostack.sundial.scheduler.core.trigger.ITriggerManager;
import cn.wegostack.sundial.scheduler.core.trigger.TriggerManagerFactory;
import cn.wegostack.sundial.scheduler.dal.entity.JobDO;
import cn.wegostack.sundial.scheduler.dal.entity.JobTriggerDO;
import cn.wegostack.sundial.scheduler.dal.repository.JobRepository;
import cn.wegostack.sundial.scheduler.dal.repository.JobTriggerRepository;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    private JobTriggerRepository triggerRepository;

    @Autowired
    private JobRepository jobRepository;

    private Map<String, JobTriggerDO> triggerDOMap = Maps.newConcurrentMap();

    public JobLoader() {
        scheduledService = new ScheduledService("Sundial-JobLoader", () -> {
            if (LocalServer.isReady()) {
                loadJob();
            }
        }, 1, 1, TimeUnit.SECONDS);
        scheduledService.start();
    }

    private void loadJob() {
        JobTriggerDO query = new JobTriggerDO();
        query.setLoadCluster(LocalServer.getCluster());
        query.setLoadServer(LocalServer.getIp());
        query.setLoadStatus("INIT");
        query.setStatus("OPEN");
        Example example = Example.of(query);
        List<JobTriggerDO> triggers = triggerRepository.findAll(example);

        if (CollectionUtils.isEmpty(triggers)) {
            return;
        }

        for (JobTriggerDO trigger : triggers) {
            String jobId = trigger.getJobId();
            try {
                String key = jobId + "#" + trigger.getTriggerCell();
                if (triggerDOMap.containsKey(key)) {
                    continue;
                }

                JobTrigger jobTrigger = new JobTrigger();
                BeanUtils.copyProperties(trigger, jobTrigger);

                JobDO jobDO = new JobDO();
                jobDO.setJobId(trigger.getJobId());
                Example queryJob = Example.of(jobDO);
                Optional<JobDO> one = jobRepository.findOne(queryJob);
                jobDO = one.get();

                JobMeta jobMeta = new JobMeta();
                BeanUtils.copyProperties(jobDO, jobMeta);
                jobMeta.setTriggerType(TriggerType.valueOf(jobDO.getTriggerType()));
                jobTrigger.setJobMeta(jobMeta);

                ITriggerManager triggerManager = TriggerManagerFactory.getTriggerManager(jobMeta.getTriggerType());
                triggerManager.add(jobTrigger);

                trigger.setLoadStatus("LOAD");
                trigger.setLoadTime(new Date());
                triggerRepository.saveAndFlush(trigger);
            } catch (Exception e) {
                LOGGER.error(String.format("[JobLoader] Failed to load job trigger %s", jobId), e);
            }
        }
    }
}
