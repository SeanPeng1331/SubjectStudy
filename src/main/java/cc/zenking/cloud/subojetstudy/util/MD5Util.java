package cc.zenking.cloud.subojetstudy.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 *
 * @author cjc
 * @date
 */
public class MD5Util {

    static Log logger = LogFactory.getLog(MD5Util.class);

    /**
     * MD5加密<br>
     *
     * @param strObj
     * @return String
     */
    public static String MD5String(String strObj) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.info("初始化MD5失败...", e);
            return "";
        }
        String resultString = null;
        byte[] date;
        date = md.digest(strObj.getBytes());
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < date.length; offset++) {
            i = date[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        resultString = buf.toString();
        return resultString;
    }

    public static byte[] MD5Bytes(String strObj) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logger.info("初始化MD5失败...", e);
            return null;
        }
        byte[] date;
        date = md.digest(strObj.getBytes());
        return date;
    }
}
