package cn.inkroom.software.version.common.socket.client;

import cn.inkroom.software.version.common.socket.listener.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ClientSocket extends Thread {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    private SocketChannel channel;
    private Selector selector;
    private int port;
    private String host;

    private MessageListener listener;

    private Boolean connected = true;

    public ClientSocket(int port, String host) {
        this.port = port;
        this.host = host;
    }

    protected SocketChannel getChannel() {
        return channel;
    }

    public void setListener(MessageListener listener) {
        this.listener = listener;
    }

    public int getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

    public void startThread() throws IOException {
        selector = Selector.open();
        channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_CONNECT);
        channel.connect(new InetSocketAddress(getHost(), getPort()));

        setName("Thread-" + getClass().toString().substring(6));
        logger.debug("客户端socket线程启动");
        start();
    }

    public void stopThread() throws IOException {
        logger.debug("开始停止客户端socket线程");
        synchronized (connected) {
            connected = false;
            selector.close();
            channel.close();
            logger.debug("正在停止客户端socket线程");
        }
    }

    @Override
    public void run() {
        while (connected) {
            try {
                selector.select();
                Iterator<SelectionKey> key = selector.selectedKeys().iterator();
                while (key.hasNext()) {
                    handKey(key.next());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        logger.debug("客户端socket线程已结束");
    }

    protected void handKey(SelectionKey key) {
        try {
            if (key.isConnectable()) {
                SocketChannel channel = (SocketChannel) key.channel();
                if (channel.isConnectionPending()) {
                    channel.finishConnect();
//                    ByteBuffer byteBuffer = ByteBuffer.wrap("客户端消息".getBytes());
//                    channel.write(byteBuffer);
                    channel.register(selector, SelectionKey.OP_READ);
                }
            } else if (key.isReadable()) {//接收来自服务器的消息
                SocketChannel channel = (SocketChannel) key.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1);

                int count = channel.read(byteBuffer);
                if (count > 0) {
                    if (listener != null)
                        listener.hand(key, byteBuffer.array()[0]);
                    channel.register(selector, SelectionKey.OP_READ);
                } else {
                    key.cancel();
                    channel.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 往服务器端写数据
     *
     * @param message 消息
     * @throws IOException 异常
     */
    public void sendMessage(String message) throws IOException {
        logger.debug("发送消息：" + message);
        SocketChannel channel = getChannel();
        ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
        channel.write(byteBuffer);
    }

}
