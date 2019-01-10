package cn.inkroom.software.update.client.socket.listener;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public interface MessageEventListener {
    void hand(SocketChannel channel, ByteBuffer buffer) throws IOException;
}
