package com.hhly.partner.presentation.view.banner;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GetIndexlbtResp;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.product.ProductDetailsActivity;

/**
 * 产品列表头部holder
 * Created by dell on 2017/4/24.
 */

public class ProductBannerHolder implements Holder<GetIndexlbtResp.DataBean.ListBean> {

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
    public void UpdateUI(final Context context, int position, final GetIndexlbtResp.DataBean.ListBean bean) {
        Glide.with(context)
                .load(bean.getPictureUrl())
                .placeholder(R.drawable.ic_action_banner_loading)
                .error(R.drawable.ic_action_banner_loading)
                .fitCenter()
                .into(mImageView);
        mImageView.setTag(R.id.item_data_key, bean);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean != null && !TextUtils.isEmpty(bean.getJumpUrl())) {
                    if (bean.getJumpUrl().lastIndexOf("gamelist") != -1) {
                        ActivityCompat.startActivity(mContext, ProductDetailsActivity.getCallIntent(
                                mContext,
                                Integer.parseInt(bean.getJumpUrl().substring(bean.getJumpUrl().length() - 6, bean.getJumpUrl().length())),
                                bean.getRemarks()), null);
                    } else {
                        ToastUtil.showShort(context, context.getString(R.string.partner_data_exception));
                    }
                }
            }
        });
    }
}
