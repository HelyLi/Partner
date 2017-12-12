package com.hhly.partner.presentation.view.product;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GameDataResp;
import com.hhly.partner.presentation.utils.CollectionUtil;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.utils.GlideUtils;
import com.mcxtzhang.nestlistview.NestFullListView;
import com.mcxtzhang.nestlistview.NestFullListViewAdapter;
import com.mcxtzhang.nestlistview.NestFullViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品》推荐adapter
 * Created by dell on 2017/4/21.
 */

public class ProductRecommendAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    //热门游戏\最高盈利\最多人代理
    public static final int RECOMMEND_TYPE_LINEAR_DISPLAY = 1;
    //最新游戏
    public static final int RECOMMEND_TYPE_GRID_DISPLAY = 2;
    //只有一个标题的类型
    public static final int RECOMMEND_TYPE_TITLE_DISPLAY = 3;
    private LinearLayout.LayoutParams mImgLayoutParams;

    public ProductRecommendAdapter(Context context) {
        super(new ArrayList<MultiItemEntity>());
        mContext = context;
        int width = (DisplayUtil.getScreenWidth(mContext) - DisplayUtil.dip2px(mContext, 45)) / 2;
        mImgLayoutParams = new LinearLayout.LayoutParams(width, (int) (width * 0.75));
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        int viewType = helper.getItemViewType();
        switch (viewType) {
            case RECOMMEND_TYPE_LINEAR_DISPLAY: //热门游戏\最高盈利\最多人代理
                final LinearItem linearItem = (LinearItem) item;
                TextView titleTv = helper.getView(R.id.section_title_tv);
                titleTv.setText(linearItem.getTitleName());
                titleTv.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(
                        mContext, linearItem.getIconRes()), null, null, null);
                helper.setText(R.id.section_title_tv, ((LinearItem) item).getTitleName());
                NestFullListView listView = helper.getView(R.id.list_view);
                listView.setAdapter(new NestFullListViewAdapter<GameDataResp.BaseGameBean>(
                        R.layout.product_recommend_linear_display_item_layout, linearItem.getGameBeanList()) {
                    @Override
                    public void onBind(int i, GameDataResp.BaseGameBean item, NestFullViewHolder holder) {
                        holder.setText(R.id.name_tv, item.getNAME());
                        ImageView iconImg = holder.getView(R.id.icon_img);
                        GlideUtils.loadImageViewLoading(mContext, item.getTITTLEIMG_URL(), iconImg, R.drawable.ic_product_action_game_loading,
                                R.drawable.ic_product_action_game_loading);
                    }
                });
                listView.setOnItemClickListener(new NestFullListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(NestFullListView nestFullListView, View view, int position) {
                        GameDataResp.BaseGameBean bean = (GameDataResp.BaseGameBean) linearItem.getGameBeanList().get(position);
                        ActivityCompat.startActivity(mContext, ProductDetailsActivity.getCallIntent(
                                mContext, bean.getID(), bean.getNAME()), null);
                    }
                });
                listView.updateUI();
                break;
            case RECOMMEND_TYPE_GRID_DISPLAY: //最新游戏
                final GameDataResp.DataBean.MaxNewsBean newsBean = (GameDataResp.DataBean.MaxNewsBean) item;
                ImageView gameLogoImg = helper.getView(R.id.game_logo_img);
                gameLogoImg.setLayoutParams(mImgLayoutParams);
                GlideUtils.loadImageDoNoAni(mContext, newsBean.getTITTLEIMG_URL(), gameLogoImg,
                        R.drawable.ic_product_loading, R.drawable.ic_product_loading);
                helper.setText(R.id.game_name_tv, newsBean.getNAME());
                helper.setText(R.id.proxy_num_tv, mContext.getString(R.string.partner_product_proxy_num, newsBean.getAGENT()));
                helper.getView(R.id.newest_game_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.startActivity(mContext, ProductDetailsActivity.getCallIntent(
                                mContext, newsBean.getID(), newsBean.getNAME()), null);
                    }
                });
                break;
            case RECOMMEND_TYPE_TITLE_DISPLAY://只有一个标题的类型
                TitleItem titleItem = (TitleItem) item;
                TextView sectionTitleTv = helper.getView(R.id.section_title_tv);
                sectionTitleTv.setText(titleItem.getTitleName());
                sectionTitleTv.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(mContext
                        , titleItem.getIconRes()), null, null, null);
                break;
        }
    }


    private static class LinearItem<T extends GameDataResp.BaseGameBean> implements MultiItemEntity {
        private List<T> mGameBeanList;
        private String mTitleName;
        @DrawableRes
        private int mIconRes;

        LinearItem(String titleName, int iconRes, List<T> list) {
            mTitleName = titleName;
            mGameBeanList = list;
            mIconRes = iconRes;
        }


        private String getTitleName() {
            return mTitleName;
        }

        private List<T> getGameBeanList() {
            return mGameBeanList;
        }

        public int getIconRes() {
            return mIconRes;
        }

        @Override
        public int getItemType() {
            return RECOMMEND_TYPE_LINEAR_DISPLAY;
        }

    }

    private static class TitleItem implements MultiItemEntity {
        private String mTitleName;
        @DrawableRes
        private int mIconRes;

        TitleItem(String titleName, int iconRes) {
            mTitleName = titleName;
            mIconRes = iconRes;
        }

        public int getIconRes() {
            return mIconRes;
        }

        public String getTitleName() {
            return mTitleName;
        }

        @Override
        public int getItemType() {
            return RECOMMEND_TYPE_TITLE_DISPLAY;
        }
    }

    @SuppressWarnings("unchecked")
    void update(GameDataResp.DataBean dataBean) {
        if (dataBean == null)
            return;
        setNewData(new ArrayList<MultiItemEntity>());
        if (CollectionUtil.isNotEmpty(dataBean.getHotGame())) {
            addItemType(RECOMMEND_TYPE_LINEAR_DISPLAY, R.layout.product_recommend_header_layout);
            addData(new LinearItem(mContext.getString(R.string.partner_product_hot_game), R.drawable.ic_product_hot_game, dataBean.getHotGame()));
        }
        if (CollectionUtil.isNotEmpty(dataBean.getMaxGains())) {
            addItemType(RECOMMEND_TYPE_LINEAR_DISPLAY, R.layout.product_recommend_header_layout);
            addData(new LinearItem(mContext.getString(R.string.partner_product_highest_profit),
                    R.drawable.ic_product_highest_profit, dataBean.getMaxGains()));
        }
        if (CollectionUtil.isNotEmpty(dataBean.getMaxAgent())) {
            addItemType(RECOMMEND_TYPE_LINEAR_DISPLAY, R.layout.product_recommend_header_layout);
            addData(new LinearItem(mContext.getString(R.string.partner_product_max_proxy),
                    R.drawable.ic_product_max_proxy, dataBean.getMaxAgent()));
        }
        if (CollectionUtil.isNotEmpty(dataBean.getMaxNews())) {
            addItemType(RECOMMEND_TYPE_GRID_DISPLAY, R.layout.product_recommend_grid_display_item_layout);
            addItemType(RECOMMEND_TYPE_TITLE_DISPLAY, R.layout.product_recommend_title_layout);
            addData(new TitleItem(mContext.getString(R.string.partner_product_newest_game),
                    R.drawable.ic_product_newest_game));
            getData().addAll(dataBean.getMaxNews());
        }
    }
}
