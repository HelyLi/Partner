package com.hhly.partner.presentation.utils;

import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by HELY on 2016/5/27.
 * 32位加密
 */

public class MD5 {

    private String md5_32;
    private String md5_16;

    public MD5(String password) {
        if (TextUtils.isEmpty(password)) {
            return ;
        }
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(password.getBytes());
            md5_32 = byte2String(mDigest.digest());
            md5_16= md5_32.substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            md5_32 = String.valueOf(password.hashCode());
        }
    }
    
    private static String byte2String(byte[] b){
        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                buf.append("0");
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    public String getMd5_32(){return md5_32.toUpperCase();}

    public String getMd5_16(){return md5_16.toUpperCase();}

}
