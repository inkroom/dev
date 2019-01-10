package cn.inkroom.software.log;

import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 墨盒
 */
public class Entry {
    //TODO 尝试动态修改log4j配置
    public static void main(String[] args) {
        Properties properties = new Properties();
        try {


            properties.load(Object.class.getResourceAsStream("/log.properties"));


        } catch (IOException e) {
            e.printStackTrace();
        }

//        PropertyConfigurator.configure("log4j.properties");
        if (args == null) {

        } else if (args.length == 4) {
            /**
             * 依次是端口号，最大延迟，日志文件位置，输出级别
             */
            int port = Integer.parseInt(args[0]);
            int max = Integer.parseInt(args[1]);
            String log = args[2];
            try {
                properties.setProperty("log4j.appender.logfile.fileName", log);
                properties.setProperty("log4j.appender.logfile.level", args[3]);
                PropertyConfigurator.configure(properties);

                Controller controller = new Controller(port, max);
                controller.start();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
