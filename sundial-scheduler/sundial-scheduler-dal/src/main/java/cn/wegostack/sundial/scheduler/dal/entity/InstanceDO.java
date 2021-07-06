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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_instance")
public class InstanceDO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 15)
    private String ip;

    private String hostname;

    private String appName;

    /**
     * instance status:
     * <b>RUNNING<b/>
     * <b>DOWN<b/>
     */
    @Column(length = 10)
    private String status;

    private Date heartbeat;

    private String region;

    private String dataCenter;

    private String cell;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Date createTime;

    @LastModifiedDate
    @Column(nullable = false)
    private Date modifiedTime;
}
