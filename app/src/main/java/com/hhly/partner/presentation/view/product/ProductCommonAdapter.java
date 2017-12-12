package com.hhly.partner.presentation.view.product;

import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GameTypeDataResp;
import com.hhly.partner.presentation.utils.GlideUtils;

import java.util.ArrayList;

/**
 * 产品》其他（策略、角色、射击、冒险、动作、棋牌）adapter
 * Created by dell on 2017/4/15.
 */

public class ProductCommonAdapter extends BaseQuickAdapter<GameTypeDataResp.DataBean.GameTypeDataBean, BaseViewHolder> {
    public ProductCommonAdapter() {
        super(R.layout.product_common_item_layout, new ArrayList<GameTypeDataResp.DataBean.GameTypeDataBean>());
    }

    @Override
    protected void convert(final BaseViewHolder helper, final GameTypeDataResp.DataBean.GameTypeDataBean item) {
        GlideUtils.loadImageViewLoading(mContext, item.getTITTLEIMG_URL(), (ImageView) helper.getView(R.id.game_logo_img),
                R.drawable.ic_product_action_game_loading, R.drawable.ic_product_action_game_loading);
        helper.setText(R.id.game_name_tv, item.getNAME());
        helper.setText(R.id.proxy_num_tv, mContext.getString(R.string.partner_product_proxy_num, item.getAGENT()));
        helper.getView(R.id.generalize_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.startActivity(v.getContext(), ProductDetailsActivity.getCallIntent(
                        v.getContext(), item.getID(), item.getNAME()), null);
            }
        });
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.startActivity(v.getContext(), ProductDetailsActivity.getCallIntent(
                        v.getContext(), item.getID(), item.getNAME()), null);
            }
        });

    }
}
