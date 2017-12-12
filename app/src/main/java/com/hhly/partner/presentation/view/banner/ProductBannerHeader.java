package com.hhly.partner.presentation.view.banner;

import android.content.Context;
import android.util.AttributeSet;

import com.bigkoo.convenientbanner.holder.Holder;
import com.hhly.partner.data.net.protocol.game.GetIndexlbtResp;

/**
 * 产品列表头部
 * Created by dell on 2017/4/24.
 */

public class ProductBannerHeader extends BannerHeader<GetIndexlbtResp.DataBean.ListBean>{
    public ProductBannerHeader(Context context) {
        super(context);
    }
    public ProductBannerHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Holder createHolder() {
        return new ProductBannerHolder();
    }
}
