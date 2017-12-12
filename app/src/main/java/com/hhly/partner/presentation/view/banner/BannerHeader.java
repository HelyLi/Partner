package com.hhly.partner.presentation.view.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.widget.RatioFrameLayout;

import java.util.List;

/**
 * Created by Simon on 16/9/6.
 */
public abstract class BannerHeader<T> extends RatioFrameLayout implements BannerContract.View<T> {

    private static final long BANNER_TURNING_TIME = 5000;

    private ConvenientBanner<T> mConvenientBanner;
    private int pageCount = 0;

    public BannerHeader(Context context) {
        super(context);
        initView();
    }

    public BannerHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void startTurning() {
        if (pageCount > 1) {
            mConvenientBanner.startTurning(BANNER_TURNING_TIME);
        }
    }

    public void stopTurning() {
        if (pageCount > 1) {
            mConvenientBanner.stopTurning();
        }
    }

    private void initView() {
        inflate(getContext(), R.layout.fragment_banner_layout, this);
        widthWeight = 0.5f;
        requestLayout();
        mConvenientBanner = (ConvenientBanner<T>) findViewById(R.id.banner);
    }


    @Override
    public void showError(String msg) {
        ToastUtil.showShort(getContext(), msg);
    }

    @Override
    public void updateBanner(List<T> bannerData) {
        updateBanner(bannerData,false);
    }

    public void updateBanner(List<T> bannerData, boolean isShowEmptyView) {
        pageCount = bannerData != null ? bannerData.size() : 0;
        CBViewHolderCreator holder = null;
        if (!isShowEmptyView) {
            holder = new CBViewHolderCreator() {
                @Override
                public Object createHolder() {
                    return BannerHeader.this.createHolder();
                }
            };
        } else {
            holder = new CBViewHolderCreator() {
                @Override
                public Object createHolder() {
                    return new ExtensionEmptyBannerHolder();
                }
            };
        }
        mConvenientBanner.setPages(holder, bannerData)
                .setPageIndicator(new int[]{
                        R.drawable.indicator_banner_normal,
                        R.drawable.indicator_banner_focus})
                .setPageIndicatorAlign(0, 0, 0, DisplayUtil.dip2px(getContext(), 6), RelativeLayout.ALIGN_PARENT_BOTTOM)
                .setCanLoop(pageCount > 1);
    }

    public abstract Holder createHolder();

}
