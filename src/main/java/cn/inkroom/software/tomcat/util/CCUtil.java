package cn.inkroom.software.tomcat.util;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * @author 墨盒
 */
public class CCUtil {
    /**
     * 解密
     * @param src byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String password) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");//返回实现指定转换的 Cipher 对象
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }
    public static long bytes2Long(byte[] byteNum) {
        long num = 0;
        for (int ix = 0; ix < 8; ++ix) {
            num <<= 8;
            num |= (byteNum[ix] & 0xff);
        }
        return num;
    }
    public static void main(String[] args) {
        //读取证书

//        try {
//            File file= new File("/home/inkbox/log/show");
//            FileInputStream inputStream = new FileInputStream(file);
//            byte bytes[]=new byte[(int) file.length()];
//            int count = inputStream.read(bytes);
//            if (count>0){
//                //首先是6个字节标志位
//                byte flat[]=new byte[6];
//                System.arraycopy(bytes,0,flat,0,6);
//                System.out.println(new String(flat));
//
//                //获取数据长度
//                byte len[] =new byte[2];
//                System.arraycopy(bytes,6,len,0,2);
//                System.out.println(bytes2Long(len));
//
//                String s = new String(bytes);
////                System.out.println(s);
//            }


            //获取rsa秘钥
//
//            File file = new File("/media/inkbox/software/software/linux/FBI.key");
//            FileInputStream inputStream = new FileInputStream(file);
//            byte bytes[] = new byte[(int) file.length()];
//
//            System.out.println(file.length());
//            System.out.println(file.length()-30);
//            int count = inputStream.read(bytes);
//            if (count!=0){
//                System.out.println(count);
//                AESEncryptUtil util = new AESEncryptUtil("d6a4d3551d609e2553e620128684cbc8fb454887d24193c6c80705540f4e500e");
//                //解密32个字节往后的内容
//                byte data[]=new byte[bytes.length-32];
//
//                System.arraycopy(bytes,30,data,0,data.length);
//
//
////                for (int i = 0; i < data.length/10; i++) {
////                    System.out.println(data[i]);
////                }
//
//                String rsaKey = util.decrypt((data));
//
//                System.out.println(rsaKey);
//
//
//            }

            //使用rsa密钥解密






//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
