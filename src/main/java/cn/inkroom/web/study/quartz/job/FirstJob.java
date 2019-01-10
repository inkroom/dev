package cn.inkroom.web.study.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/8
 * @Time 10:51
 * @Descorption
 */
public class FirstJob implements Job {
    Logger log = LoggerFactory.getLogger(getClass());

    public void execute(JobExecutionContext context) throws JobExecutionException {
//        context.getTrigger().
        log.info(hashCode()+"   第一个quartz任务"+context.getJobDetail().getJobDataMap().getString("key")+"   "+context.getTrigger().getStartTime()+"   "+context.getTrigger().getEndTime());
        log.debug("测试debug");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(hashCode()+"  ");
    }
}
