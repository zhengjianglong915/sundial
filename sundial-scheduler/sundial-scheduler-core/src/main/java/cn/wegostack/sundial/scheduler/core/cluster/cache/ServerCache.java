package cn.wegostack.sundial.scheduler.core.cluster.cache;

import cn.wegostack.sundial.common.model.JobTrigger;
import cn.wegostack.sundial.scheduler.dal.entity.JobDO;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zhengjianglong
 * @since 2021-07-11
 */
@Service
public class ServerCache {
    private Map<String, JobTrigger> triggerDOMap = Maps.newConcurrentMap();

    private Map<String, JobDO> jobMap = Maps.newConcurrentMap();

    /**
     * slot -> trigger
     */
    private static Map<Integer, List<JobTrigger>> slotMap = Maps.newConcurrentMap();


}
