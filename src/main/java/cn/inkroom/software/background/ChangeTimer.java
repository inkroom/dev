package cn.inkroom.software.background;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.TimerTask;

/**
 * @author 墨盒
 */
public class ChangeTimer extends TimerTask {


    private String base;

    private String files[];
    private ChangeListener listener;
    private Random random;

    public ChangeTimer(String base) {
//        this.interval = interval;
        this.base = base;
        files = new File(base).list();
        random = new Random();
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }
//    /**
//     *
//     * @param args 第一个为文件夹路径，第二个为间隔
//     */
//    public static void main(String[] args) {
//        if (args.length == 2) {
//
//            ChangeTimer entry = new ChangeTimer(args[0]);
//
//            java.util.Timer timer = new java.util.Timer();
//            timer.schedule(entry, 0, Long.parseLong(args[1]));
//        }
//
////        ChangeTimer entry = new ChangeTimer(1000, "/media/inkbox/study/娱乐/图片/壁纸");
////
////        ChangeTimer timer = new ChangeTimer();
////        timer.schedule(entry, 0, 5000);
//    }


    @Override
    public void run() {
        Runtime runtime = Runtime.getRuntime();
        try {
            int index = random.nextInt(files.length);

            Process process = runtime.exec("gsettings set com.deepin.wrap.gnome.desktop.background picture-uri \"" + base + File.separator + files[index] + "\"");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
            }
            if (listener!=null)
                listener.change( base + File.separator + files[index]);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
