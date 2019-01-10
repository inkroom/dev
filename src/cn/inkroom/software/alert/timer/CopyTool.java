package cn.inkroom.software.alert.timer;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 墨盒 on 14:09.
 * 文件复制
 */
public class CopyTool {

    private File input;
    private String file;
    private CopyThread copyThreads[];
//    private long length;

    public static void main(String[] args) {
        File file = new File("E:\\娱乐\\视频\\2017.TS.390影视.mp4");
        CopyTool tool = new CopyTool(file, "E:\\copy.mp4", 5);
        tool.start();
    }

    public CopyTool(File input, String file, int size) {
        this.input = input;
        this.file = file;
        long length = input.length();

        //计算字节分配
        long baseLength = input.length() / size;//每个线程需要写入的字节数
        copyThreads = new CopyThread[size];
        for (int i = 0; i < copyThreads.length; i++) {
            if (i == copyThreads.length - 1) {//最后一个线程
                copyThreads[i] = new CopyThread(i, i * baseLength, length - (i) * baseLength);
            } else {
                copyThreads[i] = new CopyThread(i, i * baseLength, baseLength);
            }
        }
    }

    public void start() {
        if (copyThreads != null) {
            for (CopyThread thread :
                    copyThreads) {
                new Thread(thread).start();
            }
//            for (int i = 0; i < copyThreads.length; i++) {
//                if (copyThreads[i] != null)
//                    copyThreads[i].start();
//            }
        }
    }

    private class CopyThread implements Runnable {

        private long start;
        private long length;
        private int index;

        private CopyThread(int index, long start, long length) {
            this.start = start;
            this.index = index;
            this.length = length;
//            System.out.println("start = " + start + "   length = " + length);
        }

        @Override
        public void run() {
            try {
                //创建随机输入输出流
                RandomAccessFile reader = new RandomAccessFile(input, "r");
                RandomAccessFile randomAccessFile = new RandomAccessFile(new File(file), "rw");
                //跳过开始字节
                reader.seek(start);
                randomAccessFile.seek(start);

                int temp;
                long totalLength = 0;
                byte bytes[] = new byte[4 * 1024];
                while ((temp = reader.read(bytes)) != -1 && totalLength < length) {
                    randomAccessFile.write(bytes, 0, temp);
                    totalLength += temp;
                }
                randomAccessFile.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
