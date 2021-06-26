package cn.wegostack.sundial.scheduler.server.sundial.worker.annotation;

import java.lang.annotation.*;

/**
 * @author zhengjianglong
 * @since 2021-06-26
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Scheduler {

    String value();

    String version();

}
