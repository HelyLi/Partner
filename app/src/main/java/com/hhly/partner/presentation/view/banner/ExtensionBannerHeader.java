package com.hhly.partner.presentation.view.banner;

import android.content.Context;
import android.util.AttributeSet;

import com.bigkoo.convenientbanner.holder.Holder;
import com.hhly.partner.data.net.protocol.game.GetBannerListByUseridResp;

/**
 * 自定义推广页banner
 * Created by dell on 2017/5/5.
 */

public class ExtensionBannerHeader extends BannerHeader<GetBannerListByUseridResp.DataBeanX.DataBean> {
    public ExtensionBannerHeader(Context context) {
        super(context);
    }

    public ExtensionBannerHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public Holder createHolder() {
        return new ExtensionBannerHolder();
    }
}
