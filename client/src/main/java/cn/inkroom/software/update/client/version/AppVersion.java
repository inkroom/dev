package cn.inkroom.software.update.client.version;

import cn.inkroom.software.update.client.command.CommandProxy;

public class AppVersion {
    private int version;
    private String appName;
    private String basePath;
    //容器类型
    private int type;
    private CommandProxy proxy;

    public AppVersion(String appName, String basePath, int type, CommandProxy proxy,int version) {
        this.appName = appName;
        this.basePath = basePath;
        this.type = type;
        this.version = version;
        this.proxy = proxy;
    }

    public int getVersion() {
        return version;
    }

    public String getBasePath() {
        return proxy.getNewPath();
    }

    // TODO: 18-5-14 如何在更新文件之后对服务进行重启 还要有返回。。。。
    public boolean update(String file) {
        return proxy.startOrReload(file);
    }

    public String getApp() {
        return appName;
    }

    public boolean isFile() {
        return proxy.isFile();
    }

    public String getMessage() {
        return proxy.getMessage();
    }
}
