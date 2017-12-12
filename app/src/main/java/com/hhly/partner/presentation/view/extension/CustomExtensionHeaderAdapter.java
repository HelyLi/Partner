package com.hhly.partner.presentation.view.extension;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GetGameCustomizationResp;
import com.hhly.partner.presentation.utils.GlideUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义推广页头部adapter
 * Created by dell on 2017/5/3.
 */

public class CustomExtensionHeaderAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    //添加游戏
    public static final int ADD_GAME_TYPE = 0X11;
    //游戏已经添加
    public static final int GAME_ADDED_TYPE = 0X12;

    private OnClickCallBack mOnClickCallBack;

    public CustomExtensionHeaderAdapter() {
        super(new ArrayList<MultiItemEntity>());
        addItemType(ADD_GAME_TYPE, R.layout.custom_extension_header_add_game_item);
        addItemType(GAME_ADDED_TYPE, R.layout.custom_extension_header_game_added_item);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MultiItemEntity item) {
        int itemType = helper.getItemViewType();
        final int position = helper.getAdapterPosition();
        switch (itemType) {
            case ADD_GAME_TYPE://添加游戏
                helper.getView(R.id.add_game_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnClickCallBack != null) {
                            mOnClickCallBack.addGame(position + 1);
                        }
                    }
                });
                break;
            case GAME_ADDED_TYPE://游戏已经添加
                GameAddedItem addedItem = (GameAddedItem) item;
                helper.setText(R.id.game_name_tv, addedItem.getGameName());
                GlideUtils.loadImageViewLoading(mContext, addedItem.getGameLogoUrl(),
                        (ImageView) helper.getView(R.id.game_logo_img), R.drawable.ic_product_action_game_loading,
                        R.drawable.ic_product_action_game_loading);
                helper.getView(R.id.delete_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnClickCallBack != null) {
                            mOnClickCallBack.deleteGame(((GameAddedItem) item).getGameId(), position);
                        }
                    }
                });
                break;
        }
    }

    public void setOnClickCallBack(OnClickCallBack onClickCallBack) {
        mOnClickCallBack = onClickCallBack;
    }

    public static class AddGameItem implements MultiItemEntity {
        @Override
        public int getItemType() {
            return ADD_GAME_TYPE;
        }
    }

    public static class GameAddedItem implements MultiItemEntity {
        private String gameLogoUrl;
        private String gameName;
        private int gameId;

        public String getGameLogoUrl() {
            return gameLogoUrl;
        }

        public void setGameLogoUrl(String gameLogoUrl) {
            this.gameLogoUrl = gameLogoUrl;
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        @Override
        public int getItemType() {
            return GAME_ADDED_TYPE;
        }
    }


    public void updateData(List<GetGameCustomizationResp.DataBeanX.DataBean> list) {
        if (list == null) return;
        List<CustomExtensionHeaderAdapter.GameAddedItem> gameAddedItemList = new ArrayList<>();
        CustomExtensionHeaderAdapter.GameAddedItem gameAddedItem = null;
        for (GetGameCustomizationResp.DataBeanX.DataBean bean : list) {
            gameAddedItem = new CustomExtensionHeaderAdapter.GameAddedItem();
            gameAddedItem.setGameLogoUrl(bean.getICON_URL());
            gameAddedItem.setGameName(bean.getNAME());
            gameAddedItem.setGameId(bean.getPROMOTION_GAME_ID());
            gameAddedItemList.add(gameAddedItem);
        }
        getData().clear();
        int size = gameAddedItemList.size();
        getData().addAll(gameAddedItemList);
        if (size < 3) {
            int sub = 3 - size;
            for (int i = 0; i < sub; i++) {
                getData().add(new AddGameItem());
            }
        }
        notifyDataSetChanged();
    }

    interface OnClickCallBack {
        void addGame(int promotionPosition);

        void deleteGame(int gameId, int position);
    }
}
