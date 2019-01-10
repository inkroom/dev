package cn.inkroom.software.tomcat.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.LinkedList;

/**
 * @author 墨盒
 */
public class Rsa {
    public static String publicKeyString = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCISLP98M/56HexX/9FDM8iuIEQozy6kn2JMcbZS5/BhJ+U4PZIChJfggYlWnd8NWn4BYr2kxxyO8Qgvc8rpRZCkN0OSLqLgZGmNvoSlDw80UXq90ZsVHDTOHuSFHw8Bv//B4evUNJBB8g9tpVxr6P5EJ6FMoR/kY2dVFQCQM4+5QIDAQAB";
    public static String privateKeyString = "<RSAKeyValue><Modulus>t8EcBHHFOLAEJdvjCLY/n2b++OwvGyNX73Q45kurMuyY6WOaioYRWl5HyEsuL0MXv6XMYWT0/PP5YGuSH9SgH5q02ZCqo2GDIf9T8TVEZQYrUhYuWxYzIpHji23SB3xCDcW91Wcl/bBlsYUpJhQXTU0JBxKEj5ixkkmVaui1P5E=</Modulus><Exponent>AQAB</Exponent><P>zFqLoVjmWMcXjpbC1E/W+Gz18wyEBgXwvwcNphiEaS6Y3eaTY8YVc07nf6bRMOnqgyRQNQ9284m/LtdifUlxDQ==</P><Q>5jHO/22SS8eqJ/KuCq9Y9jE2Hd9sl8c47z/d271F4UgOinX4vX17+3vVyoRK2nsB1S5WcpPi+h3MEdVcUyV/lQ==</Q><DP>uxxA82g56ZnKCQYjuFCuTeM+w9921ykDSXYE2ktimghPcgaYd+e8ZkseDrTIoBfeYVuTkTHqwY0jn5kZFgxz1Q==</DP><DQ>fTKB3rJ9avHBPdDjj7Qmsl+5iy3FJheYg6c8xxJ+OzSxk+Tt0EG7eN+1O1wQShu0t3loUxDT1iz2nIxqll5CsQ==</DQ><InverseQ>W84IEIpwh/ihQxUrbqIwfgGjglW17VIKLRbzQwZJBCm/gSiv9+2oaIpCSVAjtfk6lGZmJNEHJCMVlTJ5gtKkKQ==</InverseQ><D>GKcTU7NR0uOmgs/H21zcF04xqgLB5SGuRAVzNs5zFv9wGmvPCYOoI1gfyErx1MxrDL65N13ckm1HCT0VWyjjHHAPsswLcAJuxNYvON2aJl/VA4NUIEWr5KLg2gHGkw0xrTec/EAgewaoO9kISR9WkzRxnWjpcxJR45S55pDRKfk=</D></RSAKeyValue>";

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        System.out.println(privateKeyString.length());

//        byte show[] = privateKeyString.getBytes();
//        for (int i = 0; i < 10; i++) {
//            System.out.println(show[i]);
//        }

