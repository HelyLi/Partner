package com.hhly.partner.presentation.view.banner;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GetBannerListByUseridResp;
import com.hhly.partner.presentation.view.extension.AddExtensionBannerActivity;

import static com.hhly.partner.presentation.view.extension.CustomExtensionFragment.ADD_BANNER_REQUEST_CODE;

/**
 * 产品列表头部holder
 * Created by dell on 2017/4/24.
 */

public class ExtensionBannerHolder implements Holder<GetBannerListByUseridResp.DataBeanX.DataBean> {

    private ImageView mImageView;
    private Context mContext;

    @Override
    public View createView(Context context) {
        mContext = context;
        mImageView = new ImageView(context);
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        mImageView.setImageResource(R.drawable.ic_action_banner_loading);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return mImageView;
    }

    @Override
    public void UpdateUI(final Context context, int position, final GetBannerListByUseridResp.DataBeanX.DataBean bean) {
        Glide.with(context)
                .load(bean.getICON_URL())
                .placeholder(R.drawable.ic_action_banner_loading)
                .error(R.drawable.ic_action_banner_loading)
                .fitCenter()
                .into(mImageView);
        mImageView.setTag(R.id.item_data_key, bean);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean != null) {
                    ActivityCompat.startActivityForResult((Activity)mContext, AddExtensionBannerActivity.getCallIntent(
                            context), ADD_BANNER_REQUEST_CODE, null);
                }
            }
        });
    }

}
