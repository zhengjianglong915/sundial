package cn.wegostack.sundial.discovery.registry.store;

import cn.wegostack.sundial.scheduler.dal.repository.InstanceRepository;
import cn.wegostack.sundial.scheduler.dal.entity.Instance;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zhengjianglong
 * @since 2021-07-04
 */
@Service
public class DBRegistryStore {

    @Autowired
    private InstanceRepository instanceRepository;

    /**
     * registry publisher
     *
     * @param dataId
     * @param instance
     */
    public void registerPublisher(String dataId, cn.wegostack.sundial.discovery.registry.model.Instance instance) {
        String appName = instance.getAppName();
        String ip = instance.getIp();
        Instance instanceDO = new Instance();
        instanceDO.setIp(ip);

        Example<Instance> example = Example.of(instanceDO);
        List<Instance> instanceDOS = instanceRepository.findAll(example);
        if (CollectionUtils.isEmpty(instanceDOS)) {
            instanceDO.setIp(ip);
            instanceDO.setHostname(instance.getHostname());
            instanceDO.setAppName(appName);
            instanceDO.setHeartbeat(new Date());
            instanceRepository.saveAndFlush(instanceDO);
        } else {
            // update heartbeat
            Instance instanceDB = instanceDOS.get(0);
            instanceDB.setStatus("RUNNING");
            instanceDB.setHeartbeat(new Date());
            instanceRepository.saveAndFlush(instanceDB);
        }
    }

    /**
     * query publishers list
     *
     * @param appName
     * @return
     */
    public List<cn.wegostack.sundial.discovery.registry.model.Instance> queryPublisher(String appName) {
        Instance instanceDO = new Instance();
        instanceDO.setAppName(appName);
        instanceDO.setStatus("RUNNING");
        Example<Instance> example = Example.of(instanceDO);
        List<Instance> instanceDOS = instanceRepository.findAll(example);
        List<cn.wegostack.sundial.discovery.registry.model.Instance> instanceList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(instanceDOS)) {
            return instanceList;
        }

        for (Instance inst : instanceDOS) {
            cn.wegostack.sundial.discovery.registry.model.Instance instance = new cn.wegostack.sundial.discovery.registry.model.Instance();
            instance.setAppName(inst.getAppName());
            instance.setIp(inst.getIp());
            instanceList.add(instance);
        }

        return instanceList;
    }
}
