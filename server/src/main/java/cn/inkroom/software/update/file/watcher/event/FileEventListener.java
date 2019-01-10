package cn.inkroom.software.update.file.watcher.event;

import java.nio.file.WatchEvent;

public interface FileEventListener {

    void hand(String path, WatchEvent.Kind kind);
}
