package cn.inkroom.software.atm;

import cn.inkroom.software.atm.controller.Controller;
import cn.inkroom.software.atm.view.LoginView;

import java.io.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Entry {

    public static void main(String[] args) {
		Controller core = new Controller();
		LoginView view = new LoginView(core);
		core.setView(view);
		view.setVisible(true);
//        new Entry().shutdown();
    }

//    public void downloadImage() {
//        try {
//            JsonObject object = (JsonObject) new JsonParser().parse(new FileReader("E:\\json.txt"));
//            JsonArray array = object.getAsJsonObject("data").getAsJsonArray("photoList");
//            String path = "E:\\娱乐\\图片\\动物\\动物";
//            int start = getSize(path) + 1;
//            for (int i = 0; i < array.size(); i++) {
//                new Down(path + (i + start) + ".png", array.get(i).getAsJsonObject().get("url").getAsString()).start();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//    public int getSize(String path) {
//        File file = new File(path);
//        file = file.getParentFile();
//        if (!file.exists())
//            System.out.println("创建 " + file.getAbsolutePath() + " " + file.mkdir());
//        return file.listFiles().length;
//    }
//    class Down extends Thread {
//        private String name;
//        private String url;
//
//        public Down(String name, String url) {
//            this.name = name;
//            this.url = url;
//        }
//
//        @Override
//        public void run() {
//            try {
//                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(this.name));
//
//                System.out.println("url=" + url);
//                URL url1 = new URL(url);
//                HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
//                connection.setRequestMethod("GET");
//
//                BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
//                byte[] bytes = new byte[1024 * 10];
//                int len;
//                while ((len = in.read(bytes)) != -1) {
//                    out.write(bytes, 0, len);
//                }
//                in.close();
//                out.close();
//                System.out.println(name + " 下载完成");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private void shutdown() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 1);
        c.set(Calendar.HOUR_OF_DAY, 6);
//        System.out.println(c.get(Calendar.HOUR_OF_DAY));

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Runtime.getRuntime().exec("cmd /c shutdown /p");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, c.getTime());
    }
}
