package com.hhly.partner.presentation.utils;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * json 格式转换
 */
public class JsonUtil {

    /**
     * 格式化字符串
     * @param json
     * @return
     */
    public static String json(String json) {
        if (TextUtils.isEmpty(json)) {
            return "";
        }
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String message = jsonObject.toString(2);
                return message;
            }
            if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String message = jsonArray.toString(2);
                return message;
            }
            return json;
        } catch (JSONException e) {
            return json;
        }
    }

}