package cn.inkroom.software.version.client.core;

import cn.inkroom.software.version.client.command.Command;
import cn.inkroom.software.version.client.proxy.AppProxy;
import cn.inkroom.software.version.client.queue.ApplicationQueue;
import cn.inkroom.software.version.client.socket.DataSocket;
import cn.inkroom.software.version.common.app.Application;
import cn.inkroom.software.version.common.enums.MessageType;
import cn.inkroom.software.version.common.reader.DomXmlReader;
import cn.inkroom.software.version.common.reader.MessageReader;
import cn.inkroom.software.version.common.socket.client.ClientSocket;
import cn.inkroom.software.version.common.socket.listener.FileTransferListener;
import cn.inkroom.software.version.common.socket.listener.MessageListener;
import cn.inkroom.software.version.common.socket.util.ChannelUtil;
import cn.inkroom.software.version.common.writer.DomXmlWriter;
import cn.inkroom.software.version.common.writer.MessageWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;

/**
 * 核心控制器
 */
public class ClientController implements MessageListener, FileTransferListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Command command;
    private ClientSocket messageSocket;
    private ChannelUtil channelReader = new ChannelUtil();
    private MessageReader reader;
    private ApplicationQueue queue;
    private MessageWriter writer;

    public void setChannelReader(ChannelUtil channelReader) {
        this.channelReader = channelReader;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void setReader(MessageReader reader) {
        this.reader = reader;
    }

    public void setWriter(MessageWriter writer) {
        this.writer = writer;
    }

    public ClientController(int port, String host, ApplicationQueue queue, Command command) {
        reader = new DomXmlReader();
        writer = new DomXmlWriter();
        this.queue = queue;
        this.command = command;
        messageSocket = new ClientSocket(port, host);
        messageSocket.setListener(this);
        try {
            messageSocket.startThread();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void hand(SelectionKey key, byte b) {
        SocketChannel channel = (SocketChannel) key.channel();
        //读取消息
        try {
            byte[] message = channelReader.getMessage(channel, b);
            if (reader.read(message)) {
                switch (reader.getType()) {
                    case FILE:
                        operatorFile(reader.getApp(), reader.getOperator(), channel);
                        break;
                    case COMMAND:
                        operatorContext(reader.getApp(), reader.getCommand(), channel);
                        break;
                    case LIST:
                        break;
                    case DOWNLOAD:
                        download(reader.getApp(), reader.getVersion(), reader.getPort(), channel);
                        break;
                    default:
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 操作context
     *
     * @param app     app
     * @param command 命令
     */
    private void operatorContext(String app, String command, SocketChannel channel) {
        try {
            String message;
            ByteBuffer buffer;
            switch (command) {
                case "start":
                    if (this.command.start()) {
                        writer.clear().writeApp(app).writeCommand(command).writeType(MessageType.COMMAND_FAIL);
                    } else {
                        writer.clear().writeApp(app).writeType(MessageType.COMMAND_OK);
                    }

                    break;
                case "reload":
                    String context = reader.getOther();
                    if (this.command.reload(context)) {
                        writer.clear().writeApp(app).writeType(MessageType.COMMAND_OK);
                    } else {
                        writer.clear().writeApp(app).writeCommand(command).writeType(MessageType.COMMAND_FAIL);
                    }
                    break;
                case "undelopy":
                    context = reader.getOther();
                    if (this.command.undelopy(context)) {
                        writer.clear().writeApp(app).writeType(MessageType.COMMAND_OK);
                    } else {
                        writer.clear().writeApp(app).writeCommand(command).writeType(MessageType.COMMAND_FAIL);
                    }
                    break;
                case "stop":
                    if (this.command.stop()) {
                        writer.clear().writeApp(app).writeType(MessageType.COMMAND_OK);
                    } else {
                        writer.clear().writeApp(app).writeCommand(command).writeType(MessageType.COMMAND_FAIL);
                    }
                case "stopWith":
                    context = reader.getOther();
                    if (this.command.stop(context)) {
                        writer.clear().writeApp(app).writeType(MessageType.COMMAND_OK);
                    } else {
                        writer.clear().writeApp(app).writeCommand(command).writeType(MessageType.COMMAND_FAIL);
                    }
                    break;
                case "getStatusWith":
                    context = reader.getOther();
                    if (this.command.getStatus(context)) {
                        writer.clear().writeApp(app).writeType(MessageType.COMMAND_OK);
                    } else {
                        writer.clear().writeApp(app).writeCommand(command).writeType(MessageType.COMMAND_FAIL);
                    }
                    break;
                case "getStatus":
                    if (this.command.getStatus()) {
                        writer.clear().writeApp(app).writeType(MessageType.COMMAND_OK);
                    } else {
                        writer.clear().writeApp(app).writeCommand(command).writeType(MessageType.COMMAND_FAIL);
                    }
                    break;
                case "list":
                    List<Application> applications = this.command.list();
                    if (applications != null) {
                        writer.clear().writeType(MessageType.LIST_OK).writeContexts(applications);
                    } else {
                        writer.clear().writeCommand(command).writeType(MessageType.COMMAND_FAIL);
                    }
                    break;
                default:
                    logger.error("错误的命令-{}", command);
                    return;
            }
            message = writer.getMessage();
            buffer = ByteBuffer.wrap(message.getBytes());
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 操作文件
     *
     * @param app      app
     * @param operator 操作类型
     */
    private void operatorFile(String app, String operator, SocketChannel channel) {
        switch (operator) {
            case "delete":
                break;
        }
    }

    /**
     * 下载文件
     *
     * @param app     app
     * @param version 版本
     * @param port    数据端口
     * @param channel 频道
     * @throws IOException 异常
     */
    private void download(String app, int version, int port, SocketChannel channel) throws IOException {
        Application application = queue.getApplication(app);
        if (application != null && application.getVersion() < version) {

            AppProxy proxy = new AppProxy(application);

            DataSocket dataSocket = new DataSocket(port, messageSocket.getHost(), proxy);
            dataSocket.setTransferListener(this);
            dataSocket.startThread();

            //往数据socket发送消息，告诉对方我需要下载哪个app
            writer.clear().writeApp(app).writeVersion(version).writeType(MessageType.DOWNLOAD_ING);

            String message = writer.getMessage();
            dataSocket.sendMessage(message);

        }
    }

    @Override
    public void hand(Application application) {
        logger.info("文件下载完成，存储路径:{}，app name:{},version {}", application.getPath(), application.getName(), application.getVersion());
        //下载成功
        writer.clear().writeType(MessageType.DOWNLOAD_OK).writeApp(application.getName()).writeVersion(application.getVersion());
        try {
            messageSocket.sendMessage(writer.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void before(Application application) {

        //向消息socket发送消息，告诉对方我正在下载文件
        writer.clear().writeApp(application.getName()).writeType(MessageType.DOWNLOAD_ING).writeVersion(application.getVersion());

        String message = writer.getMessage();

        try {
            messageSocket.sendMessage(message);
            logger.debug("往服务器端写消息：" + message);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
