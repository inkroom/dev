package cn.inkroom.software.update.server.socket.data;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.List;

public interface DataSocketClient {
    /**
     * 获取已连接的客户端个数
     *
     * @return
     */
    int size();


    /**
     * 添加客户端到队列
     *
     * @param key 客户端
     * @throws IOException
     */
    void addClient(SocketChannel key) throws IOException;

    void removeClient(SocketChannel key) throws IOException;


    /**
     * 写消息，需要等待对方返回消息
     *
     * @param key     客户端
     * @param message 消息
     * @return 对方的消息
     * @throws IOException 异常
     */
    String writeFile(SocketChannel key, String file, boolean isFile) throws IOException;

    /**
     * 广播消息
     *
     * @return 接收到消息的客户端ip
     * @throws IOException io异常
     */
    List<String> broadFile(String file, boolean isFile) throws IOException;

}
