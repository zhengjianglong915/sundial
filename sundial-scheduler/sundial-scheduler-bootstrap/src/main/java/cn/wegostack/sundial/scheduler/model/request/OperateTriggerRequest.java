package cn.wegostack.sundial.scheduler.model.request;

import java.io.Serializable;
import java.util.List;

public class OperateTriggerRequest implements Serializable {
    private String jobId;
    private String cluster;
    private List<String> triggerCell;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public List<String> getTriggerCell() {
        return triggerCell;
    }

    public void setTriggerCell(List<String> triggerCell) {
        this.triggerCell = triggerCell;
    }
}
