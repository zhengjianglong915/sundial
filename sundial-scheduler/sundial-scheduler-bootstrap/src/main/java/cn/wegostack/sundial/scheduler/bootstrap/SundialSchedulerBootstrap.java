package cn.wegostack.sundial.scheduler.bootstrap;

import cn.wegostack.sundial.common.utils.HostUtils;
import cn.wegostack.sundial.common.utils.LocalServer;
import cn.wegostack.sundial.scheduler.core.cluster.SundialCluster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Closeable;
import java.io.IOException;

/**
 * @author zhengjianglong
 * @since 2021-07-10
 */
@Component
public class SundialSchedulerBootstrap implements Closeable {

    @Autowired
    private SundialCluster sundialCluster;

    @PostConstruct
    public void start() {
        // set local info
        LocalServer.setIp(HostUtils.getHostIp());
        String cluster = System.getProperty("sundial.scheduler.cluster", "default");
        LocalServer.setCluster(cluster);

        // join cluster of scheduler
        sundialCluster.join(cluster);


        LocalServer.setIsReady(true);
    }

    @Override
    public void close() throws IOException {
    }
}
