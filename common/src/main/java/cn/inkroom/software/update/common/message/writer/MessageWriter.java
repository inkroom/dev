package cn.inkroom.software.update.common.message.writer;

import cn.inkroom.software.update.common.message.reader.MessageReader;

public interface MessageWriter {

    MessageWriter writeApp(String appName);
    MessageWriter writeIp(String ip);

    MessageWriter writeVersion(int version);

    MessageWriter writeType(MessageReader.Type type);

    MessageWriter write(String ip, int version, MessageReader.Type type);

    MessageWriter writePort(int port);

    /**
     * 获取消息
     * @return
     */
    String toString();
}
