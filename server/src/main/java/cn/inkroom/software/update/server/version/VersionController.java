package cn.inkroom.software.update.server.version;

import cn.inkroom.software.update.common.message.reader.MessageReader;
import cn.inkroom.software.update.common.message.writer.MessageWriter;
import cn.inkroom.software.update.file.watcher.MultiFileWatcher;
import cn.inkroom.software.update.file.watcher.event.FileEventListener;
import cn.inkroom.software.update.server.event.MessageEventKey;
import cn.inkroom.software.update.server.event.MessageEventListener;
import cn.inkroom.software.update.server.socket.data.DataSocket;
import cn.inkroom.software.update.server.socket.message.AcceptSocket;
import cn.inkroom.software.update.server.version.map.DirectoryVersion;

import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;

/**
 * 核心处理器，处理版本更新等操作
 */
public class VersionController implements MessageEventListener, FileEventListener {
    private AcceptSocket socket;
    //是否进行自动更新
    private boolean autoUpdate;
    private List<DirectoryVersion> versions;//管理多个版本工具
    private int dataPort;

    private DataSocket dataSocket;
    private MessageWriter writer;

    public void setWriter(MessageWriter writer) {
        this.writer = writer;
    }

    public VersionController(int messagePort, int dataPort, boolean auto, List<DirectoryVersion> versions) {
        this.versions = versions;
        socket = new AcceptSocket(messagePort);
        socket.setListener(this);
        this.dataPort = dataPort;
        try {
            socket.startThread();

            this.autoUpdate = auto;
            if (this.autoUpdate) {//启动文件监听器
                registerFileWatcher();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 注册文件监听器
     *
     * @throws IOException
     */
    private void registerFileWatcher() throws IOException {
        List<String> dirs = new LinkedList<>();
        for (DirectoryVersion v : versions) {
            dirs.add(v.getBasePath());
        }
        MultiFileWatcher.startWatcher(dirs, this);
    }

    public void broadVersion() {
        for (DirectoryVersion version : versions) {
            if (version.updateModify()) {
                try {
                    if (dataSocket == null) {
                        dataSocket = new DataSocket(dataPort, versions);
                        dataSocket.startThread();
                    }
                    socket.broadVersion(writer.writeType(MessageReader.Type.UPDATE).writeVersion(version.getVersion()).writeApp(version.getApp()).writePort(this.dataPort).toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void hand(MessageEventKey key) {
        switch (key.getMessageReader().getType()) {
            case UPDATE:
                broadVersion();
                break;
            case CREATE:
                broadVersion();
                break;
            case FAIL:
                break;
            case OK:
                break;
        }
    }

    @Override
    public void hand(String path, WatchEvent.Kind kind) {
        for (DirectoryVersion v : versions) {
            if (v.getBasePath().equals(path)) {
                v.updateModify();
            }
        }
    }
}
