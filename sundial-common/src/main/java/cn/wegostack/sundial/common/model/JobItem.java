package cn.wegostack.sundial.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhengjianglong
 * @since 2021-06-22
 */
@Data
@NoArgsConstructor
public class JobItem extends JobMeta {

    private String cluster;

    private String cell;

    private String jobId;

    private String status;

}
