package cn.inkroom.software.version.common.socket.server.queue;

import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * socket客户端记录
 */
public interface SocketClientQueue {
    /**
     * 添加客户端
     * @param address 地址
     */
    void add(String address);

    /**
     * 个数
     * @return 客户端个数
     */
    int size();

    /**
     * 获取SocketChanel
     * @param address 地址
     */
    SocketChannel get(String address);

    /**
     * 删除socketChanel
     * @param address 地址
     */
    void remove(String address);

    /**
     * 获取所有socket
     * @return 集合
     */
    List<SocketChannel> list();
}
