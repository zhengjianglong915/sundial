package cn.wegostack.sundial.scheduler.server.sundial.scheduler.core.trigger.timing;

import cn.wegostack.sundial.common.model.JobItem;
import cn.wegostack.sundial.scheduler.core.scheduler.SundialScheduler;
import cn.wegostack.sundial.scheduler.core.trigger.timing.TimingTrigger;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author zhengjianglong
 * @since 2021-06-24
 */
public class TimingTriggerTest extends TestCase {

    @Test
    public void testSchedule() throws Exception {
        TimingTrigger trigger = new TimingTrigger();
        trigger.init();

        JobItem jobItem = new JobItem();
        jobItem.setJobId("JB-adaremwe");
        jobItem.setTriggerExp("2021-06-24 09:26:00");
        trigger.add(jobItem);

//        JobItem jobItem2 = new JobItem();
//        jobItem2.setJobId("JB-adaremwe22");
//        jobItem2.setTriggerExp("2021-06-24 09:28:00");
//        trigger.add(jobItem2);

        SundialScheduler scheduler = new SundialScheduler();
        scheduler.init();
        Thread.sleep(600000);
    }

}