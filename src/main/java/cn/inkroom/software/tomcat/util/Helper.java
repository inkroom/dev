package cn.inkroom.software.tomcat.util;

import javax.naming.ConfigurationException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.util.Base64;

/**
 * @author 墨盒
 */
public class Helper {
//    public static String toHexString(byte[] b) {
//        StringBuilder sb = new StringBuilder(b.length * 2);
//        for (int i = 0; i < b.length; i++) {
//            sb.append(HEXCHAR[(b[i] & 0xf0) >>> 4]);
//            sb.append(HEXCHAR[b[i] & 0x0f]);
//        }
//        return sb.toString();
//    }
//    public void saveKey(KeyPair keyPair, String publicKeyFile,
//                        String privateKeyFile) throws ConfigurationException {
//        PublicKey pubkey = keyPair.getPublic();
//        PrivateKey prikey = keyPair.getPrivate();
//
//        // save public key
//
//        // save private key
//        PropertiesConfiguration privateConfig = new PropertiesConfiguration(
//                privateKeyFile);
//        privateConfig.setProperty("PRIVATEKEY",
//                toHexString(prikey.getEncoded()));
//        privateConfig.save();
//    }

    /**
     * 从xml密钥转到java密钥
     *
     * @param xml rsa的xml密钥
     * @return
     */
    public static PrivateKey decodePrivateKeyFromXml(String xml) {
        xml = xml.replaceAll("\r", "").replaceAll("\n", "");
        BigInteger modulus = new BigInteger(1, Base64.getDecoder().decode(StringHelper
                .GetMiddleString(xml, "<Modulus>", "</Modulus>")));
        BigInteger publicExponent = new BigInteger(1,
                Base64.getDecoder().decode(StringHelper.GetMiddleString(xml,
                        "<Exponent>", "</Exponent>")));
        BigInteger privateExponent = new BigInteger(1,
                Base64.getDecoder().decode(StringHelper.GetMiddleString(xml, "<D>",
                        "</D>")));
        BigInteger primeP = new BigInteger(1, Base64.getDecoder().decode(StringHelper
                .GetMiddleString(xml, "<P>", "</P>")));
        BigInteger primeQ = new BigInteger(1, Base64.getDecoder().decode(StringHelper
                .GetMiddleString(xml, "<Q>", "</Q>")));
        BigInteger primeExponentP = new BigInteger(1,
                Base64.getDecoder().decode(StringHelper.GetMiddleString(xml, "<DP>",
                        "</DP>")));
        BigInteger primeExponentQ = new BigInteger(1,
                Base64.getDecoder().decode(StringHelper.GetMiddleString(xml, "<DQ>",
                        "</DQ>")));
        BigInteger crtCoefficient = new BigInteger(1,
                Base64.getDecoder().decode(StringHelper.GetMiddleString(xml,
                        "<InverseQ>", "</InverseQ>")));

        RSAPrivateCrtKeySpec rsaPriKey = new RSAPrivateCrtKeySpec(modulus,
                publicExponent, privateExponent, primeP, primeQ,
                primeExponentP, primeExponentQ, crtCoefficient);

        KeyFactory keyf;
        try {
            keyf = KeyFactory.getInstance("RSA");
            return keyf.generatePrivate(rsaPriKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
