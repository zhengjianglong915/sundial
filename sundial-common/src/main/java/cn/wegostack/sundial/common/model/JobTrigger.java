package cn.wegostack.sundial.common.model;

import cn.wegostack.sundial.common.enums.LoadStatus;
import cn.wegostack.sundial.common.enums.TriggerStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author zhengjianglong
 * @since 2021-06-22
 */
@Data
@NoArgsConstructor
public class JobTrigger {
    /**
     * jobId +  loadCluster + triggerCell
     */
    private String triggerId;

    private JobMeta jobMeta;

    private String loadCluster;

    private String triggerCell;

    private TriggerStatus status = TriggerStatus.CLOSED;

    private LoadStatus loadStatus = LoadStatus.INIT;

    private Date loadTime;

    private String loadServer;

    private Integer slot;
}
