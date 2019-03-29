package cc.zenking.cloud.subojetstudy.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * @Auther: chengjunchao
 * @Date: 2018/6/10 14:13
 * @Description:
 */
public class DesUtil {
    private static final String DES = "DES";
    private static final String KEY = "64128382";

    public DesUtil() {
    }

    public static String encrypt(String data) throws Exception {
        byte[] bt = encrypt(data.getBytes(), "64128382".getBytes());
        String strs = (new BASE64Encoder()).encode(bt);
        return strs;
    }

    public static String decrypt(String data) throws IOException, Exception {
        if (data == null) {
            return null;
        } else {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] buf = decoder.decodeBuffer(data);
            byte[] bt = decrypt(buf, "64128382".getBytes());
            return new String(bt);
        }
    }

    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(1, securekey, sr);
        return cipher.doFinal(data);
    }

    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        SecureRandom sr = new SecureRandom();
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(2, securekey, sr);
        return cipher.doFinal(data);
    }
}
