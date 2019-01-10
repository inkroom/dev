package cn.inkroom.software.update.file.watcher;

import cn.inkroom.software.update.file.watcher.event.FileEventListener;
import com.sun.istack.internal.NotNull;

import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedList;
import java.util.List;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * 监听多个文件
 */
public class MultiFileWatcher implements Runnable {

    private WatchService watchService;
    private String path;
    private FileEventListener listener;

    private MultiFileWatcher(String dir, FileEventListener listener) {
        try {
            watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(dir);
            this.path = dir;
            this.listener = listener;
            path.register(watchService, ENTRY_MODIFY, ENTRY_CREATE, ENTRY_DELETE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startWatcher(List<String> dirs, @NotNull FileEventListener listener) {
        MultiFileWatcher watchers[] = new MultiFileWatcher[dirs.size()];
        for (int i = 0; i < watchers.length; i++) {
            watchers[i] = new MultiFileWatcher(dirs.get(i), listener);
            new Thread(watchers[i]).start();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                WatchKey key = watchService.take();
                for (WatchEvent event : key.pollEvents()) {
                    if (event.kind() == OVERFLOW) continue;
                    listener.hand(this.path, event.kind());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
