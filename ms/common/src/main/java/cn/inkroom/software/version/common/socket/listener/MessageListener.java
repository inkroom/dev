package cn.inkroom.software.version.common.socket.listener;

import java.nio.channels.SelectionKey;

public interface MessageListener {
    /**
     * 处理消息请求
     * @param key 请求
     * @param b 已被读取的字节
     */
    void hand(SelectionKey key, byte b);
}
