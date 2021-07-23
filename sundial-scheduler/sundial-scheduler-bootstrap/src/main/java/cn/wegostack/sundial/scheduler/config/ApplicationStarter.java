package cn.wegostack.sundial.scheduler.config;

import cn.wegostack.sundial.discovery.registry.SundialRegistryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhengjianglong
 * @since 2021-07-04
 */
@Component
public class ApplicationStarter {

    @Autowired
    private SundialRegistryServer server;

    @PostConstruct
    public void init() {
        try {
            // registry
            server.start();
            server.blockUntilShutdown();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
