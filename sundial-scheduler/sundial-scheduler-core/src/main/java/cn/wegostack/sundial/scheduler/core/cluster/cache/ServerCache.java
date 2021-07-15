package cn.wegostack.sundial.scheduler.core.cluster.cache;

import cn.wegostack.sundial.scheduler.dal.entity.JobDO;
import cn.wegostack.sundial.scheduler.dal.entity.JobTriggerDO;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author zhengjianglong
 * @since 2021-07-11
 */
@Service
public class ServerCache {
    private Map<String, JobTriggerDO> triggerDOMap = Maps.newConcurrentMap();

    private Map<String, JobDO> jobMap = Maps.newConcurrentMap();

}
