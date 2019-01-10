package cn.inkroom.software.alert;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 墨盒 on 16:08.
 */
public class Entry {
    public static void main(String[] args) {

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年M月d日 HH:mm:ss");
        System.out.println(format.format(date));
//        System.out.println(format.toLocalizedPattern());
//        Print print = new newPrint();
//        InkThread thread1 = new InkThread(print, 1, true);
//        thread1.setName("first");
//        InkThread thread2 = new InkThread(print, 3, false);
//        thread2.setName("secoend");
//
//        thread1.start();
//        thread2.start();
    }
}

class InkThread extends Thread {

    private Print print;
    private int value;
    private static boolean flag = true;
    private boolean f = false;

    public InkThread(Print print, int value, boolean flag) {
        this.print = print;
        this.value = value;
        this.f = flag;
    }

    @Override
    public void run() {
//        boolean flag = true;
        synchronized (print) {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (f == flag) {
//                    System.out.println(this.getName() + "  " + f + "  1");
                    try {
//                        print.notify();
                        f = !flag;
                        print.wait();
//                        System.out.println(this.getName() + "  " + f + "  2");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
//                System.out.println(this.getName() + "  " + f + "  3");
//                System.out.println(value);
                print.print(value);
                print.notify();
//                System.out.println(this.getName() + "  " + f + "  4");
                try {
//                    System.out.println(this.getName() + "  " + f + "  5");
                    print.wait();
//                    System.out.println(this.getName() + "  " + f + "  6");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Print {
    public void print(int value) {
        System.out.println(this.getClass() + "   " + value);
    }
}

class newPrint extends Print {
    private BufferedWriter out = null;

    @Override
    public synchronized void print(int value) {
//        super.print(value);
        try {
//            if (out == null) {
            out = new BufferedWriter(new FileWriter(new File("E:\\new.txt"), true));
//            }
            out.write(String.valueOf(value));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}