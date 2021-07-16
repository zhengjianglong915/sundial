package cn.wegostack.sundial.scheduler.controller;

import cn.wegostack.sundial.common.enums.TriggerStatus;
import cn.wegostack.sundial.common.exception.BadRequestException;
import cn.wegostack.sundial.common.utils.LocalServer;
import cn.wegostack.sundial.scheduler.core.cluster.ConsistantHash;
import cn.wegostack.sundial.scheduler.dal.entity.JobDO;
import cn.wegostack.sundial.scheduler.dal.entity.JobTriggerDO;
import cn.wegostack.sundial.scheduler.dal.repository.JobRepository;
import cn.wegostack.sundial.scheduler.dal.repository.JobTriggerRepository;
import cn.wegostack.sundial.scheduler.model.constants.RestConstants;
import cn.wegostack.sundial.scheduler.model.request.OperateTriggerRequest;
import cn.wegostack.sundial.scheduler.model.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = {RestConstants.WEBAPI, RestConstants.OPENAPI})
public class JobTriggerController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobTriggerRepository jobTriggerRepository;

    /**
     * open trigger
     *
     * @param request
     * @return
     */
    @PostMapping(path = "/trigger/open")
    public Result openTrigger(@RequestBody OperateTriggerRequest request) {
        Result result = Result.success();
        if (StringUtils.isEmpty(request.getJobId())) {
            throw new BadRequestException("The jobId should not be empty.");
        }

        JobDO jobDO = new JobDO();
        jobDO.setJobId(request.getJobId());
        Example example = Example.of(jobDO);
        Optional<JobDO> one = jobRepository.findOne(example);
        if (!one.isPresent()) {
            throw new BadRequestException(String.format("The job does not found by %s", request.getJobId()));
        }
        jobDO = one.get();

        String cluster = request.getCluster();
        if (StringUtils.isEmpty(cluster)) {
            cluster = LocalServer.getCluster();
        }


        JobTriggerDO jobTriggerDO = new JobTriggerDO();
        jobTriggerDO.setJobId(jobDO.getJobId());
        jobTriggerDO.setLoadCluster(cluster);
        jobTriggerDO.setTriggerCell("DEFAULT");

        Example e = Example.of(jobTriggerDO);
        Optional<JobTriggerDO> triggerOne = jobTriggerRepository.findOne(e);
        if (triggerOne.isPresent()) {
            if (TriggerStatus.OPEN.name().equals(jobTriggerDO.getStatus())) {
                return result;
            }
        } else {
            jobTriggerDO.setStatus(TriggerStatus.OPEN.name());
            jobTriggerDO.setLoadStatus("INIT");
            jobTriggerDO.setSlot(jobDO.getSlot());
        }

        String server = ConsistantHash.getServer(jobDO.getSlot());
        jobTriggerDO.setLoadServer(server);
        jobTriggerDO.setStatus(TriggerStatus.OPEN.name());
        jobTriggerRepository.saveAndFlush(jobTriggerDO);
        return result;
    }
}
