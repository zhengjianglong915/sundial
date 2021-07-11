package cn.wegostack.sundial.scheduler.dal.entity;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author zhengjianglong
 * @since 2021-07-10
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_solt")
public class SoltDO {

    @Id
    @GeneratedValue
    private Long id;

    private String status;

    private Integer jobCount;

    private String loadServer;
}
