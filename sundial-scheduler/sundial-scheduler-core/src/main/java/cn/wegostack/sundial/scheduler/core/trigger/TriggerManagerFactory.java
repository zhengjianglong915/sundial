package cn.wegostack.sundial.scheduler.core.trigger;

import cn.wegostack.sundial.common.enums.TriggerType;
import cn.wegostack.sundial.scheduler.core.trigger.timing.TimingTriggerManager;

/**
 * @author zhengjianglong
 * @since 2021-07-12
 */
public class TriggerManagerFactory {

    private static ITriggerManager timingTriggerManager = new TimingTriggerManager();

    private static ITriggerManager quartzTriggerManager = new QuartzTriggerManager();

    /**
     * Get trigger
     *
     * @param type
     * @return
     */
    public static ITriggerManager getTriggerManager(TriggerType type) {
        switch (type) {
            case TIMING:
                return timingTriggerManager;
            case CRON:
            case PERIOD:
                return quartzTriggerManager;
        }
        return null;
    }
}
