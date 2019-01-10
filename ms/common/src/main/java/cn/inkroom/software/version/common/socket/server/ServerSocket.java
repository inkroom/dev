package cn.inkroom.software.version.common.socket.server;

import cn.inkroom.software.version.common.socket.listener.MessageListener;
import cn.inkroom.software.version.common.socket.server.queue.SocketClientQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.List;

/**
 * 服务器socket基类
 */
public class ServerSocket extends Thread {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private int port;
    protected Selector selector;
    private ServerSocketChannel serverSocketChannel;

    private Boolean acceptable = true;//是否继续接收消息
    private MessageListener listener;

    private SocketClientQueue queue;

    public ServerSocket(int port) {
        this.port = port;
    }

    public void setListener(MessageListener listener) {
        this.listener = listener;
    }

    public void setQueue(SocketClientQueue queue) {
        this.queue = queue;
    }

    /**
     * 开启socket
     *
     * @throws IOException 异常
     */
    public void startThread() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        logger.debug("socket消息接收线程启动");

        this.setName("Thread-" + getClass().toString().substring(6));
        this.start();
    }

    public void stopThread() throws IOException {
        logger.debug("开始停止socket消息接收线程");
        synchronized (acceptable) {
            acceptable = false;
            selector.close();
            serverSocketChannel.close();
        }
    }

    @Override
    public void run() {
        while (acceptable) {
            try {
                selector.select();//阻塞

                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    handKey(key); // 当前线程内处理。（为了高效，一般会在另一个线程中处理此消息）
                    iterator.remove(); // 删除此消息
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.debug("线程" + Thread.currentThread().getName() + "已停止");
    }

    protected void handKey(final SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            SocketChannel channel = serverSocketChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
            if (queue != null) {//记录队列
                logger.debug("客户端" + getClientKey(channel) + "连接，已加入队列");
                queue.add(getClientKey(channel));
            } else {
                logger.debug("客户端" + getClientKey(channel) + "连接");
            }
        } else if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1);
            int count = channel.read(byteBuffer);
            if (count == -1 && queue != null) {
                channel.close();
                key.cancel();
                queue.remove(getClientKey(channel));
                logger.debug("客户端" + getClientKey(channel) + "已断开连接，已从队列中移除");
                return;
            } else {
                logger.debug("客户端" + getClientKey(channel) + "已断开连接");
            }
            channel.register(selector, SelectionKey.OP_READ);
            if (listener != null)
                new Thread(() -> listener.hand(key, byteBuffer.array()[0])).start();

        }
    }

    /**
     * 获取客户端唯一标志
     *
     * @param channel 客户端
     * @return 唯一标志
     * @throws IOException 异常
     */
    public String getClientKey(SocketChannel channel) throws IOException {
        return ((InetSocketAddress) channel.getRemoteAddress()).getAddress().getHostAddress();
    }

    /**
     * 获取所有客户端，如果没有记录客户端则返回null
     *
     * @return 集合或者null
     */
    public List<SocketChannel> getClient() {
        if (queue != null) return queue.list();
        return null;
    }
}
