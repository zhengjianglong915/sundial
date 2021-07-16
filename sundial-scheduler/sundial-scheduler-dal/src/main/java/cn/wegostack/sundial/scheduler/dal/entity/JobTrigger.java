package cn.wegostack.sundial.scheduler.dal.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author zhengjianglong
 * @since 2021-07-11
 */
@Data
@Entity
@Table(name = "t_job_trigger")
public class JobTrigger extends BaseEntity {

    @Column
    private String jobId;

    /**
     * The trigger was load in the cluster of scheduler server.
     */
    @Column(nullable = false)
    private String loadCluster;

    /**
     * The client cell will received the trigger event.
     */
    @Column
    private String triggerCell;

    /**
     * Is open or close
     */
    @Column
    private String status;

    @Column
    private String loadStatus;

    @Column
    private String loadServer;

    /**
     * use to sharding
     */
    @Column
    private Integer slot;

    @Column
    private Date loadTime;
}
