package cn.inkroom.software.update.common.message.reader.update;

import cn.inkroom.software.update.common.message.reader.XmlMessageReader;

/**
 * 更新文件读取器
 */
public class UpdateMessageReader extends XmlMessageReader {
    private int port;

    public int getPort() {
        return Integer.parseInt(getContent("port", getMessage()));
    }
}
