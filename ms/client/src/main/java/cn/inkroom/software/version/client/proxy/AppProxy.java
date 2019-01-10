package cn.inkroom.software.version.client.proxy;

import cn.inkroom.software.version.common.app.Application;

import java.io.File;

/**
 * app工具类
 */
public class AppProxy {

    private Application application;

    public AppProxy() {
    }

    public AppProxy(Application application) {
        this.application = application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getApp() {
        return application.getName();
    }

    public int getVersion() {
        return application.getVersion();
    }

    /**
     * 获取新的contextPath
     *
     * @return
     */
    protected String createNewContext() {
        try {
            String oldContext = application.getContext();
            if (oldContext.contains("-")) {
                return oldContext.substring(0, oldContext.indexOf("-") + 1) + System.currentTimeMillis();
            }
            return oldContext + "-" + System.currentTimeMillis();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("application can't be null");
        }
    }

    public void setContextPath(String contextPath) {
        application.setContext(contextPath);
    }

    public void setPath(String path) {
        application.setPath(path);
    }

    /**
     * 根据context创建新的文件路径
     *
     * @param context contextPath
     * @return 新的路径，用于下载文件
     */
    protected String createNewPath(String context) {
        File file = new File(application.getPath());
        if (file.isDirectory()) {
            // TODO: 18-5-18 暂不考虑文件夹
            return null;
        } else {
            return file.getParent() + File.separator + context + ".war";
        }

    }

    /**
     * 获取新版本的app
     *
     * @return
     */
    public Application getUpdateApp() {
        String newContext = createNewContext();

        Application newApp = new Application();
        newApp.setName(application.getName());
        newApp.setContext(newContext);
        newApp.setPath(createNewPath(newContext));

        return newApp;
    }


}
