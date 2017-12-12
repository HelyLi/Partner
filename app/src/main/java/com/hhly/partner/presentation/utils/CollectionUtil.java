package com.hhly.partner.presentation.utils;

import java.util.Collection;

/**
 * 集合工具类
 * Created by Simon on 2016/11/24.
 */

public class CollectionUtil {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection){
        return collection != null && !collection.isEmpty();
    }
}
