package com.hhly.partner.presentation.rxbus.event;

import android.support.annotation.IntDef;

import com.hhly.partner.data.net.protocol.game.GetIndexlbtResp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * 产品bannerEvent
 * Created by dell on 2017/4/24.
 */

public class ProductBannerEvent {
    //下载数据
    public static final int REFRESH_EVENT = 301;
    //刷新数据
    public static final int DOWNLOAD_EVENT = 302;

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    @IntDef({REFRESH_EVENT, DOWNLOAD_EVENT})
    public @interface ProductBannerEventType {

    }

    public int eventType;
    public List<GetIndexlbtResp.DataBean.ListBean> list;

    public ProductBannerEvent(@ProductBannerEventType int eventType,List<GetIndexlbtResp.DataBean.ListBean> list ) {
        this.eventType = eventType;
        this.list = list;
    }

    public ProductBannerEvent(@ProductBannerEventType int eventType) {
        this.eventType = eventType;
    }

}
