package cn.inkroom.software.update.server.socket.message;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class DefaultMessageSocketClient implements MessageSocketClient {

    private LinkedList<SocketChannel> clients;

    public DefaultMessageSocketClient() {
        clients = new LinkedList<>();
    }

    @Override
    public int size() {
        return clients.size();
    }

    @Override
    public boolean checkFirstMessage(String message) {
        return true;
    }

    @Override
    public void addClient(SocketChannel key) throws IOException {
        clients.add(key);
    }

    @Override
    public void removeClient(SocketChannel key) throws IOException {
        clients.add(key);
    }

    @Override
    public String readMessage(SocketChannel key) throws IOException {
//        if (key.isReadable()) {
//
//            SocketChannel channel = (SocketChannel) key.channel();
//
//        }
        return null;
    }

    @Override
    public String writeMessage(SocketChannel channel, String message) throws IOException {
//        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer charBuffer = ByteBuffer.wrap(message.getBytes());
        System.out.println("写入地址=" + channel.write(charBuffer));

        return null;
    }

    @Override
    public List<String> broadMessage(String message) throws IOException {
        System.out.println("客户端个数=" + clients.size());
        List<String> ips = new ArrayList<>(clients.size());
        for (SocketChannel k : clients) {
            System.out.println("k" + k + "  发送消息 " + message);
            writeMessage(k, message);
            ips.add(k.getRemoteAddress().toString());
        }
        return ips;
    }

    @Override
    public int closeAll() {
        int size = clients.size();
        clients.forEach(channel -> {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return size;
    }
}
