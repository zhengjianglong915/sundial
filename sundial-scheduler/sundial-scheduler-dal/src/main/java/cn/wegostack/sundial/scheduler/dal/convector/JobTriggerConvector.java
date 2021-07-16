package cn.wegostack.sundial.scheduler.dal.convector;

import cn.wegostack.sundial.common.enums.LoadStatus;
import cn.wegostack.sundial.common.enums.TriggerStatus;
import cn.wegostack.sundial.common.model.JobMeta;
import cn.wegostack.sundial.common.model.JobTrigger;
import cn.wegostack.sundial.scheduler.dal.entity.JobTriggerDO;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class JobTriggerConvector {

    /**
     * Convect JobTriggerDO to JobTrigger
     *
     * @param source
     * @return
     */
    public static JobTrigger convect(JobTriggerDO source) {
        if (null == source) {
            return null;
        }

        JobTrigger target = new JobTrigger();
        BeanUtils.copyProperties(source, target);
        target.setLoadStatus(LoadStatus.of(source.getLoadStatus()));
        target.setStatus(TriggerStatus.of(source.getStatus()));
        JobMeta jobMeta = new JobMeta();
        jobMeta.setJobId(source.getJobId());
        target.setJobMeta(jobMeta);
        return target;
    }

    /**
     * @param sourceList
     * @return
     */
    public static List<JobTrigger> convect(List<JobTriggerDO> sourceList) {
        List<JobTrigger> targetsList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(sourceList)) {
            return targetsList;
        }

        for (JobTriggerDO jobTriggerDO : sourceList) {
            JobTrigger jobTrigger = convect(jobTriggerDO);
            targetsList.add(jobTrigger);
        }
        return targetsList;
    }

    /**
     * Convect jobTrigger to JobTriggerDO
     *
     * @param source
     * @return
     */
    public static JobTriggerDO convect(JobTrigger source) {
        if (null == source) {
            return null;
        }

        JobTriggerDO target = new JobTriggerDO();
        BeanUtils.copyProperties(source, target);
        target.setLoadStatus(source.getLoadStatus().name());
        target.setStatus(source.getStatus().name());
        JobMeta jobMeta = source.getJobMeta();
        if (jobMeta != null) {
            target.setJobId(jobMeta.getJobId());
        }
        return target;
    }
}
