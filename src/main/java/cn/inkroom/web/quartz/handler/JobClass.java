package cn.inkroom.web.quartz.handler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/11/10
 * @Time 10:56
 * @Descorption
 */
public class JobClass extends QuartzJobBean {
    Logger log = LoggerFactory.getLogger(getClass());

    @Resource(name="s")
    private String s;

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("这是测试" + getS());
//        try {
//            log.info(jobExecutionContext.getRecoveringTriggerKey().getGroup() + "   " + jobExecutionContext.getRecoveringTriggerKey().getName());
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
