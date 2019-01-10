package cn.inkroom.software.version.common.writer;


import cn.inkroom.software.version.common.app.Application;
import cn.inkroom.software.version.common.enums.MessageType;

import java.util.List;

public interface MessageWriter {

    /**
     * 写入消息类型
     *
     * @return 消息类型
     */
    MessageWriter writeType(MessageType type);

    /**
     * 写入app名字
     *
     * @return app
     */
    MessageWriter writeApp(String app);

    /**
     * <p>仅对文件操作类型有效</p>
     * 写入操作类型，如删除
     *
     * @return 操作类型
     */
    MessageWriter writeOperator(String operator);

    /**
     * 写入web容器操作类型
     *
     * @return 类型
     */
    MessageWriter writeCommand(String command);

    /**
     * 写入数据socket端口
     *
     * @return 端口
     */
    MessageWriter writePort(int port);

    /**
     * 写入app版本
     *
     * @return 版本
     */
    MessageWriter writeVersion(int version);

    MessageWriter writeContexts(List<Application> applications);

    /**
     * 清空以前写入的数据
     */
    MessageWriter clear();

    /**
     * 获取消息
     *
     * @return 消息的字符串形式
     */
    String getMessage();
}
