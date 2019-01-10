package cn.inkroom.web.study.quartz.scheduler;

import cn.inkroom.web.study.quartz.job.FirstJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/8
 * @Time 10:57
 * @Descorption
 */
public class FirstScheduler {
    public static final Logger log = LoggerFactory.getLogger(FirstScheduler.class);

    public static void main(String[] args) {
        try {


            JobDetail detail = JobBuilder.newJob(FirstJob.class)
                    .withIdentity("myJob", "group1")
                    .build();

            detail.getJobDataMap().put("key", "来自detail");

            Trigger trigger = TriggerBuilder.newTrigger()
//                    .withIdentity("myTrigger", "group1")
//                    .startAt(new Date(Calendar.getInstance().getTimeInMillis() + 3000))
//                    .endAt(new Date(Calendar.getInstance().getTimeInMillis() + 6000))
                    .withSchedule(
                            CronScheduleBuilder.cronSchedule("* * * * * ?")
//                            SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY)
                    )
                    .build();

            SchedulerFactory factory = new StdSchedulerFactory();

            Scheduler scheduler = factory.getScheduler();

            log.info("所有的 scheduler" +factory.getAllSchedulers());

            scheduler.scheduleJob(detail, trigger);

            scheduler.start();

            log.info("开始执行   detail name = " + detail.getKey().getName() + "  " + detail.getKey().getGroup() + "   " + detail.getJobClass().getName());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("挂起");
            scheduler.standby();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("关闭");
            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
