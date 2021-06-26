package cn.wegostack.sundial.scheduler.server.sundial.scheduler.bootstrap;

import cn.wegostack.sundial.scheduler.core.scheduler.SundialScheduler;
import cn.wegostack.sundial.scheduler.core.trigger.timing.TimingTrigger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SundialSchedulerBootstrapApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SundialSchedulerBootstrapApplication.class, args);

            TimingTrigger trigger = new TimingTrigger();
            trigger.init();

            SundialScheduler sundialScheduler = new SundialScheduler();
            sundialScheduler.init();

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
