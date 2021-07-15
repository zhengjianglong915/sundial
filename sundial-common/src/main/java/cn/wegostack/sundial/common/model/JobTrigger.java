package cn.wegostack.sundial.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String status;

    private String loadStatus;

    private String loadTime;

    private String loadServer;

    private Integer slot;
}
