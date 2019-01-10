package cn.inkroom.software.update.client.command;

/**
 * 命令代理类
 */
public interface CommandProxy {
    /**
     * 启动或重新加载项目
     *
     * @return 成功或失败
     */
    boolean startOrReload(String file);

    /**
     * 获取现有的，或者新的context位置
     *
     * @return 文件夹路径，或者文件路径，绝对路径
     */
    String getNewPath();

    /**
     * 是否是war包形式
     * @return
     */
    boolean isFile();

    String getMessage();
}
