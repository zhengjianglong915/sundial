package cn.wegostack.sundial.scheduler.controller;

import cn.wegostack.sundial.common.constants.CommonConstants;
import cn.wegostack.sundial.common.exception.BadRequestException;
import cn.wegostack.sundial.common.utils.Generator;
import cn.wegostack.sundial.scheduler.dal.entity.JobDO;
import cn.wegostack.sundial.scheduler.dal.repository.JobRepository;
import cn.wegostack.sundial.scheduler.model.constants.RestConstants;
import cn.wegostack.sundial.scheduler.model.request.AddJobRequest;
import cn.wegostack.sundial.scheduler.model.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengjianglong
 * @since 2021-07-11
 */
@RestController
@RequestMapping(path = {RestConstants.WEBAPI, RestConstants.OPENAPI})
public class JobRestController {

    @Autowired
    private JobRepository jobRepository;

    @PostMapping(path = "/job")
    public Result addJob(@RequestBody AddJobRequest request) {
        Result result = Result.success();
        if (StringUtils.isEmpty(request.getAppName())) {
            throw new BadRequestException();
        }
        JobDO jobDO = new JobDO();
        BeanUtils.copyProperties(request, jobDO);

        String jobId = Generator.genJobId();
        jobDO.setJobId(jobId);
        int slot = jobId.hashCode() % CommonConstants.SLOT_COUNT;
        jobDO.setSlot(slot);
        jobRepository.save(jobDO);
        return result;
    }
}
