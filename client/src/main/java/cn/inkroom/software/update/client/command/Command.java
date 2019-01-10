package cn.inkroom.software.update.client.command;

import java.io.IOException;
import java.util.List;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2018/5/12
 * @Time 23:21
 * @Descorption 对项目进行重启，卸载，上传的接口
 */
public interface Command {
    /**
     * 获取所有的contextPath
     * @return 集合
     * @throws IOException 异常
     */
    List<String> list() throws IOException;

    /**
     * 启动整个容器
     *
     * @return 成功或者失败
     * @throws IOException io异常，如网络超时
     */
    boolean start() throws IOException;

    /**
     * 启动个别应用，如tomcat下启动不同的contextPath
     *
     * @param contextPath 要启动的项目名
     * @return 结果
     * @throws IOException io异常，如网络超时
     */
    boolean start(String contextPath) throws IOException;

    /**
     * 重新加载个别应用，如tomcat下启动不同的contextPath
     *
     * @param contextPath 要启动的项目名
     * @return 结果
     * @throws IOException io异常，如网络超时
     */
    boolean reload(String contextPath) throws IOException;

    /**
     * 卸载个别应用，如tomcat下启动不同的contextPath
     *
     * @param contextPath 要启动的项目名
     * @return 结果
     * @throws IOException io异常，如网络超时
     */
    boolean undelopy(String contextPath) throws IOException;

    /**
     * 关闭整个容器
     *
     * @return 成功或者失败
     * @throws IOException io异常，如网络超时
     */
    boolean stop() throws IOException;

    /**
     * 关闭个别应用，如tomcat下启动不同的contextPath
     *
     * @param contextPath 要启动的项目名
     * @return 结果
     * @throws IOException io异常，如网络超时
     */
    boolean stop(String contextPath) throws IOException;

    /**
     * 获取context的状态
     * @param contextPath 指定项目
     * @return 状态
     * @throws IOException 异常
     */
    boolean getStatus(String contextPath) throws IOException;

    /**
     * 获取容器的状态
     * @return 状态
     * @throws IOException 异常
     */
    boolean getStatus() throws IOException;
    /**
     * 获取消息，如在启动应用失败后获取结果
     *
     * @return 消息
     */
    String getMessage();
}
