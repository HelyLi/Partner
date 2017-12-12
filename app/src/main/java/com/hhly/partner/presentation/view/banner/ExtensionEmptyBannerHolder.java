package com.hhly.partner.presentation.view.banner;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.holder.Holder;
import com.hhly.partner.R;
import com.hhly.partner.presentation.view.extension.AddExtensionBannerActivity;

import static com.hhly.partner.presentation.view.extension.CustomExtensionFragment.ADD_BANNER_REQUEST_CODE;

/**
 * 产品列表头部holder
 * Created by dell on 2017/4/24.
 */

public class ExtensionEmptyBannerHolder implements Holder {
    private View mContentLayout;
    private Context mContext;

    @Override
    public View createView(Context context) {
        mContext = context;
        mContentLayout = LayoutInflater.from(context).inflate(R.layout.extension_empty_banner_holder_layout, null);
        mContentLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        return mContentLayout;
    }

    @Override
    public void UpdateUI(final Context context, int position, Object data) {
        mContentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.startActivityForResult((Activity) mContext, AddExtensionBannerActivity.getCallIntent(
                        context), ADD_BANNER_REQUEST_CODE, null);
            }
        });
    }

}
