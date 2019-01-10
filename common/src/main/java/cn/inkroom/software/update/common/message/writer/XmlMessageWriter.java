package cn.inkroom.software.update.common.message.writer;

import cn.inkroom.software.update.common.message.reader.MessageReader;

public class XmlMessageWriter implements MessageWriter {
    private StringBuilder builder;

    public XmlMessageWriter() {
        builder = new StringBuilder();
    }

    @Override
    public MessageWriter writeApp(String appName) {
        builder.append("<app>").append(appName).append("</app>");
        return this;
    }

    @Override
    public MessageWriter writeIp(String ip) {
        builder.append("<ip>").append(ip).append("</ip>");
        return this;
    }

    @Override
    public MessageWriter writeVersion(int version) {
        builder.append("<version>").append(version).append("</version>");
        return this;
    }

    @Override
    public MessageWriter writeType(MessageReader.Type type) {
        builder.append("<type>").append(type).append("</type>");
        return this;
    }

    @Override
    public MessageWriter write(String ip, int version, MessageReader.Type type) {
        writeIp(ip);
        writeVersion(version);
        writeType(type);
        return this;
    }

    @Override
    public MessageWriter writePort(int port) {
        builder.append("<port>").append(port).append("</port>");
        return this;
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
