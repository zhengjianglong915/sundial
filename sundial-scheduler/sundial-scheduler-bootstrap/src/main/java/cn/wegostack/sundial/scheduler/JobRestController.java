package cn.wegostack.sundial.scheduler;

import cn.wegostack.sundial.scheduler.dal.dao.InstanceRepository;
import cn.wegostack.sundial.scheduler.dal.entity.InstanceDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhengjianglong
 * @since 2021-06-25
 */
@RestController
public class JobRestController {

    @Autowired
    private InstanceRepository instanceRepository;

    @GetMapping(path = "/test")
    public void addPerson() {
        InstanceDO instanceDO = new InstanceDO();
        instanceDO.setAppName("test");
        instanceDO.setIp("111");
        instanceRepository.save(instanceDO);
    }
}
