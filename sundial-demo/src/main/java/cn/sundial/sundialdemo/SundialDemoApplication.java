package cn.sundial.sundialdemo;

import cn.wegostack.sundial.worker.server.WorkerServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SundialDemoApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(SundialDemoApplication.class, args);

        String appName = context.getEnvironment().getProperty("spring.application.name");
        System.getProperties().put("spring.application.name", appName);
        WorkerServer server = new WorkerServer();
        server.start();
        server.blockUntilShutdown();
    }

}
