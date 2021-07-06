package cn.wegostack.sundial.scheduler.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("cn.wegostack.sundial")
public class SundialSchedulerBootstrapApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SundialSchedulerBootstrapApplication.class, args);


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
