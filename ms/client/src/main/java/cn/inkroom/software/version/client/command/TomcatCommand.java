package cn.inkroom.software.version.client.command;

import cn.inkroom.software.version.common.app.Application;
import org.springframework.util.Base64Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TomcatCommand implements Command{

    private String host;
    private int port;

    private String username;
    private String password;

    private String managerContext;//manager管理模块的contextPath

    public TomcatCommand(String host, int port, String username, String password) {
        this(host, port, username, password, "manager");
    }

    public TomcatCommand(String host, int port, String username, String password, String managerContext) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.managerContext = managerContext;
    }

    /**
     * 获取认证的url
     *
     * @param url 请求地址，如果get请求有参数也一并写上
     * @return 请求连接
     * @throws IOException IO异常
     */
    private HttpURLConnection getAuthorityConn(String url) throws IOException {
        URL u = new URL(url);
        String userPassword = this.username + ":" + this.password;//此处为用户名密码
        String encoding = Base64Utils.encodeToString(userPassword.getBytes());
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setRequestProperty("Authorization", "Basic " + encoding);
        return conn;
    }

    @Override
    public List<Application> list() throws IOException {
        String url = getUrlWithoutContextPath("list");
        HttpURLConnection connection = getAuthorityConn(url);
        int statusCode = connection.getResponseCode();
        if (statusCode == 200) {
            InputStream is = connection.getInputStream();
            BufferedReader breader = new BufferedReader(new InputStreamReader(is));
            String line;
            List<Application> list = new ArrayList<>();
            Pattern pattern = Pattern.compile("^/(.*):.+:.:.+");
            // TODO: 18-5-28 正则待完善
//            while ((line = breader.readLine()) != null) {
//                Matcher matcher = pattern.matcher(line);
//                if (matcher.find()) list.add(matcher.group(1));
//            }
            return list;
        }
        message = "" + statusCode;
        return null;
    }

    @Override
    public boolean start() throws IOException {
        return false;
    }

    @Override
    public boolean start(String contextPath) throws IOException {
        return connect(getUrlWithoutContextPath("start") + contextPath);
    }

    private boolean connect(String url) throws IOException {
        HttpURLConnection connection = getAuthorityConn(url);
        //判断结果
        boolean result = getResponse(connection);
        connection.disconnect();
        return result;
    }

    private String message;

    private boolean getResponse(HttpURLConnection connection) throws IOException {
        int statusCode = connection.getResponseCode();
        InputStream is = connection.getInputStream();
        BufferedReader breader = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = breader.readLine()) != null) {
            builder.append(line);
        }
        message = builder.toString();
        return statusCode == 200 && builder.toString().contains("OK");
    }

    private String getUrlWithoutContextPath(String command) {
        return "http://" + this.host + ":" + this.port + "/" + this.managerContext + "/text/" + command + "?path=/";
    }

    @Override
    public boolean reload(String contextPath) throws IOException {
        return connect(getUrlWithoutContextPath("reload") + contextPath);
    }

    @Override
    public boolean undelopy(String contextPath) throws IOException {
        return connect(getUrlWithoutContextPath("undelopy") + contextPath);
    }

    @Override
    public boolean stop() throws IOException {
        return false;
    }

    @Override
    public boolean stop(String contextPath) throws IOException {
        return connect(getUrlWithoutContextPath("stop") + contextPath);
    }

    // TODO: 18-5-15 方法待完善
    @Override
    public boolean getStatus(String contextPath) throws IOException {

        return true;
    }

    @Override
    public boolean getStatus() throws IOException {
        return true;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
