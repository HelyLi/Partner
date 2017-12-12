package com.hhly.partner.presentation.view.product.search;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.hhly.partner.presentation.view.product.search.SearchType.TYPE_EXTENSION;
import static com.hhly.partner.presentation.view.product.search.SearchType.TYPE_PRODUCT_SEARCH;

/**
 * 搜索的类型
 * Created by dell on 2017/5/4.
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.PARAMETER)
@IntDef({TYPE_PRODUCT_SEARCH, TYPE_EXTENSION})
public @interface SearchType {
    //搜索产品
    int TYPE_PRODUCT_SEARCH = 0X1;
    //推广搜索
    int TYPE_EXTENSION = 0X2;
}
