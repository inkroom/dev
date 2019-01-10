package cn.inkroom.software.update.client.command.tomcat;

import cn.inkroom.software.update.client.command.CommandProxy;

import java.io.File;
import java.io.IOException;

/**
 * tomcat启动contextPath代理类
 */
public class TomcatContextCommandProxy implements CommandProxy {

    private String basePath;//基础路径
    private String contextPath;//原有的contextPath

    private TomcatCommand command;

    public TomcatContextCommandProxy(String host, int port, String username, String password,String contextPath,String basePath) {
        command = new TomcatCommand(host, port, username, password);
        this.contextPath = contextPath;
        this.basePath = basePath;
    }


    private String getContextPath(String file) {
        return file.replaceAll(basePath, "").replaceAll("\\.war", "");
    }

    @Override
    public boolean startOrReload(String file) {
        try {
            int count = 0;
            String newContext = getContextPath(file);

            while (count < 3 && !command.list().contains(contextPath)) {
                try {
                    Thread.sleep(3000);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (count >= 3) {
                return false;
            }

            if (command.stop(contextPath)) {
                // TODO: 18-5-15 逻辑待完善
                if (command.start(newContext) && command.getStatus(newContext)) {
                    deleteFile(basePath + File.separator + contextPath + (isFile() ? ".war" : ""));
                    contextPath = newContext;
                    return true;
                } else {
                    if (command.reload(newContext)) {
                        deleteFile(basePath + File.separator + contextPath + (isFile() ? ".war" : ""));
                        contextPath = newContext;
                        return true;
                    }
                }
            }
            //删除新下载的文件
            deleteFile(file);
            return false;


        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String getNewPath() {
        return basePath + File.separator + createNewContext() + (isFile() ? ".war" : "");
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public String getMessage() {
        return command.getMessage();
    }

    private String createNewContext() {
        if (contextPath.contains("-")) {
            return contextPath.substring(0, contextPath.indexOf("-") + 1) + System.currentTimeMillis();
        } else {
            return contextPath + "-" + System.currentTimeMillis();
        }
    }

    private void deleteFile(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File files[] = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    deleteFile(f.getAbsolutePath());
                }
            }
        }
        file.delete();
    }
}
