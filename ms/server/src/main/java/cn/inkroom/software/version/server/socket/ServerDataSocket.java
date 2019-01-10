package cn.inkroom.software.version.server.socket;

import cn.inkroom.software.version.common.app.AppList;
import cn.inkroom.software.version.common.app.Application;
import cn.inkroom.software.version.common.enums.MessageType;
import cn.inkroom.software.version.common.file.FileTransfer;
import cn.inkroom.software.version.common.reader.DomXmlReader;
import cn.inkroom.software.version.common.reader.MessageReader;
import cn.inkroom.software.version.common.socket.listener.FileTransferListener;
import cn.inkroom.software.version.common.socket.server.ServerSocket;
import cn.inkroom.software.version.common.socket.util.ChannelUtil;
import cn.inkroom.software.version.common.writer.DomXmlWriter;
import cn.inkroom.software.version.common.writer.MessageWriter;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * 数据传输socket
 */
public class ServerDataSocket extends ServerSocket {


    private MessageReader reader;
    private MessageWriter writer;

    private FileTransferListener fileTransferListener;
    private FileTransfer transfer;
    private ChannelUtil channelUtil;

    private AppList apps;

    public void setFileTransferListener(FileTransferListener fileTransferListener) {
        this.fileTransferListener = fileTransferListener;
    }

    public void setChannelUtil(ChannelUtil channelUtil) {
        this.channelUtil = channelUtil;
    }

    public void setReader(MessageReader reader) {
        this.reader = reader;
    }

    public void setWriter(MessageWriter writer) {
        this.writer = writer;
    }

    public void setTransfer(FileTransfer transfer) {
        this.transfer = transfer;
    }

    public ServerDataSocket(int port, AppList appList) {
        super(port);
        this.apps = appList;
        reader = new DomXmlReader();
        writer = new DomXmlWriter();
        channelUtil = new ChannelUtil();
        transfer = new FileTransfer();
    }

    @Override
    protected void handKey(SelectionKey key) throws IOException {
        if (key.isReadable()) {

            SocketChannel channel = (SocketChannel) key.channel();
            byte bytes[] = channelUtil.getMessage(channel);
            if (reader.read(bytes)) {
                if (reader.getType() == MessageType.DOWNLOAD_ING) {
                    //获取对应的app
                    Application application = apps.getApp(reader.getApp());
                    if (application == null) {
                        logger.warn("不存在名为{}的web应用", reader.getApp());
                        return;
                    }
                    new Thread(() -> {
                        //发送对应的文件
                        try {

                            //写入消息头部
                            writer.clear().writeType(MessageType.DOWNLOAD).writeApp(application.getName()).writeVersion(application.getVersion());

                            String message = writer.getMessage();

                            ByteBuffer buffer = ByteBuffer.allocate(1 + message.length());
                            buffer.put(Integer.valueOf(message.length()).byteValue());

                            // TODO: 18-5-28 测试头部和文件是否作为一个消息传递
                            buffer.put(message.getBytes());

                            channel.write(buffer);

                            logger.info("开始传输文件{}", application.getPath());
                            transfer.toSocketFile(channel, application.getPath());
                            logger.info("结束传输文件{}", application.getPath());
                            key.cancel();
                            channel.close();
                            if (fileTransferListener != null) fileTransferListener.hand(application);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            } else {
                logger.error("socket数据格式错误，请检查客户端版本是否匹配");
            }
        }
    }
}
