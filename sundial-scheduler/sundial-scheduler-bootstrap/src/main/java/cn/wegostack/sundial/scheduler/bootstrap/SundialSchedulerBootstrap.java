package cn.wegostack.sundial.scheduler.bootstrap;

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
        String cluster = System.getProperty("sundial.scheduler.cluster", "default");
        sundialCluster.join(cluster);
    }

    @Override
    public void close() throws IOException {
    }
}
