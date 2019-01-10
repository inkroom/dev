package cn.inkroom.software.version.client.socket;

import cn.inkroom.software.version.client.proxy.AppProxy;
import cn.inkroom.software.version.common.app.Application;
import cn.inkroom.software.version.common.enums.MessageType;
import cn.inkroom.software.version.common.file.FileTransfer;
import cn.inkroom.software.version.common.reader.DomXmlReader;
import cn.inkroom.software.version.common.reader.MessageReader;
import cn.inkroom.software.version.common.socket.client.ClientSocket;
import cn.inkroom.software.version.common.socket.listener.FileTransferListener;
import cn.inkroom.software.version.common.socket.listener.MessageListener;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class DataSocket extends ClientSocket implements MessageListener {

    private AppProxy proxy;

    private FileTransfer transfer;

    private MessageReader reader;

    private FileTransferListener transferListener;

    public DataSocket(int port, String host, AppProxy proxy) {
        super(port, host);
        reader = new DomXmlReader();
        this.proxy = proxy;
        transfer = new FileTransfer();
    }

    public DataSocket(int port, String host, AppProxy proxy, MessageReader reader) {
        super(port, host);
        this.proxy = proxy;
        this.reader = reader;
        transfer = new FileTransfer();
    }

    public DataSocket(int port, String host, AppProxy proxy, FileTransfer transfer, MessageReader reader) {
        super(port, host);
        this.proxy = proxy;
        this.transfer = transfer;
        this.reader = reader;
    }

    public void setTransferListener(FileTransferListener transferListener) {
        this.transferListener = transferListener;
    }

    @Override
    public void setListener(MessageListener listener) {
        throw new RuntimeException("数据传输socket不允许自定义监听器");
    }

    @Override
    public void hand(SelectionKey key, byte b) {

        try {
            //获取头部文本信息长度
            int count = Byte.valueOf(b).intValue();
            //读取头部消息
            SocketChannel channel = (SocketChannel) key.channel();
            ByteBuffer headBuffer = ByteBuffer.allocate(count);
            if (channel.read(headBuffer) == count) {

                //解析头部消息
                if (reader.read(headBuffer.array())) {
                    if (reader.getType() == MessageType.DOWNLOAD && reader.getApp().equals(proxy.getApp()) && reader.getVersion() > proxy.getVersion()) {
                        Application application = proxy.getUpdateApp();
                        logger.debug("开始下载，存储路径：" + application.getPath());
                        application.setVersion(reader.getVersion());
                        if (application.isFile()) {
                            transfer.toLocalFile(channel, application.getPath());
                            logger.debug("文件下载完成");
                        } else {
                            // TODO: 18-5-18 文件夹下载待完成
                            transfer.toLocalDir(channel, application.getPath());
                            logger.debug("文件夹下载完成");
                        }
                        //通知文件下载完成监听器
                        if (transferListener != null) {
                            transferListener.hand(application);
                        }
                    } else {
                        logger.warn("接收到不应该下载的项目数据包");
                    }
                } else {
                    logger.error("数据socket收到错误的消息");
                    close(key, channel);
                }
            } else {
                logger.error("数据socket收到错误的消息");
                close(key, channel);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close(SelectionKey key, SocketChannel channel) throws IOException {
        key.cancel();
        channel.close();
        stopThread();
    }
}
