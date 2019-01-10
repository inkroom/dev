package cn.inkroom.web.study.message.producer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/10/31
 * @Time 16:58
 * @Descorption
 */
public class SpringProducerEntry {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:base.xml");
        ProducerService service = context.getBean(ProducerService.class);
        for (int i = 0; i < 100; i++) {
            service.sendMessage("来自spring-jms的消息" + (i + 1));
        }
        context.close();
    }
}
