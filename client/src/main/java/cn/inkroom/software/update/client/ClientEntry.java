package cn.inkroom.software.update.client;

import cn.inkroom.software.update.client.command.Command;
import cn.inkroom.software.update.client.command.CommandProxy;
import cn.inkroom.software.update.client.command.tomcat.TomcatCommand;
import cn.inkroom.software.update.client.command.tomcat.TomcatContextCommandProxy;
import cn.inkroom.software.update.client.core.VersionController;
import cn.inkroom.software.update.client.version.AppVersion;
import cn.inkroom.software.update.common.message.reader.XmlMessageReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2018/5/12
 * @Time 22:59
 * @Descorption
 */
public class ClientEntry {
    public static void main(String[] args) {


        CommandProxy proxy = new TomcatContextCommandProxy("localhost", 8080, "s", "s",
                "kill", "/media/inkbox/software/software/java/apache-tomcat-9.0.0.M19-windows-x64/apache-tomcat-9.0.0.M19/webapps");
        Map<String, AppVersion> map = new HashMap<>();
        AppVersion appVersion = new AppVersion("test", "/media/inkbox/software/software/java/apache-tomcat-9.0.0.M19-windows-x64/apache-tomcat-9.0.0.M19/webapps"
                , 1, proxy, 1);
        map.put(appVersion.getApp(), appVersion);
        VersionController controller = new VersionController(9999, "localhost", map);
        controller.setReader(new XmlMessageReader());
    }
}
