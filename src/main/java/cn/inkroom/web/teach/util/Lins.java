package cn.inkroom.web.teach.util;

import sun.nio.ch.FileLockImpl;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by 墨盒 on 16:34.
 */
public class Lins implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        Integer number = (Integer) httpSessionEvent.getSession().getServletContext().getAttribute("number");
        if (number == null)
            httpSessionEvent.getSession().getServletContext().setAttribute("number", 1);
        else
            httpSessionEvent.getSession().getServletContext().setAttribute("number", number + 1);
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        Integer number = (Integer) httpSessionEvent.getSession().getServletContext().getAttribute("number");
        if (number != null)
            httpSessionEvent.getSession().getServletContext().setAttribute("number", number - 1);

    }

    public static void main(String[] args) {
//        randomName("E:\\娱乐\\图片\\1600X800");

        try {
//            FileOutputStream fis= new FileOutputStream(new File("E:\\娱乐\\视频\\动漫\\灰色\\灰色的迷宫.mp4"));
//            FileLock lock= fis.getChannel().lock();
//            System.out.println(lock.position());
//            while (true);
//            FileChannel
//            FileLockImpl impl=new FileLockImpl();
//            System.out.println(new File("E:\\娱乐\\视频\\动漫\\灰色\\灰色的迷宫.mp4").canWrite());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void randomName(String path) {
        File files[] = new File(path).listFiles();
        for (int i = 0; i < files.length; i++) {
            String newName = null;
            do {
                newName = createName();
            } while (newName == null);
            files[i].renameTo(new File(files[i].getParent() + File.separator +
                    newName +
//                    ".jpg"
                    files[i].getName().substring(files[i].getName().lastIndexOf("."))
            ));

        }
    }

    static Map<String, Integer> map = new HashMap<String, Integer>();

    public static String createName() {
        String salt = "qwertyuioplkjhgfdsazxcvbnm123456789";
        StringBuilder builder = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            builder.append(salt.charAt(r.nextInt(salt.length())));
        }
        if (map.get(builder.toString()) != null) {
            return null;
        }
        map.put(builder.toString(), 1);
        return builder.toString();
    }
}
