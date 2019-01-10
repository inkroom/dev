package cn.inkroom.software.update.common.message.reader;

/**
 * 消息解析器
 */
public interface MessageReader {

    int getPort();
    /**
     * 读取数据
     *
     * @return 解析成功
     */
    boolean readMessage(byte[] bytes);

    String getIp();

    String getMessage();
    /**
     * 获取对应管理的文件
     *
     * @return
     */
    String getApp();

    /**
     * 获取对应的消息ack
     *
     * @return
     */
    int getVersion();

    /**
     * 消息类型
     *
     * @return 消息类型
     */
    Type getType();
    public enum Type {
        CONNECT,//客户端连接
        UPDATE,//客户端版本待更新
        CREATE,//客户端创建版本
        OK,//客户端处理结果
        FAIL//客户端处理结果
    }

}
