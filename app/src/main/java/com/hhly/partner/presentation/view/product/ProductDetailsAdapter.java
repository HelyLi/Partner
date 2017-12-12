package com.hhly.partner.presentation.view.product;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GameDataInfoResp;
import com.hhly.partner.presentation.utils.UserPrefsUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品详情adapter
 * Created by dell on 2017/4/25.
 */

public class ProductDetailsAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    //普通item
    public static final int NORMAL_ITEM = 0;
    //底部item
    public static final int FOOTER_ITEM = 1;

    public ProductDetailsAdapter() {
        super(new ArrayList<MultiItemEntity>());
        addItemType(NORMAL_ITEM, R.layout.product_details_item_layout);
        addItemType(FOOTER_ITEM, R.layout.product_details_footer_item_layout);
    }

    private SpannableString getRankingStr(int drawable) {
        Drawable firstDrawable = ContextCompat.getDrawable(mContext, drawable);
        firstDrawable.setBounds(0, 0, firstDrawable.getIntrinsicWidth(), firstDrawable.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(firstDrawable);
        SpannableString spannableString = new SpannableString("1");
        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity multiItemEntity) {
        switch (multiItemEntity.getItemType()) {
            case NORMAL_ITEM://普通item
                GameDataInfoResp.DataBeanX.AmongBean item = (GameDataInfoResp.DataBeanX.AmongBean) multiItemEntity;
                helper.setText(R.id.account_tv, item.getPHONE());
                helper.setText(R.id.amount_tv, String.valueOf(item.getRECHARGE_AMOUNT()));
                TextView rankingTv = helper.getView(R.id.ranking_tv);
                int ranking = item.getRN();
                if (ranking == 1) {
                    rankingTv.setText(getRankingStr(R.drawable.ic_product_proxy_ranking_first));
                } else if (ranking == 2) {
                    rankingTv.setText(getRankingStr(R.drawable.ic_product_proxy_ranking_second));
                } else if (ranking == 3) {
                    rankingTv.setText(getRankingStr(R.drawable.ic_product_proxy_ranking_third));
                } else {
                    rankingTv.setText(String.valueOf(item.getRN()));
                }
                if (UserPrefsUtil.getInstance().getUserName(mContext).equals(item.getPHONE())) {
                    helper.getView(R.id.my_ranking_img).setVisibility(View.VISIBLE);
                } else {
                    helper.getView(R.id.my_ranking_img).setVisibility(View.GONE);
                }
                break;
            case FOOTER_ITEM://底部item
                FooterItem footerItem = (FooterItem) multiItemEntity;
                helper.setText(R.id.footer_tv, footerItem.getDescription());
                break;
        }
    }

    /**
     * 添加中间的省略跟自己的排名显示
     *
     * @param dataBean dataBean
     */
    public void addFooterView(GameDataInfoResp.DataBeanX dataBean) {
        getData().add(new ProductDetailsAdapter.FooterItem("···"));
        if (dataBean.getMy() != null && !dataBean.getMy().isEmpty() && dataBean.getMy().get(0) != null) {
            int ranking = dataBean.getMy().get(0).getRN();
            getData().add(new ProductDetailsAdapter.FooterItem(
                    mContext.getString(R.string.partner_product_proxy_game_cur_ranking, ranking)));
        } else {
            getData().add(new ProductDetailsAdapter.FooterItem(
                    mContext.getString(R.string.partner_product_proxy_game_no_ranking)));
        }
    }

    /**
     * 显示暂无数据
     */
    public void showEmptyView(Context context) {
        List<MultiItemEntity> list = new ArrayList<>();
        list.add(new ProductDetailsAdapter.FooterItem(
                context.getString(R.string.partner_no_data)));
        setNewData(list);
    }

    public static class FooterItem implements MultiItemEntity {
        public FooterItem(String description) {
            mDescription = description;
        }

        private String mDescription;

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            this.mDescription = description;
        }

        @Override
        public int getItemType() {
            return FOOTER_ITEM;
        }
    }

}
