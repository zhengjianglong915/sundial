package cn.wegostack.sundial.scheduler.dal.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author zhengjianglong
 * @since 2021-07-04
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_job")
public class JobDO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String appName;

    @Column(length = 15)
    private String triggerType;

    @Column(length = 50)
    private String triggerExp;
    
}
