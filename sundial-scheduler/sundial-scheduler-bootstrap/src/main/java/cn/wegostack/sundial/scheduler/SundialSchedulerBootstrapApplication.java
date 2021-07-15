package cn.wegostack.sundial.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.wegostack.sundial")
public class SundialSchedulerBootstrapApplication {
    private static Logger LOGGER = LoggerFactory.getLogger(SundialSchedulerBootstrapApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(SundialSchedulerBootstrapApplication.class, args);
            LOGGER.info("sundial scheduler started.");

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
            LOGGER.error("sundial scheduler start exception", e);
        }
    }

}
