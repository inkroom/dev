package cn.inkroom.software.update.server.socket.data;

import cn.inkroom.software.update.common.message.data.FileTransfer;

import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DefaultDataSocketClient implements DataSocketClient {

    private List<SocketChannel> list;
    private FileTransfer transfer;

    public void setTransfer(FileTransfer transfer) {
        this.transfer = transfer;
    }

    public DefaultDataSocketClient() {
        list = new LinkedList<>();
    }

    @Override
    public int size() {
        return list.size();
    }


    @Override
    public void addClient(SocketChannel key) throws IOException {
        list.add(key);
    }

    @Override
    public void removeClient(SocketChannel key) throws IOException {
        list.remove(key);
    }

    @Override
    public String writeFile(SocketChannel key, String file, boolean isFile) throws IOException {
        if (isFile) {
            transfer.toSocketFile(key, file);
        } else {
            // TODO: 18-5-15 文件夹待完成
        }
        return null;
    }

    @Override
    public List<String> broadFile(String file, boolean isFile) throws IOException {
        List<String> ips = new ArrayList<>(list.size());
        for (SocketChannel channel : list) {
            if (isFile) transfer.toSocketFile(channel, file);
            try {
                ips.add(channel.getRemoteAddress().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ips;
    }
}
