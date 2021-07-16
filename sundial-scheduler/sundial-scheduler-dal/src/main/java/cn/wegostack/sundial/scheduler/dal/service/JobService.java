package cn.wegostack.sundial.scheduler.dal.service;

import cn.wegostack.sundial.common.model.JobMeta;
import cn.wegostack.sundial.scheduler.dal.convector.JobMetaConvector;
import cn.wegostack.sundial.scheduler.dal.entity.JobDO;
import cn.wegostack.sundial.scheduler.dal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    /**
     * Query job by id
     *
     * @param jobId
     * @return
     */
    public JobMeta findByJobId(String jobId) {
        JobDO jobDO = jobRepository.findByJobId(jobId);
        return JobMetaConvector.convect(jobDO);
    }
}
