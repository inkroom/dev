package cn.inkroom.software.update.client.socket.listener;

/**
 * 文件传输完成事件，用于接收完数据后的操作
 */
public interface FileTransferListener {

    void hand(String app,String file);
}
