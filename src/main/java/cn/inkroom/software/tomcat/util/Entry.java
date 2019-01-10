package cn.inkroom.software.tomcat.util;

import org.springframework.util.Base64Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Entry {
    /**
     * 请求 host,port 账号，密码，命令，contextPath
     *
     * @param args
     */
    public static void main(String[] args) {

        if (args.length >= 5) {//list

            String command = args[4];
            if (command.equals("list")) {
                connect(args[0], args[1], args[2], args[3], args[4], "");
            } else {
                connect(args[0], args[1], args[2], args[3], args[4], args[5]);
            }


        } else {
            System.out.println("错误命令");
        }


    }

    private static void connect(String host, String port, String username, String password, String command, String contextPath) {
        try {
            URL u = null;
            if (command.equals("list")) {
                u = new URL("http://" + host + ":" + port + "/manager/text/" + command);
            } else {
                u = new URL("http://" + host + ":" + port + "/manager/text/" + command + "?path=/" + contextPath);
            }

            String userPassword = username + ":" + password;//此处为用户名密码
            String encoding = Base64Utils.encodeToString(userPassword.getBytes());//在classpath中添加rt.jar包，在%java_home%/jre/lib/rt.jar
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestProperty("Authorization", "Basic " + encoding);

            int statusCode = conn.getResponseCode();
            if (statusCode != 200) {
                System.out.println("请求错误" + statusCode);
            }
            InputStream is = conn.getInputStream();
            BufferedReader breader = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = breader.readLine()) != null) {
                builder.append(line);
            }
            breader.close();
            System.out.println(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
