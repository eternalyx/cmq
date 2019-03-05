package com.cmq.utils;

import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * 密码加密&解密
 */
public class DigestUtil {

    /**
     * 利用MD5进行加密
     */
    public static String EncoderByMD5(String str){
        if(StringUtils.isEmpty(str)){
            return null;
        }

        try {
            //calculate method
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();

            return base64Encoder.encode(md5.digest(str.getBytes("UTF-8")));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断密码是否正确
     */
    public static boolean checkPassword(String pubStr, String str){
        if(StringUtils.isEmpty(pubStr)){
            return false;
        }
        return EncoderByMD5(pubStr).equals(str);
    }
}
