package com.hhly.partner.presentation.view.product;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GameDataInfoResp;
import com.hhly.partner.presentation.utils.GlideUtils;
import com.hhly.partner.presentation.view.extension.ExtensionActivity;
import com.hhly.partner.presentation.view.widget.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 游戏详情headerView
 * Created by dell on 2017/4/25.
 */

public class ProductDetailsHeaderView extends FrameLayout {
    @BindView(R.id.big_game_logo_img)
    ImageView mBigGameLogoImg;
    @BindView(R.id.game_logo_img)
    RoundImageView mGameLogoImg;
    @BindView(R.id.game_name_tv)
    TextView mGameNameTv;
    @BindView(R.id.proxy_num_tv)
    TextView mProxyNumTv;
    @BindView(R.id.proportion_tv)
    TextView mProportionTv;
    private GameDataInfoResp.DataBeanX.DataBean mData;
    private Context mContext;

    public ProductDetailsHeaderView(Context context) {
        this(context, null);
    }

    public ProductDetailsHeaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProductDetailsHeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View headerView = LayoutInflater.from(context).inflate(R.layout.product_details_header_layout, null);
        addView(headerView);
        ButterKnife.bind(this, headerView);

    }

    @OnClick(R.id.generalize_btn)
    public void onViewClicked() {
        if (mData != null) {
            mContext.startActivity(ExtensionActivity.getCallIntent(mContext, ExtensionActivity.EXTENSION_PRODUCT, mData.getID()));
        }
    }

    public void updateInfo(GameDataInfoResp.DataBeanX.DataBean data) {
        if (data == null)
            return;
        mData = data;
        GlideUtils.loadImageViewLoading(mContext, data.getTITTLEIMG_URL(), mBigGameLogoImg,
                R.drawable.ic_action_banner_loading, R.drawable.ic_action_banner_loading);
        GlideUtils.loadImageViewLoading(mContext, data.getICON_URL(), mGameLogoImg, R.drawable.ic_product_action_game_loading,
                R.drawable.ic_product_action_game_loading);
        mGameNameTv.setText(data.getNAME());
        mProxyNumTv.setText(mContext.getString(R.string.partner_product_proxy_person_num, data.getAGENT()));
    }
}
