package cn.inkroom.software.version.common.reader;

import cn.inkroom.software.version.common.enums.MessageType;

public interface MessageReader {
    /**
     * 获取额外传递的信息
     * @return 额外信息
     */
    String getOther();
    /**
     * 读消息
     *
     * @param bytes 消息字节
     * @return 格式正确则返回true，否则返回false
     */
    boolean read(byte bytes[]);

    /**
     * 获取消息类型
     *
     * @return 消息类型
     */
    MessageType getType();

    /**
     * 获取app名字
     *
     * @return app
     */
    String getApp();

    /**
     * <p>仅对文件操作类型有效</p>
     * 获取操作类型，如删除
     *
     * @return 操作类型
     */
    String getOperator();

    /**
     * 获取web容器操作类型
     *
     * @return 类型
     */
    String getCommand();

    /**
     * 获取数据socket端口
     *
     * @return 端口
     */
    int getPort();

    /**
     * 获取app版本
     *
     * @return 版本
     */
    int getVersion();

}
