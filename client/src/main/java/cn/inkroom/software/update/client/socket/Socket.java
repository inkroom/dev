package cn.inkroom.software.update.client.socket;

import cn.inkroom.software.update.client.socket.listener.MessageEventListener;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public abstract class Socket implements Runnable, MessageEventListener {
    private int port;
    private String host;
    private Selector selector;
    private SocketChannel channel;
    private MessageEventListener listener;
    private Boolean isContinue;

    public void setListener(MessageEventListener listener) {
        this.listener = listener;
    }

    public Socket(int port, String host) {
        this.isContinue = Boolean.TRUE;
        this.port = port;
        this.host = host;
        try {
            selector = Selector.open();
            channel = SocketChannel.open();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_CONNECT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startThread() throws IOException {
        channel.connect(new InetSocketAddress(this.host, this.port));
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (isContinue) {
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
    }

    public void stop() {
        synchronized (isContinue) {
            isContinue = Boolean.FALSE;
        }
    }

    protected void handKey(SelectionKey key) {
        try {
            if (key.isConnectable()) {
                SocketChannel channel = (SocketChannel) key.channel();
                if (channel.isConnectionPending()) {
                    channel.finishConnect();
                    ByteBuffer byteBuffer = ByteBuffer.wrap("客户端消息".getBytes());
                    channel.write(byteBuffer);
                    channel.register(selector, SelectionKey.OP_READ);
                }
            } else if (key.isReadable()) {//接收来自服务器的消息
                SocketChannel channel = (SocketChannel) key.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                int count = channel.read(byteBuffer);
                if (count != -1) {
                    listener.hand(channel,byteBuffer);
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



}
