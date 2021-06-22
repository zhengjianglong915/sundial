package cn.wegostack.sundial.scheduler.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SundialSchedulerBootstrapApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(SundialSchedulerBootstrapApplication.class, args);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
