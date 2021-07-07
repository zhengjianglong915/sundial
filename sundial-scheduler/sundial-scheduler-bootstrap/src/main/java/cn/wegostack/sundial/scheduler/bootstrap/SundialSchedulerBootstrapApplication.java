package cn.wegostack.sundial.scheduler.bootstrap;

import cn.wegostack.sundial.registry.client.SundialRegistry;
import cn.wegostack.sundial.registry.client.api.SubscribeDataListener;
import cn.wegostack.sundial.scheduler.core.discovery.SundialDiscovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.wegostack.sundial")
public class SundialSchedulerBootstrapApplication {

    private static SundialRegistry registry;

    public static void main(String[] args) {
        try {
            SpringApplication.run(SundialSchedulerBootstrapApplication.class, args);

//            registry = new SundialRegistry("localhost");
//            registry.init();
//
//            SubscribeDataListener listener = new SundialDiscovery();
//            registry.subscribe("sundial-demo", listener);

//            TimingTrigger trigger = new TimingTrigger();
//            trigger.init();
//
//            SundialScheduler sundialScheduler = new SundialScheduler();
//            sundialScheduler.init();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
