package cn.inkroom.web.quartz.bean;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/9
 * @Time 17:02
 * @Descorption
 */
public class FBean extends QuartzJobBean {
    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("来着第二种");
        System.out.println(11111);
    }
}
