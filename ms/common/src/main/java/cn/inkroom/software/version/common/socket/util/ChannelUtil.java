package cn.inkroom.software.version.common.socket.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;

public class ChannelUtil {
    public byte[] getMessage(SocketChannel channel, Byte b) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 10);
        int count = -1;
        LinkedList<Byte> byteLinkedList = new LinkedList<>();
        while ((count = channel.read(buffer)) != -1) {
            byte bytes[] = buffer.array();
            for (byte b1 : bytes) {
                byteLinkedList.add(b1);
            }
            buffer.clear();
        }
        if (count <= 0 && byteLinkedList.isEmpty()) {
            return new byte[0];
        }
        byte bytes[] = null;
        if (b != null) {
            bytes = new byte[byteLinkedList.size() + 1];
            bytes[0] = b;
            for (int i = 1; i < bytes.length; i++) {
                bytes[i] = byteLinkedList.get(i - 1);
            }
        } else {
            bytes = new byte[byteLinkedList.size()];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = byteLinkedList.get(i - 1);
            }
        }
        return bytes;
    }

    public byte[] getMessage(SocketChannel channel) throws IOException {
        return getMessage(channel, null);
    }
}
