package cn.inkroom.web.money.gate.utils.encrypt;

/**
 * 可解密接口
 */

public interface DecryptAble {


    /**
     * 解密
     * @param sSrc
     * @return
     */
     String decrypt(String sSrc);
}
