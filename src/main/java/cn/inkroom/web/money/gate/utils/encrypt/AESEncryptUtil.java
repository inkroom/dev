package cn.inkroom.web.money.gate.utils.encrypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
public class AESEncryptUtil implements DecryptAble {

    private String key;
    private String parameter;

    public AESEncryptUtil(String key, String parameter) {
        this.key = key;
        this.parameter = parameter;

        //测试秘钥是否符合规则
        encrypt("test");
    }

    /**
     * AES加密
     *
     * @param sSrc
     * @return
     */
    public String encrypt(String sSrc) {
        String result = null;
        try {
            Cipher cipher;
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(parameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
            result = new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 此处使用BASE64做转码。
        return result;

    }

    /**
     * aes解密
     *
     * @param sSrc
     * @return
     */
    public String decrypt(String sSrc) {
        try {
            byte[] raw = key.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(parameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original, "utf-8");
            return originalString;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        AESEncryptUtil util = new AESEncryptUtil("abcdef0123452126", "0125556789abcdef");
        String result = util.encrypt("ink");
        System.out.println("加密结果" + result);
        System.out.println("解密结果" + util.decrypt(result));
    }
}
