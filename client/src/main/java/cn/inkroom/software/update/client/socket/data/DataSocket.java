package cn.inkroom.software.update.client.socket.data;

import cn.inkroom.software.update.client.socket.Socket;
import cn.inkroom.software.update.client.socket.listener.FileTransferListener;
import cn.inkroom.software.update.common.message.data.FileTransfer;
import cn.inkroom.software.update.common.message.reader.MessageReader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class DataSocket extends Socket {

    private FileTransferListener transferListener;

    private boolean isFile = true;
    private String path;
    private String app;
    private int version;
    private FileTransfer transfer = new FileTransfer();

    private MessageReader reader;

    public DataSocket(int port, String host, FileTransferListener transferListener, boolean isFile, String path, String app, int version, FileTransfer transfer) {
        super(port, host);
        this.transferListener = transferListener;
        this.isFile = isFile;
        this.path = path;
        this.app = app;
        this.version = version;
        this.transfer = transfer;
    }

    public void setReader(MessageReader reader) {
        this.reader = reader;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public void setFile(boolean file) {
        isFile = file;
    }

    public void setTransferListener(FileTransferListener transferListener) {
        this.transferListener = transferListener;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setTransfer(FileTransfer transfer) {
        this.transfer = transfer;
    }

    public DataSocket(int port, String host) {
        super(port, host);
    }

    @Override
    protected void handKey(SelectionKey key) {
        try {
            if (key.isConnectable()) {
                SocketChannel channel = (SocketChannel) key.channel();
                if (channel.isConnectionPending()) {
                    channel.finishConnect();
                    ByteBuffer byteBuffer = ByteBuffer.wrap("客户端消息".getBytes());
                    channel.write(byteBuffer);
                    channel.register(key.selector(), SelectionKey.OP_READ);
                }
            } else if (key.isReadable()) {
                SocketChannel channel = (SocketChannel) key.channel();
                ByteBuffer countBuffer = ByteBuffer.allocate(1);
                if (channel.read(countBuffer) != -1) {


                    int length = Byte.valueOf(countBuffer.get(0)).intValue();
                    //读取头部信息
                    ByteBuffer headBuffer = ByteBuffer.allocate(length);
                    if (channel.read(headBuffer) != -1) {
                        String head = new String(headBuffer.array());
                        System.out.println("数据接收头=" + head);
                        if (reader.readMessage(headBuffer.array())) {
                            if (!reader.getApp().equals(app) || reader.getVersion() != version) {
                                channel.close();
                                key.cancel();
                                key.selector().close();
                                return;
                            }
                        }
                    }
                    //读取文件头
                    hand(channel, null);
                } else {
                    channel.close();
                    key.cancel();
                    key.selector().close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hand(SocketChannel channel, ByteBuffer buffer) throws IOException {
        if (isFile)
            transfer.toLocalFile(channel, path);
        else transfer.toLocalDir(channel, path);
        channel.close();
        new Thread(() -> transferListener.hand(app, path)).start();
    }
}
