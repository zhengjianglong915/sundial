package cn.wegostack.sundial.scheduler.dal.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author zhengjianglong
 * @since 2021-07-04
 */
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = {"cn.wegostack.sundial.scheduler.dal.repository"})
@EntityScan(basePackages = {"cn.wegostack.sundial.scheduler.dal.entity"})
public class DaoConfiguration {
}
