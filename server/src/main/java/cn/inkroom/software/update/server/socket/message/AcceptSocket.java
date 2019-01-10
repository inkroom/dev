package cn.inkroom.software.update.server.socket.message;

import cn.inkroom.software.update.common.message.reader.MessageReader;
import cn.inkroom.software.update.common.message.reader.XmlMessageReader;
import cn.inkroom.software.update.server.event.DefaultMessageEventListener;
import cn.inkroom.software.update.server.event.MessageEventListener;
import cn.inkroom.software.update.server.event.MessageEventKey;

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
 * 用于接收socket连接
 */
public class AcceptSocket implements Runnable {

    private int port;


    private Selector selector;
    private MessageSocketClient client;
    private ServerSocketChannel serverSocket;
    private MessageEventListener event = new DefaultMessageEventListener();
    private MessageReader reader;
    private Boolean acceptable = Boolean.TRUE;

    public AcceptSocket(int port) {
        this(port, new DefaultMessageSocketClient());
    }

    public AcceptSocket(int port, MessageSocketClient client) {
        this.port = port;
        this.client = client;
        reader = new XmlMessageReader();
    }

    public void setListener(MessageEventListener event) {
        this.event = event;
    }

    public void startThread() throws IOException {

        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);


        Thread thread = new Thread(this);
        thread.start();

    }

    /**
     * 待测试
     */
    public void stop() {
        client.closeAll();
        try {
            serverSocket.close();
            selector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized (acceptable) {
            acceptable = Boolean.FALSE;
        }
    }

    @Override
    public void run() {
        synchronized (acceptable) {
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
        }
    }

    /**
     * 处理消息
     *
     * @param key
     */
    protected void handKey(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            firstConnect(key);
            System.out.println("第一次连接");
        } else if (key.isReadable()) {

            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = channel.read(byteBuffer);
            if (count != -1) {
                String message = new String(byteBuffer.array(), 0, count);
                System.out.println("服务器端收到的消息" + message);
                if (key.attachment().equals("first") && client.checkFirstMessage(message)) {//这时候再将socket加入队列
                    client.addClient(channel);
                    System.out.println("加入队里" + client.size());
                    key.attach("not_first");
                } else if (key.attachment().equals("not_first")) {//读取消息
                    System.out.println("普通读取客户端消息");
                    byte bytes[] = new byte[byteBuffer.position()];
                    for (int i = 0; i < bytes.length; i++) {
                        bytes[i] = byteBuffer.get(i);
                    }
                    if (reader.readMessage(bytes)) {
                        //处理消息
                        new Thread(() -> event.hand(createKey(key, channel, reader))).start();
                    }
                } else {
                    key.cancel();
                    channel.close();
                }
                channel.register(selector, SelectionKey.OP_READ, "not_first");
            } else {//连接断开
                System.out.println("连接断开" + client.size());
                key.cancel();
                channel.close();
                client.removeClient(channel);
            }
        }
    }

    /**
     * 连接事件
     *
     * @param key
     */
    private void firstConnect(SelectionKey key) throws IOException {
        SocketChannel channel = serverSocket.accept();
        channel.configureBlocking(false);
        channel.register(selector, SelectionKey.OP_READ, "first");
    }

    private MessageEventKey createKey(SelectionKey key, SocketChannel channel, MessageReader reader) {
        return new MessageEventKey(channel, key, reader);
    }

    /**
     * 广播版本更新消息
     *
     * @param message 消息
     * @return 多少个收到小
     * @throws IOException
     */
    public List<String> broadVersion(String message) throws IOException {

        return client.broadMessage(message);
    }

}
