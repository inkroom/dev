package cn.inkroom.software.version.common.socket.listener;

import cn.inkroom.software.version.common.app.Application;

/**
 * 文件传输监听器
 */
public interface FileTransferListener {

    /**
     * 文件下载完成后触发
     * @param application 新下载的application
     */
    void hand(Application application);

    /**
     * 文件开始下载之前触发
     */
    void before(Application application);
}
