package cn.inkroom.software.chat.common.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by 墨盒 on 16:36.
 * 通过该类实现图片和base64字符串的转换
 */
public class Base64 {

    //图片转化成base64字符串
    public static String getImageStr(String path) {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try {
            in = new FileInputStream(path);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        if (data != null) {
            return replace(encoder.encode(data));
            //.replace("[\\s*\t\n\r]","");//返回Base64编码过的字节数组字符串
        }
        return null;
    }

    private static String replace(String value) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < value.length(); i++) {
            if (value.charAt(i) != 10 && value.charAt(i) != 13) {
                builder.append(value.subSequence(i, i + 1));
            }
        }
        return builder.toString();
    }

    //base64字符串转化成图片
    public static boolean generateImage(String value, String path) {   //对字节数组字符串进行Base64解码并生成图片
        if (value == null) //图像数据为空
            return false;
//        if (isBase64(value))
//            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(value);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static ImageIcon isBase64(String str) {
//        try {
//            String temp = new String(str.getBytes("base64"),"base64");
//            if(!temp.equals(str))return false;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            return false;
//        }
//        return true;
        try {
            BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(str)));
            if (null == bufImg) {
                return null;
            }
            return new ImageIcon(bufImg);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
