package cn.inkroom.software.chat.common.util;

import java.util.Date;

/**
 * Created by 墨盒 on 17:18.
 * 消息解析器
 */
public interface MessageReader {
    /**
     * 获取接收方
     * @return
     */
    public String getSender();

    /**
     * 获取发送方
     * @return
     */
    public String getRecever();

    /**
     * 获取消息
     * @return
     */
    public String getMessage();

    /**
     * 获取发送时间
     * @return
     */
    public Date getSendDate();

    public void init(String value);

    public int getType();
}
