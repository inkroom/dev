package cn.inkroom.software.update.server.socket.data;

import cn.inkroom.software.update.common.message.data.FileTransfer;
import cn.inkroom.software.update.common.message.reader.MessageReader;
import cn.inkroom.software.update.common.message.reader.XmlMessageReader;
import cn.inkroom.software.update.common.message.writer.MessageWriter;
import cn.inkroom.software.update.common.message.writer.XmlMessageWriter;
import cn.inkroom.software.update.server.socket.message.AcceptSocket;
import cn.inkroom.software.update.server.version.map.DirectoryVersion;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;

public class DataSocket extends AcceptSocket {
    private DataSocketClient client = new DefaultDataSocketClient();
    private MessageReader reader = new XmlMessageReader();
    private MessageWriter writer = new XmlMessageWriter();
    private List<DirectoryVersion> versions;

    private FileTransfer transfer;

    // TODO: 18-5-15 流程需要修改
    /*

    服务器端数据传输socket应该一直开着

    服务器端放客户端广播项目更新
    客户端接收到消息，连接服务器数据传输socket，下载对应项目
    服务器端数据socket不记录客户端


     */

    public DataSocket(int port) {
        super(port);
    }

    public DataSocket(int port, List<DirectoryVersion> versions) {
        super(port);
        transfer = new FileTransfer();
        this.versions = versions;
    }

    public void setTransfer(FileTransfer transfer) {
        this.transfer = transfer;
    }

    public void setVersions(List<DirectoryVersion> versions) {
        this.versions = versions;
    }

    public void setWriter(MessageWriter writer) {
        this.writer = writer;
    }

    public void setReader(MessageReader reader) {
        this.reader = reader;
    }

    public void setClient(DataSocketClient client) {
        this.client = client;
    }

    @Override
    protected void handKey(SelectionKey key) throws IOException {

        if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            //获取需要的版本
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = 0;
            if ((count = channel.read(buffer)) != -1) {
                byte bytes[] = new byte[count];
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = buffer.get(i);
                }
                if (reader.readMessage(bytes)) {
                    String app = reader.getApp();
                    int version = reader.getVersion();
                    for (DirectoryVersion v : versions) {
                        if (v.isOutOfData()) {
                            //写入文件
                            new Thread(() -> {
                                try {
                                    //首先写入头部信息
                                    String head = writer.writeApp(app).writeVersion(v.getVersion()).toString();
                                    ByteBuffer headBuffer = ByteBuffer.allocate(1 + head.length());
                                    headBuffer.put(Integer.valueOf(head.length()).byteValue());

                                    headBuffer.put(head.getBytes());
                                    channel.write(headBuffer);
                                    //写入文件信息
                                    transfer.toSocketFile(channel, v.getBasePath());
                                    channel.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }).start();
                        }
                    }
                }
            }
        }
//
//        if (key.isConnectable()) {
//            SocketChannel channel = (SocketChannel) key.channel();
//            channel.configureBlocking(false);
//            channel.register(key.selector(), SelectionKey.OP_READ, "first");
//        } else if (key.isReadable()) {
//            SocketChannel channel = (SocketChannel) key.channel();
//            channel.configureBlocking(false);
//            client.addClient(channel);
//            for (DirectoryVersion v : versions) {
//                if (v.updateModify()) {
//                    //开始传输文件
//                    new Thread(() -> {
//                        try {
//                            //写头部，最开始4个字节（即一个int），用于存储头部信息长度，头部信息结束后为文件信息
//                            String head = writer.writeVersion(v.getVersion()).writeApp(v.getApp()).toString();
//                            ByteBuffer buffer = ByteBuffer.allocate(head.length() + 1);
//                            buffer.put(Integer.valueOf(head.length()).byteValue());
//                            buffer.put(head.getBytes());
//                            channel.write(buffer);
//                            //写入文件信息
//                            client.writeFile(channel, v.getBasePath(), true);
//                            channel.close();
//                            client.removeClient(channel);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }).start();
//                }
//            }
//        }
    }
}
