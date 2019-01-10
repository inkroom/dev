package cn.inkroom.software.update.server.socket.message;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * 接收消息的socket客户端，用于处理消息
 * <p>同时维护一个client队列</p>
 */
public interface MessageSocketClient {
    /**
     * 获取已连接的客户端个数
     *
     * @return
     */
    int size();

    /**
     * 判断第一次连接时的消息是否合法，防止其余socket程序连接
     *
     * @param message 消息
     * @return
     */
    boolean checkFirstMessage(String message);

    /**
     * 添加客户端到队列
     *
     * @param key 客户端
     * @throws IOException
     */
    void addClient(SocketChannel key) throws IOException;

    void removeClient(SocketChannel key) throws IOException;

    String readMessage(SocketChannel key) throws IOException;

    /**
     * 写消息，需要等待对方返回消息
     *
     * @param key     客户端
     * @param message 消息
     * @return 对方的消息
     * @throws IOException 异常
     */
    String writeMessage(SocketChannel key, String message) throws IOException;

    /**
     * 广播消息
     *
     * @param message 消息
     * @return 接收到消息的客户端ip
     * @throws IOException io异常
     */
    List<String> broadMessage(String message) throws IOException;

    int closeAll();
}