        try {
            File file = new File("/home/inkbox/log/show");
            FileInputStream inputStream = new FileInputStream(file);

            byte bytes[] = new byte[(int) file.length()];
            System.out.println("chengdu" + bytes.length);
            if (inputStream.read(bytes) != 0) {

                for (int i = 0; i < 10; i++) {
                    System.out.println(bytes[i]);
                }

                //计算长度
                byte len[] = new byte[2];
                len[0] = bytes[6];
                len[1] = bytes[7];

                int l = len[1] << 8;
                l |= len[0];

                System.out.println("长度" + l);


                byte[] data = new byte[bytes.length - 8];

                System.arraycopy(bytes, 8, data, 0, data.length);
//                System.out.println(Base64.getEncoder().encodeToString(Helper.decodePrivateKeyFromXml(privateKeyString).getEncoded()));
//                System.out.println(new String(decryptData(bytes, Helper.decodePrivateKeyFromXml(privateKeyString))));
                System.out.println(de(data));
                //解密
//                byte data[] = new byte[bytes.length - 8];
//                System.arraycopy(bytes, 0, data, 0, data.length);
//                System.out.println(new String(decrypt(bytes, getPrivateKey("t8EcBHHFOLAEJdvjCLY/n2b++OwvGyNX73Q45kurMuyY6WOaioYRWl5HyEsuL0MXv6XMYWT0/PP5YGuSH9SgH5q02ZCqo2GDIf9T8TVEZQYrUhYuWxYzIpHji23SB3xCDcW91Wcl/bBlsYUpJhQXTU0JBxKEj5ixkkmVaui1P5E=","AQAB"))));

//                System.out.println(new String(decrypt(bytes,getPrivateKey(Base64.getEncoder().encodeToString(privateKeyString.getBytes()) ))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


//        //获取公钥
//        PublicKey publicKey=getPublicKey(publicKeyString);
//
//        //获取私钥
//        PrivateKey privateKey=getPrivateKey(privateKeyString);
//
//        //公钥加密
//        byte[] encryptedBytes=encrypt(data.getBytes(), publicKey);
//        System.out.println("加密后："+new String(encryptedBytes));
//
//        //私钥解密
//        byte[] decryptedBytes=decrypt(encryptedBytes, privateKey);
//        System.out.println("解密后："+new String(decryptedBytes));
    }

    public static String de(byte[] data) {
        StringBuilder builder = new StringBuilder();
        int max_length = 128;
        byte[] temp = new byte[128];

        int count = 0;
        try {
            System.out.println("data=" + new String(data, "GB2312"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte result[];
        LinkedList<Byte> linkedList = new LinkedList<>();
        while (count < data.length) {
            int rest = data.length - count;
            int every = rest > max_length ? max_length : rest;
            System.arraycopy(data, count, temp, 0, every);
            count += every;
            try {
                System.out.println("sdd" + new String(Base64.getEncoder().encodeToString(Helper.decodePrivateKeyFromXml(privateKeyString).getEncoded())));
//                System.out.println("密钥:"+new String(Helper.decodePrivateKeyFromXml(privateKeyString).getEncoded(),"GB2312"));
//            builder.append(new String(decrypt(temp,Helper.decodePrivateKeyFromXml(privateKeyString)),"gb2312"));
                byte test[] = decryptData((temp), Helper.decodePrivateKeyFromXml(privateKeyString));
                for (int i = 0; i < test.length; i++) {
                    linkedList.add(test[i]);
                }


//                return "";
//                System.out.println("sss" + new String(test));
//
//                builder.append(new String(((decrypt(temp, Helper.decodePrivateKeyFromXml(privateKeyString))))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            result = new byte[linkedList.size()];
            for (int i = 0; i < result.length; i++) {
                result[i] = linkedList.get(i);
            }
            System.out.println("changdu = "+result.length);
            System.out.println("result=" + new String((result), "gb2312"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * 通过私钥byte[]将公钥还原，适用于RSA算法
     *
     * @param keyBytes
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(byte[] keyBytes) throws NoSuchAlgorithmException,
            InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    public static PrivateKey getPri(String key) throws Exception {


        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    private static String RSA = "RSA";

    /**
     * 用私钥解密
     *
     * @param encryptedData 经过encryptedData()加密返回的byte数据
     * @param privateKey    私钥
     * @return
     */
    public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //将base64编码后的公钥字符串转成PublicKey实例
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    //将base64编码后的私钥字符串转成PrivateKey实例
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    //将base64编码后的私钥字符串转成PrivateKey实例
    public static PrivateKey getPrivateKey(String modulusStr, String exponentStr) throws Exception {
        BigInteger modulus = new BigInteger(modulusStr);
        BigInteger exponent = new BigInteger(exponentStr);
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(modulus, exponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(privateKeySpec);
    }

    //公钥加密
    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    //私钥解密
    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }
}
