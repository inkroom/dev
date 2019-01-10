package cn.inkroom.software.update.client.core;

import cn.inkroom.software.update.client.socket.data.DataSocket;
import cn.inkroom.software.update.client.socket.message.MessageSocket;
import cn.inkroom.software.update.client.socket.listener.FileTransferListener;
import cn.inkroom.software.update.client.socket.listener.MessageEventListener;
import cn.inkroom.software.update.client.version.AppVersion;
import cn.inkroom.software.update.common.message.reader.MessageReader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Map;

/**
 * 核心控制器
 */
public class VersionController implements MessageEventListener, FileTransferListener {

    private MessageSocket socket;
    //key appName，value 对应的路径
    private Map<String, AppVersion> version;

    private MessageReader reader;

    private String host;

    public void setReader(MessageReader reader) {
        this.reader = reader;
    }


    public VersionController(int port, String host, Map<String, AppVersion> version) {
        this.socket = new MessageSocket(port, host);
        this.version = version;
        this.host = host;
        this.socket.setListener(this);
        try {
            this.socket.startThread();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hand(SocketChannel channel, ByteBuffer buffer) throws IOException {

        System.out.println(buffer.position());
        System.out.println("收到消息" + new String(buffer.array(), 0, buffer.position()));
        byte bytes[] = new byte[buffer.position()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = buffer.get(i);
        }
        if (reader.readMessage(bytes)) {
            switch (reader.getType()) {
                case CREATE:
                    break;
                case UPDATE://版本更新,创建数据接收socket
                    AppVersion v = version.get(reader.getApp());
                    DataSocket dataSocket = new DataSocket(reader.getPort(), this.host);
                    dataSocket.setTransferListener(this);
                    dataSocket.setApp(v.getApp());
                    dataSocket.setFile(v.isFile());
                    dataSocket.setPath(v.getBasePath());
                    dataSocket.setVersion(v.getVersion());
                    dataSocket.startThread();
                    break;
            }
        }
    }

    @Override
    public void hand(String app, String file) {
        if (version.get(app).update(file)) {

        } else {
            // TODO: 18-5-15 待消息回滚
            version.get(app).getMessage();
        }
    }
}
