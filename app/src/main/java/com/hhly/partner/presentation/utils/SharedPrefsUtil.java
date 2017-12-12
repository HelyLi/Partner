package com.hhly.partner.presentation.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * 本地SharedPreferences 存储
 */
public class SharedPrefsUtil {
    private static volatile SharedPrefsUtil mInstance;
    private static String mFileName;

    private SharedPrefsUtil() {
    }

    public static SharedPrefsUtil getInstance() {
        if (mInstance == null) {
            synchronized (SharedPrefsUtil.class) {
                if (mInstance == null) {
                    mInstance = new SharedPrefsUtil();
                }
            }
        }
        return mInstance;
    }

    public static void setFileName(String mFileName) {
        SharedPrefsUtil.mFileName = mFileName;
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context context
     * @param key key
     * @param object Object
     */
    public void put(Context context, String key, Object object) {
        SharedPreferences sp = context.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }
        editor.apply();
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context       context
     * @param key           key
     * @param defaultObject 默认的值
     * @return Object
     */
    public Object get(Context context, String key, Object defaultObject) {
        SharedPreferences sp = context.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }
        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context context
     * @param key     key
     */
    public void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清除所有数据
     *
     * @param context context
     */
    public void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context context
     * @param key key
     * @return true 存在
     */
    public boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context context
     * @return Map
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sp = context.getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        return sp.getAll();
    }
}
