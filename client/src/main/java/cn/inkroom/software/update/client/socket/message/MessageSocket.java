package cn.inkroom.software.update.client.socket.message;

import cn.inkroom.software.update.client.socket.Socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 数据传输socket
 */
public class MessageSocket extends Socket {


    public MessageSocket(int port, String host) {
        super(port, host);
//        setListener(this);
    }


    @Override
    public void hand(SocketChannel channel, ByteBuffer buffer) throws IOException {
    }
}
