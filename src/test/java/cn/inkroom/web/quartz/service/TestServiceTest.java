package cn.inkroom.web.quartz.service;

import cn.inkroom.web.quartz.handler.JobClass;
import cn.inkroom.web.quartz.handler.SchedulerManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/10
 * @Time 11:08
 * @Descorption
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-*.xml"})
public class TestServiceTest {
//        @Autowired
//    private SchedulerFactoryBean factoryBean;
//    @Resource
//    private Scheduler scheduler;

    @Autowired
    private SchedulerManager manager;

    Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void test() {
        try {


            String firstName = "1231";
            String firstGroup = "4dfgdgd";

            manager.addJob(JobClass.class, firstName, firstGroup, "0/3 * * * * ?");

            log.info("跟新");
            manager.updateCron(firstName, firstGroup, "* * * * * ?");

//            JobDetail jobDetail = JobBuilder.newJob(JobClass.class)
//                    .withIdentity("testJobName", "testJobGroup").build();
////            jobDetail.getJobDataMap().put("scheduleJob", job);
//            //表达式调度构建器
//            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("* * * * * ?");
//
//            //按新的cronExpression表达式构建一个新的trigger
//            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("testJobName", "testJobGroup").withSchedule(scheduleBuilder).build();
//            factoryBean.getScheduler().scheduleJob(jobDetail, trigger);


//            JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
//            jobDetailFactoryBean.setJobClass(JobClass.class);
//            jobDetailFactoryBean.setDurability(true);
//            jobDetailFactoryBean.setGroup(firstGroup);
//            jobDetailFactoryBean.setName(firstName);
////            jobDetailFactoryBean.setApplicationContextJobDataKey("123");
////            jobDetailFactoryBean.setRequestsRecovery(true);
//            jobDetailFactoryBean.afterPropertiesSet();
////
//            log.info("JobDetail = " + jobDetailFactoryBean.getObject());
//
//            CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
////            cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
//            cronTriggerFactoryBean.setCronExpression("0/3 * * * * ? ");
//            cronTriggerFactoryBean.setName("trigger Name");
//
//            cronTriggerFactoryBean.afterPropertiesSet();
//            log.info("trigger == " + cronTriggerFactoryBean.getObject());
//
//            scheduler.scheduleJob(jobDetail, trigger);
////            scheduler.scheduleJob(jobDetailFactoryBean.getObject(),cronTriggerFactoryBean.getObject());
//            scheduler.start();


//            factoryBean.afterPropertiesSet();
////            factoryBean.getScheduler().addJob(jobDetailFactoryBean.getObject(),true);
//
//            factoryBean.getScheduler().scheduleJob(jobDetailFactoryBean.getObject(), cronTriggerFactoryBean.getObject());
//            factoryBean.getScheduler().start();
//
//            log.info("获取JobKey = "+JobKey.jobKey(firstName,firstGroup));
//            log.info("获取JobDetail = "+factoryBean.getScheduler().getJobDetail(JobKey.jobKey(firstName,firstGroup)));

//
            Thread.sleep(100000L);
//            log.info("挂起");
//            factoryBean.getScheduler().standby();
//            Thread.sleep(10000L);
//            log.info("重启");
//            factoryBean.getScheduler().start();
//
//            Thread.sleep(3000);
//            log.info("加入另一个任务");
//
//            jobDetailFactoryBean = new JobDetailFactoryBean();
//            jobDetailFactoryBean.setJobClass(JobClass.class);
//            jobDetailFactoryBean.setDurability(true);
//            jobDetailFactoryBean.setGroup("1231");
//            jobDetailFactoryBean.setName("4165f4g6dgr");
//            jobDetailFactoryBean.afterPropertiesSet();
//
//            cronTriggerFactoryBean = new CronTriggerFactoryBean();
//            cronTriggerFactoryBean.setName("seconded");
//            cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
//            cronTriggerFactoryBean.setCronExpression("0/2 * * * * ? ");
//            cronTriggerFactoryBean.afterPropertiesSet();
//
//            factoryBean.getScheduler().scheduleJob(cronTriggerFactoryBean.getObject());
//
//            log.info("关闭");
////            factoryBean.getScheduler().deleteJob()
//            factoryBean.getScheduler().shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}