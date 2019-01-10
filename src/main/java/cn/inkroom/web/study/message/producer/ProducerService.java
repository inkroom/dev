package cn.inkroom.web.study.message.producer;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/10/31
 * @Time 16:45
 * @Descorption
 */
public interface ProducerService {
    void sendMessage(String message);
}
