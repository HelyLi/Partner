package com.hhly.partner.presentation.utils;

import android.content.Context;

/**
 * 本地保存用户的相关资料
 * Created by dell on 2017/4/28.
 */

public class UserPrefsUtil {
    public static final String USER_FILE = "user_file";
    public static final String USER_NAME_KEY = "user_nae_key";
    public static final String USER_PWD_KEY = "user_pwd_key";
    public final static String USER_PASSWORD_SEPARATOR = "_partner_separator_";
    private SharedPrefsUtil mSharedPrefsUtil;
    private static volatile UserPrefsUtil mInstance;

    private UserPrefsUtil() {
        mSharedPrefsUtil = SharedPrefsUtil.getInstance();
    }

    public static UserPrefsUtil getInstance() {
        if (mInstance == null) {
            synchronized (SearchRecordPrefsUtil.class) {
                mInstance = new UserPrefsUtil();
            }
        }
        return mInstance;
    }

    public void saveUserName(Context context, String userName) {
        SharedPrefsUtil.setFileName(USER_FILE);
        mSharedPrefsUtil.put(context, USER_NAME_KEY, userName);
    }

    public String getUserName1(Context context) {
        SharedPrefsUtil.setFileName(USER_FILE);
        return (String) mSharedPrefsUtil.get(context, USER_NAME_KEY, "");
    }

    public void clear(Context context) {
        SharedPrefsUtil.setFileName(USER_FILE);
        mSharedPrefsUtil.clear(context);
    }

    private String compoundUserPwd(String username, String pwd) {
        StringBuilder sb = new StringBuilder();
        sb.append(username).append(USER_PASSWORD_SEPARATOR).append(pwd);
        return sb.toString();
    }

    public void saveUserNamePwd(Context context, String username, String pwd) {
        SharedPrefsUtil.setFileName(USER_FILE);
        mSharedPrefsUtil.put(context, USER_PWD_KEY, compoundUserPwd(username, pwd));
    }

    public String getUserName(Context context) {
        SharedPrefsUtil.setFileName(USER_FILE);
        String compound = (String) mSharedPrefsUtil.get(context, USER_PWD_KEY, "");
        String[] arr = compound.split(USER_PASSWORD_SEPARATOR);
        if (arr.length == 2) {
            return arr[0];
        } else {
            return "";
        }
    }

    public String getUserPwd(Context context) {
        SharedPrefsUtil.setFileName(USER_FILE);
        String compound = (String) mSharedPrefsUtil.get(context, USER_PWD_KEY, "");
        String[] arr = compound.split(USER_PASSWORD_SEPARATOR);
        if (arr.length == 2) {
            return arr[1];
        } else {
            return "";
        }
    }
}
