package cn.inkroom.software.update.server.event;

import cn.inkroom.software.update.common.message.reader.MessageReader;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * 事件传递参数
 */
public class MessageEventKey {

    private SocketChannel channel;
    private SelectionKey key;
    private MessageReader messageReader;

    public MessageEventKey(SocketChannel channel, SelectionKey key, MessageReader messageReader) {
        this.channel = channel;
        this.key = key;
        this.messageReader = messageReader;
    }


    public SocketChannel getChannel() {
        return channel;
    }

    public SelectionKey getKey() {
        return key;
    }

    public MessageReader getMessageReader() {
        return messageReader;
    }
}
