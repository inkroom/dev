package cn.inkroom.software.md5;

import com.sun.javafx.binding.StringFormatter;
import org.junit.Test;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.util.*;

/**
 * @author 墨盒
 */

public class Entry {


    @Test
    public void test() {


        Map<String, List<String>> map = new HashMap<>();

        String base = "/media/inkbox/study/娱乐/图片/从零开始的异世界生活";


        String[] paths = new File(base).list();

        for (int i = 0; i < paths.length; i++) {
            try {
                String md5 = DigestUtils.md5DigestAsHex(new FileInputStream(base + File.separator + paths[i]));
                List<String> list = map.get(md5);
                if (list == null) {

                    ArrayList<String> l = new ArrayList<>();
                    l.add(paths[i]);
                    map.put(md5, l);

                } else {
                    list.add(paths[i]);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            List<String> list = map.get(key);
            if (list.size() != 1) {

                for (int i = 1; i < list.size(); i++) {
                    new File(base + File.separator + list.get(i)).delete();
                }


                System.out.println("md5=" + key + ",重复文件为-" + list);
            }
        }


    }


    @Test
    public void eq() {


        String base = "";
        String paths[] = new File(base).list();
        for (int i = 0; i < paths.length; i++) {


        }


    }

    @Test
    public void rename() {

        String base = "/media/inkbox/study/娱乐/图片/壁纸";
        File file = new File(base);
        File files[] = file.listFiles();

        String prefix = file.getName();
        for (int i = 0; i < files.length; i++) {
            String target = String.format(base + File.separator + prefix + "%03d" + files[i].getName().substring(files[i].getName().lastIndexOf(".")), i + 1);

            files[i].renameTo(new File(target));
            System.out.println(target);

        }


    }

    /*
    gsettings set  com.deepin.dde.appearance background-uris  "['file:///home/inkbox/.config/deepin/dde-daemon/appearance/custom-wallpapers/e1f3635d6f64bd924e9d8b7ca353917f.jpg', 'file:///usr/share/wallpapers/deepin/66648306_p0_master1200.jpg']"
     */

    @Test
    public void backGround() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process= runtime.exec("qdbus --literal com.deepin.wm /com/deepin/wm com.deepin.wm.ChangeCurrentWorkspaceBackground \"file:///media/inkbox/study/娱乐/图片/壁纸/壁纸021.jpg\"");
            BufferedReader reader = new BufferedReader (new InputStreamReader(process.getInputStream()));
            String line ;
            while ((line=reader.readLine())!=null){
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
