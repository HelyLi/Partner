package com.hhly.partner.presentation.utils;

import android.content.Context;

import com.hhly.partner.data.net.protocol.game.GameDataByNameResp;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品搜索历史记录PrefsUtil
 * Created by dell on 2017/4/28.
 */

public class SearchRecordPrefsUtil {

    //产品搜索历史记录file
    public final static String PRODUCT_SEARCH_RECORD_FILE = "product_search_record_file";
    //产品搜索历史记录key
    public final static String PRODUCT_SEARCH_RECORD_KEY = "product_search_record_key";
    //产品搜索历史记录长度key
    public final static String PRODUCT_SEARCH_RECORD_SIZE_KEY = "product_search_record_size_key";
    //产品搜索历史记录Name和Id的分割符
    public final static String PRODUCT_SEARCH_RECORD_SEPARATOR = "_separator_";

    private SearchRecordPrefsUtil() {
        mSharedPrefsUtil = SharedPrefsUtil.getInstance();
    }

    private static volatile SearchRecordPrefsUtil mInstance;
    private SharedPrefsUtil mSharedPrefsUtil;

    public static SearchRecordPrefsUtil getInstance() {
        if (mInstance == null) {
            synchronized (SearchRecordPrefsUtil.class) {
                mInstance = new SearchRecordPrefsUtil();
            }
        }
        return mInstance;
    }

    /**
     * 取出List<String>
     *
     * @param context context
     * @return List<String> RecordInfoList
     */
    public List<String> getRecordInfoList(Context context) {
        SharedPrefsUtil.setFileName(PRODUCT_SEARCH_RECORD_FILE);
        List<String> strList = new ArrayList<>();
        int size = (int) mSharedPrefsUtil.get(context, PRODUCT_SEARCH_RECORD_SIZE_KEY, 0);
        for (int i = 0; i < size; i++) {
            strList.add((String) mSharedPrefsUtil.get(context, PRODUCT_SEARCH_RECORD_KEY + i, ""));
        }
        return strList;
    }


    /**
     * 存储List<String>
     *
     * @param context context
     * @param strList 对应需要存储的List<String>
     */
    public void putRecordInfoList(Context context, List<String> strList) {
        if (null == strList) {
            return;
        }
        SharedPrefsUtil.setFileName(PRODUCT_SEARCH_RECORD_FILE);
        // 保存之前先清理已经存在的数据，保证数据的唯一性
        mSharedPrefsUtil.clear(context);
        int size = strList.size();
        mSharedPrefsUtil.put(context, PRODUCT_SEARCH_RECORD_SIZE_KEY, size);
        for (int i = 0; i < size; i++) {
            mSharedPrefsUtil.put(context, PRODUCT_SEARCH_RECORD_KEY + i, strList.get(i));
        }
    }

    public void clear(Context context) {
        SharedPrefsUtil.setFileName(PRODUCT_SEARCH_RECORD_FILE);
        mSharedPrefsUtil.clear(context);
    }

    /**
     * 将gameName和id合成一条记录
     *
     * @param gameName gameName
     * @param gameId gameId
     * @return 一条记录
     */
    public static String compoundRecordContent(String gameName, int gameId) {
        SharedPrefsUtil.setFileName(PRODUCT_SEARCH_RECORD_FILE);
        StringBuilder sb = new StringBuilder();
        sb.append(gameName).append(PRODUCT_SEARCH_RECORD_SEPARATOR).append(gameId);
        return sb.toString();
    }

    /**
     * 将保存的记录解析成bean
     *
     * @param recordContent 保存的记录
     * @return DataBean
     */
    public static GameDataByNameResp.DataBeanX.DataBean parseRecordContent(String recordContent) {
        GameDataByNameResp.DataBeanX.DataBean bean = new GameDataByNameResp.DataBeanX.DataBean();
        if (recordContent.contains(PRODUCT_SEARCH_RECORD_SEPARATOR)) {
            String[] arr = recordContent.split(PRODUCT_SEARCH_RECORD_SEPARATOR);
            bean.setNAME(arr[0]);
            bean.setID(Integer.parseInt(arr[1]));
        }
        return bean;
    }
}
