package cn.wegostack.sundial.scheduler.dal.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhengjianglong
 * @since 2021-07-04
 */
@Data
@Entity
@Table(name = "t_job")
public class JobDO extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 20)
    private String jobId;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String appName;

    @Column(length = 15)
    private String triggerType;

    @Column(length = 50)
    private String triggerExp;

    @Column
    private Integer slot;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Date createTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date modifyTime;
}
