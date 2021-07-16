package cn.wegostack.sundial.discovery.registry.store;

import cn.wegostack.sundial.scheduler.dal.repository.InstanceRepository;
import cn.wegostack.sundial.scheduler.dal.entity.InstanceDO;
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
        InstanceDO instanceDO = new InstanceDO();
        instanceDO.setIp(ip);

        Example<InstanceDO> example = Example.of(instanceDO);
        List<InstanceDO> instanceDOS = instanceRepository.findAll(example);
        if (CollectionUtils.isEmpty(instanceDOS)) {
            instanceDO.setIp(ip);
            instanceDO.setHostname(instance.getHostname());
            instanceDO.setAppName(appName);
            instanceDO.setHeartbeat(new Date());
            instanceRepository.saveAndFlush(instanceDO);
        } else {
            // update heartbeat
            InstanceDO instanceDB = instanceDOS.get(0);
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
        InstanceDO instanceDO = new InstanceDO();
        instanceDO.setAppName(appName);
        instanceDO.setStatus("RUNNING");
        Example<InstanceDO> example = Example.of(instanceDO);
        List<InstanceDO> instanceDOS = instanceRepository.findAll(example);
        List<cn.wegostack.sundial.discovery.registry.model.Instance> instanceList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(instanceDOS)) {
            return instanceList;
        }

        for (InstanceDO inst : instanceDOS) {
            cn.wegostack.sundial.discovery.registry.model.Instance instance = new cn.wegostack.sundial.discovery.registry.model.Instance();
            instance.setAppName(inst.getAppName());
            instance.setIp(inst.getIp());
            instanceList.add(instance);
        }

        return instanceList;
    }
}
