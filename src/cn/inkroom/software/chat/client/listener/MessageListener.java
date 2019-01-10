package cn.inkroom.software.chat.client.listener;

/**
 * Created by 墨盒 on 16:44.
 * 消息监听器，负责与消息有关的事件
 */
public interface MessageListener {
    /**
     * 接收到消息
     *
     * @param value 消息，
     */
    public void message(String value);

    /**
     * 消息发送失败
     *
     * @param value
     */
    public void sendFail(String value);

    public void sendSuccess(String value);

    /**
     * 网络状况不好，连接中断
     *
     * @param e
     */
    public void disconnect(Throwable e);

}
