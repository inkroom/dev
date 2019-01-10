package cn.inkroom.software.version.common.app;

import java.io.File;

/**
 * web项目
 */
public class Application {

    private String name;
    private String status;
    private String path;//如果是文件，则为文件绝对路径

    private String context;
    private int version;

    public String getContext() {
        return context;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isFile() {
        return new File(this.getPath()).isFile();
    }
}
