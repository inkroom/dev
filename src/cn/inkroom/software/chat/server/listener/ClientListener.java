package cn.inkroom.software.chat.server.listener;

import java.net.Socket;

/**
 * Created by 墨盒 on 17:04.
 * 客户端监听器
 */
public interface ClientListener {
    /**
     * 新用户连接
     *
     * @param ip
     */
    public void connect(String ip);

    /**
     * 断开连接
     *
     * @param ip
     */
    public void disconnect(String ip);

    /**
     * 客户端发来消息
     *
     * @param value
     */
    public void message(String value);
}
