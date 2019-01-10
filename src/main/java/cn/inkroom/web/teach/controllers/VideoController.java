package cn.inkroom.web.teach.controllers;

import cn.inkroom.web.teach.config.DResourceHttpRequestHandler;
import cn.inkroom.web.teach.interceptors.Inc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/14
 * @Time 21:21
 * @Descorption
 */
@Controller
public class VideoController {
    @Autowired
    private HttpServletRequest request;
//    public static final String BASE_PATH = "/media/inkbox/study/娱乐/视频";
//    public static final String HIDDEN_PATH = "D:\\temp\\qq\\245900986\\Audio\\AudioInfo";

    @Value("${directory}")
    private String directory;

    @RequestMapping("list")
    public String list(String file, String i) {
        Integer index = 0;
        if (i != null) {
            index = Integer.parseInt(i);
        }
        String dirs[] = directory.split(",");
        ArrayList<File> files = new ArrayList<>();
        if (file == null) {
            File fs[] = new File(dirs[index]).listFiles();
            for (File f : fs) {
                files.add(f);
            }
        } else {
            String[] ns = file.split("/");
            StringBuilder realPath = new StringBuilder(dirs[index]);

            for (String n : ns) {
                File temp = new File(realPath.toString());
                realPath.append("/").append(temp.listFiles()[Integer.parseInt(n)].getName());
            }
            File filess[] = new File(realPath.toString()).listFiles();
            for (File file1 : filess) {
                files.add(file1);
            }
        }
        request.setAttribute("files", files);
        request.setAttribute("i", index);
        request.setAttribute("path", file == null ? "" : (file + "/"));

        return "list";
    }



    @RequestMapping("show")
    public String show(String path) {
        System.out.println(path);
        // TODO: 2017/9/16 部分资源会出现404
        request.setAttribute("src", path);
        return "video";
    }


    @Autowired
    HttpServletResponse response;

    @RequestMapping("/v")
    @ResponseBody
    public void v(HttpServletRequest request, String path, String i) {
        String[] ns = path.split("/");
        System.out.println(ns.length);
        StringBuilder realPath = null;
        Integer index = Integer.parseInt(i);
        String dirs[] = directory.split(",");
        realPath = new StringBuilder(dirs[index]);
        for (String n : ns) {
            File temp = new File(realPath.toString());
            System.out.println("当前路径 = " + temp.getAbsolutePath() );
            File files [] = temp.listFiles();
            System.out.println( "  找到文件 == " + temp.listFiles()[Integer.parseInt(n)].getName());
            realPath.append(File.separatorChar + temp.listFiles()[Integer.parseInt(n)].getName());
        }
        System.out.println("realPath = " + realPath.toString());
        File file = new File(realPath.toString());
        if (file.isDirectory()) {
            try {
                response.sendError(404);
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        long max = 4792351L;
        long start = 0;

        long count = 0;

        String range = request.getHeader("Range");
        System.out.println("range = " + range);
        range = range.substring(range.indexOf("=") + 1);

        //计算会读取多少个字节
        String bytes[] = range.split("-");
        long last = file.length() - start;
        int buffer = 20 * 1024;

        if (last > max) {
            count = (max / buffer) * buffer;
            if (last - count > 0) {//此时还会再读取一次
                System.out.println("最后一次剩下=" + (last - count));
                if (last - count > buffer) {//装满buffer
                    count += buffer;
                } else {
                    count += (last - count);
                }
            }

        } else {
            count = last;

        }
//        if (bytes.length == 2) {
        start = Long.parseLong(bytes[0]);
//        }
        System.out.println("start" + start);

        //获取需要的字节】
        response.setStatus(206);
        response.setHeader("Accept-Ranges", "bytes");

        response.setContentType("video/mp4;charset=utf-8");
        response.setDateHeader("Last-Modified", file.lastModified());
//        response.setHeader("Last-Modified", new Date(file.lastModified()).toString());

        response.setHeader("Content-Range", "bytes " + start + "-" + (file.length() - 1) + "/" + file.length());
//        response.setHeader("Content-Range", "bytes " + start + "-" + (count + start - 1) + "/" + file.length());
        response.setContentLengthLong(file.length() - start);


        System.out.println("计算后的count=" + count);


//        if (bytes.length == 1) {//从头开始，全部下载
        try {
            start = Long.parseLong(bytes[0]);
//                response.setHeader("Content-Range", "bytes " + start + "-" + (count + start-1) + "/" + file.length());
//                response.setContentLength((int) count);
//                FileInputStream input = new FileInputStream(file);

            RandomAccessFile input = new RandomAccessFile(file, "r");

            OutputStream out = response.getOutputStream();
            input.seek(start);


            byte[] b = new byte[buffer];
            int length = 0;
            count = 0;
            while ((length = input.read(b)) != -1 ) {
//                    System.out.println("count =" + count + "  length=" + length);
                out.write(b, 0, length);
//                    list.add(bytes);
//                    for (int i = 0; i < length; i++) {
//                        list.add(b[i]);
//                    }
                count += length;
            }
            System.out.println("实际的count = " + count);
            // TODO: 18-3-30 依然不清楚content-Range用法，但是在局域网带宽允许的情况下可以瞬间加载完成

//                Byte[] b1 = new Byte[(int) list.size()];
//
//                b1 = list.toArray(b1);
//                out.write(b1, 0, list.size());
//                out.close();
        } catch (IOException e) {
//                response.setHeader("Content-Range", "bytes " + start + "-" + (file.length()-1) + "/" + file.length());
//                response.setContentLengthLong(file.length());
            e.printStackTrace();
        }


//        }
        System.out.println("count = " + count);

        System.out.println(response.toString());
//        try {
//            response.getOutputStream().close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
