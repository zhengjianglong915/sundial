package cn.wegostack.sundial.scheduler.dal.service;

import cn.wegostack.sundial.common.enums.LoadStatus;
import cn.wegostack.sundial.common.enums.TriggerStatus;
import cn.wegostack.sundial.common.model.JobTrigger;
import cn.wegostack.sundial.scheduler.dal.convector.JobTriggerConvector;
import cn.wegostack.sundial.scheduler.dal.entity.JobTriggerDO;
import cn.wegostack.sundial.scheduler.dal.repository.JobTriggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class JobTriggerService {

    @Autowired
    private JobTriggerRepository jobTriggerRepository;

    /**
     * Query the triggers which need load in special cluster, and belongs to loadServer.
     *
     * @param cluster
     * @param loadServer
     * @return
     */
    public List<JobTrigger> getNeedLoadTriggers(String cluster, String loadServer) {
        JobTriggerDO query = new JobTriggerDO();
        query.setLoadCluster(cluster);
        query.setLoadServer(loadServer);
        query.setLoadStatus(LoadStatus.INIT.name());
        query.setStatus(TriggerStatus.OPEN.name());
        Example example = Example.of(query);
        List<JobTriggerDO> triggerDOList = jobTriggerRepository.findAll(example);
        return JobTriggerConvector.convect(triggerDOList);
    }

    /**
     * Load Trigger
     *
     * @param jobTrigger
     * @return
     */
    public boolean loadTrigger(JobTrigger jobTrigger) {
        jobTrigger.setLoadStatus(LoadStatus.LOAD);
        jobTrigger.setLoadTime(new Date());
        JobTriggerDO triggerDO = JobTriggerConvector.convect(jobTrigger);
        return jobTriggerRepository.saveAndFlush(triggerDO) != null;
    }

    /**
     * Update load server
     *
     * @param loadStatus
     * @param loadServer
     * @param slot
     * @return
     */
    public boolean updateLoadServerBySlot(String loadStatus,
                                          String loadServer,
                                          Integer slot) {
        return jobTriggerRepository.updateLoadServerBySlot(loadStatus, loadServer, slot) > 0;
    }
}
