package cn.inkroom.web.teach.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author 墨盒
 * @version 1.0
 * @Date 2017/9/20
 * @Time 23:01
 * @Descorption
 */
public class VideoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //获取开始字节
//        long start = Long.valueOf(req.getHeader("Range").replace("bytes=", "").replace("-", ""));
//        boolean hidden = req.getParameter("hidden") == null ? false : Boolean.valueOf(req.getParameter("hidden"));
//
//        //判断资源请求格式是否错误
//        //获取文件资源实体对象
//        String[] ns = req.getParameter("file").split("/");
//        StringBuilder realPath = null;
//        if (hidden) {
//            realPath = new StringBuilder(VideoController.HIDDEN_PATH);
//        } else
//            realPath = new StringBuilder(VideoController.BASE_PATH);
//        for (String n : ns) {
//            File temp = new File(realPath.toString());
//            realPath.append("/" + temp.listFiles()[Integer.parseInt(n)].getName());
//        }
////        UploadFiles uploadFile = examResService.getResource(RES_TYPE_MAP.get(type),id);
//        //如果文件未能查询到则返回空数据
//        //通过地址获取文件对象
//        File downloadFile = new File(realPath.toString());
//        HttpStatus statusCode = HttpStatus.NOT_FOUND;
//        //读取文件流
//        byte[] body = null;
//        InputStream is = null;
//        HttpHeaders headers = null;
//        is = new FileInputStream(downloadFile);
//        System.out.println(start + "  跳过的字节 - " + is.skip(start));
//        //获取本次传输长度
//        if (is.available() > (2064770L)) {
//            body = new byte[2064770];
//        } else {
//            if (start + is.available() == downloadFile.length())
//                body = new byte[is.available() - 1];
//            else body = new byte[is.available()];
//        }
//        resp.addHeader("Last-Modified", new java.util.Date(downloadFile.lastModified()).toString());
//        resp.addHeader("Accept-Ranges", "bytes");
//        resp.addHeader("Content-Type", "video/mp4;charset=utf-8");
//        resp.setStatus(206);
//        if (start == 0) {
//            resp.addHeader("Content-Length", Long.toString(downloadFile.length()));
//            resp.addHeader("Content-Range", "bytes " + start + "-" + (downloadFile.length() - 1) + "/" + downloadFile.length());
////            resp.getOutputStream().flush();
////            return;
//        } else {
//            resp.addHeader("Content-Length", String.valueOf(body.length + 1));
//            resp.addHeader("Content-Range", "bytes " + start + "-" + (start + body.length) + "/" + ((start + body.length + 1) > downloadFile.length() ? downloadFile.length() : (start + body.length + 1)));
//        }
//
//        System.out.println(start + "  本次的长度 = " + is.read(body));
//        //设置报文和状态
//        headers = new HttpHeaders();
//
////            headers.add("Content-Type", new MimetypesFileTypeMap().getContentType(downloadFile));
////            headers.add("Content-Disposition", "attchement;filename=" + downloadFile.getName());
//
//        System.out.println(start + "  开始传输");
//        resp.getOutputStream().write(body);
//        System.out.println(start + "  传输完成");

//        long min = 4792351L;
//        //获取需要的字节】
//
//        String range = request.getHeader("Range");
//        System.out.println("range = " + range);
//        range = range.substring(range.indexOf("=") + 1);
//
//        long count = 0;
//        long start = 0;
//        String bytes[] = range.split("-");
//        if (bytes.length == 1) {//从头开始
//            try {
//                FileInputStream input = new FileInputStream(new File("/media/inkbox/study/娱乐/视频/动漫/冰菓/08.mp4"));
//
//                OutputStream out = response.getOutputStream();
//                byte[] b = new byte[20 * 1024];
//                int length = 0;
//                while ((length = input.read(b)) != -1 && count < min) {
//                    out.write(b, 0, length);
//                    count += length;
//                }
////                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//
//        } else if (bytes.length == 2) {//跳过一部分
//            start = Long.parseLong(bytes[0]);
//            try {
//                FileInputStream input = new FileInputStream(new File("/media/inkbox/study/娱乐/视频/动漫/冰菓/08.mp4"));
//                OutputStream out = response.getOutputStream();
//                byte[] b = new byte[20 * 1024];
//                int length = 0;
//                input.skip(start);
//                while ((length = input.read(b)) != -1 && count < min) {
//                    out.write(b, 0, length);
//                    count += length;
//                }
////                out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("count = " + count);
//        response.setHeader("Content-Range", "bytes " + start + "-" + (count + start) + "/" + count);
//        response.setHeader("Accept-Ranges", "bytes");
//
//        response.setContentLength((int) count);
//        response.setStatus(206);
//        System.out.println(response.toString());
//        try {
//            response.getOutputStream().close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
