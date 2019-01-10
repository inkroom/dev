package cn.inkroom.software.update.server.version.map;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * 记录文件夹的更新日期等版本信息
 * <p>文件形式</p>
 */
public class DirectoryVersion {

    private String appName;//唯一的标志
    private int version;//版本号
    //记录文件最后更新日期，key绝对路径，value最后更新日期
    private LinkedHashMap<String, Long> versionData;
    //本次检查更新了的文件
    private LinkedList<String> modify;
    private String basePath;

    public String getApp() {
        return appName;
    }

    public String getBasePath() {
        return basePath;
    }

    public int getVersion() {
        return version;
    }

    public DirectoryVersion(String appName, String basePath, int version) {
        this.appName = appName;
        this.version = version;
        versionData = new LinkedHashMap<>();
        modify = new LinkedList<>();
        init(basePath);
    }

    public void init(String basePath) {
        versionData.clear();
        this.basePath = basePath;

        File files[] = new File(this.basePath).listFiles();
        if (files != null) {
            modify.add(this.basePath);
            for (File file : files) {
                getLastModify(file.getAbsolutePath());
            }
        }
    }

    private void getLastModify(String path) {
        File file = new File(path);
        if (file.exists() && file.isDirectory()) {
            File files[] = file.listFiles();
            versionData.put(file.getAbsolutePath(), file.lastModified());
            if (files != null) {
                for (File file1 : files) {
                    getLastModify(file1.getAbsolutePath());
                }
            }
        } else if (file.isFile()) {
            versionData.put(file.getAbsolutePath(), file.lastModified());
        }
    }

    /**
     * 更新modify
     *
     * @return 如果有文件更新，返回true
     */
    public boolean updateModify() {
        modify.clear();
        File file = new File(this.basePath);
        File files[] = file.listFiles();
        if (files != null) {
            for (File file1 : files) {
                if (check(file1)) {
                    modify.add(file1.getAbsolutePath());
                    getLastModify(file1.getAbsolutePath());
                }
            }
        }
        return !modify.isEmpty();
    }

    /**
     * 判断文件是否更新了
     *
     * @param file
     * @return
     */
    private boolean check(File file) {
        Long modify = versionData.get(file.getAbsolutePath());
        return (modify != null && modify != file.lastModified());
    }

    public LinkedList<String> getModify() {
        return modify;
    }

    public boolean isOutOfData() {
        return !modify.isEmpty();
    }
}
