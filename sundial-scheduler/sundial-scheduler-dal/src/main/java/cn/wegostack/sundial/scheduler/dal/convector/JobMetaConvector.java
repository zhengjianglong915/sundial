package cn.wegostack.sundial.scheduler.dal.convector;

import cn.wegostack.sundial.common.enums.TriggerType;
import cn.wegostack.sundial.common.model.JobMeta;
import cn.wegostack.sundial.scheduler.dal.entity.JobDO;
import org.springframework.beans.BeanUtils;

public class JobMetaConvector {

    public static JobMeta convect(JobDO jobDO) {
        if (null == jobDO) {
            return null;
        }

        JobMeta jobMeta = new JobMeta();
        BeanUtils.copyProperties(jobDO, jobMeta);
        jobMeta.setTriggerType(TriggerType.of(jobDO.getTriggerType()));
        // jobMeta.setType();
        return jobMeta;
    }
}
