package cn.inkroom.web.study.message.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/10/31
 * @Time 17:15
 * @Descorption
 */
public class SpringConsumerEntry {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:consumer.xml");




    }
}
