package cn.inkroom.web.study.message.consumer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/10/31
 * @Time 17:09
 * @Descorption 消费者消息监听器
 */
public class ConsumerListener implements MessageListener {
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("接收到消息：" + textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
