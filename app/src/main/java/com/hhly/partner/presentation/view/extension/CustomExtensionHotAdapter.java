package com.hhly.partner.presentation.view.extension;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.utils.GlideUtils;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 自定义推广页热门游戏adapter
 * Created by dell on 2017/5/4.
 */

public class CustomExtensionHotAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_ADD_GAME = 0X1;//添加游戏
    public static final int TYPE_GAME_ADDED = 0X2;//游戏已经添加
    private OnClickCallBack mOnClickCallBack;
    //最后点击的position
    private int mLastClickedPosition = -1;

    public CustomExtensionHotAdapter() {
        super(new ArrayList<MultiItemEntity>());
        addItemType(TYPE_ADD_GAME, R.layout.custom_extension_hot_add_game_item_layout);
        addItemType(TYPE_GAME_ADDED, R.layout.custom_extension_hot_game_added_item_layout);
    }

    public void setOnClickCallBack(OnClickCallBack onClickCallBack) {
        mOnClickCallBack = onClickCallBack;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final MultiItemEntity item) {
        switch (item.getItemType()) {
            case TYPE_ADD_GAME:
                helper.getView(R.id.add_game_layout).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnClickCallBack != null) {
                            mOnClickCallBack.addGame(helper.getAdapterPosition() + 1);
                        }
                    }
                });
                break;
            case TYPE_GAME_ADDED:
                final GameAddedItem gameAddedItem = (GameAddedItem) item;
                GlideUtils.loadImageViewLoading(mContext, gameAddedItem.gameIconUrl,
                        (ImageView) helper.getView(R.id.game_logo_img), R.drawable.ic_product_action_game_loading,
                        R.drawable.ic_product_action_game_loading);
                helper.setText(R.id.game_name_tv, gameAddedItem.getGameName());
                helper.getView(R.id.cancel_tv).setVisibility(gameAddedItem.isDeleteState ? View.VISIBLE : View.INVISIBLE);
                helper.getView(R.id.delete_img).setVisibility(gameAddedItem.isDeleteState ? View.VISIBLE : View.INVISIBLE);
                if (gameAddedItem.isStartCloseAnim) {
                    startCloseAnimation(helper.getView(R.id.cancel_tv), helper.getView(R.id.delete_img));
                    gameAddedItem.setStartCloseAnim(false);
                }
                RxView.clicks(helper.getView(R.id.delete_state_layout)).throttleFirst(200, TimeUnit.MILLISECONDS)
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(@NonNull Object o) throws Exception {
                                changeDeleteState(helper, gameAddedItem);
                            }
                        });
                helper.getView(R.id.cancel_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeDeleteState(helper, gameAddedItem);
                    }
                });
                helper.getView(R.id.delete_img).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnClickCallBack != null) {
                            mOnClickCallBack.deleteGame(gameAddedItem.getGameId(), helper.getAdapterPosition());
                        }
                    }
                });
                break;
        }
    }

    public static class AddGameItem implements MultiItemEntity {
        private String gameName;
        private String gameIconUrl;

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public String getGameIconUrl() {
            return gameIconUrl;
        }

        public void setGameIconUrl(String gameIconUrl) {
            this.gameIconUrl = gameIconUrl;
        }

        @Override
        public int getItemType() {
            return TYPE_ADD_GAME;
        }
    }

    public static class GameAddedItem implements MultiItemEntity {
        private String gameName;
        private String gameIconUrl;
        private int gameId;
        //删除布局是否正显示
        private boolean isDeleteState;
        private boolean isStartCloseAnim;

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public String getGameIconUrl() {
            return gameIconUrl;
        }

        public void setGameIconUrl(String gameIconUrl) {
            this.gameIconUrl = gameIconUrl;
        }

        public int getGameId() {
            return gameId;
        }

        public void setGameId(int gameId) {
            this.gameId = gameId;
        }

        public boolean isDeleteState() {
            return isDeleteState;
        }

        public void setDeleteState(boolean deleteState) {
            isDeleteState = deleteState;
        }

        public boolean isStartCloseAnim() {
            return isStartCloseAnim;
        }

        public void setStartCloseAnim(boolean startCloseAnim) {
            isStartCloseAnim = startCloseAnim;
        }

        @Override
        public int getItemType() {
            return TYPE_GAME_ADDED;
        }
    }

    public void updateItems(List<GameAddedItem> list) {
        getData().clear();
        int size = list.size();
        getData().addAll(list);
        if (size < 8) {
            int sub = 8 - list.size();
            List<AddGameItem> addGameItems = new ArrayList<>();
            for (int i = 0; i < sub; i++) {
                addGameItems.add(new AddGameItem());
            }
            getData().addAll(addGameItems);
        }
        notifyDataSetChanged();
    }

    interface OnClickCallBack {
        void addGame(int promotionPosition);

        void deleteGame(int gameId, int position);
    }

    private void startOpenAnimation(View cancelView, View deleteView) {
        cancelView.setVisibility(View.VISIBLE);
        deleteView.setVisibility(View.VISIBLE);
        AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator topTranYAnimator = ObjectAnimator.ofFloat(deleteView, "translationY",
                -DisplayUtil.dip2px(mContext, 38), 0);
        ObjectAnimator topAlphaAnimator = ObjectAnimator.ofFloat(deleteView, "alpha", 0, 1f);
        ObjectAnimator bottomTranYAnimator = ObjectAnimator.ofFloat(cancelView, "translationY",
                DisplayUtil.dip2px(mContext, 78), 0);
        ObjectAnimator bottomAlphaAnimator = ObjectAnimator.ofFloat(cancelView, "alpha", 0, 1f);
        animSet.playTogether(topAlphaAnimator, topTranYAnimator, bottomTranYAnimator, bottomAlphaAnimator);
        animSet.setDuration(500);
        animSet.start();
    }

    private void startCloseAnimation(final View cancelView, final View deleteView) {
        AnimatorSet animSet = new AnimatorSet();
        ObjectAnimator topTranYAnimator = ObjectAnimator.ofFloat(deleteView, "translationY",
                0, -DisplayUtil.dip2px(mContext, 38));
        ObjectAnimator topAlphaAnimator = ObjectAnimator.ofFloat(deleteView, "alpha", 1f, 0);
        ObjectAnimator bottomTranYAnimator = ObjectAnimator.ofFloat(cancelView, "translationY",
                0, DisplayUtil.dip2px(mContext, 76));
        ObjectAnimator bottomAlphaAnimator = ObjectAnimator.ofFloat(cancelView, "alpha", 1, 0);
        animSet.playTogether(topAlphaAnimator, topTranYAnimator, bottomTranYAnimator, bottomAlphaAnimator);
        animSet.setDuration(500);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cancelView.setVisibility(View.INVISIBLE);
                deleteView.setVisibility(View.INVISIBLE);
            }
        });
        animSet.start();
    }

    private void changeDeleteState(BaseViewHolder helper, GameAddedItem curItem) {
        int curPosition = helper.getAdapterPosition();
        if (curPosition != mLastClickedPosition && mLastClickedPosition != -1) {
            MultiItemEntity clickItem = getItem(mLastClickedPosition);
            if (clickItem instanceof GameAddedItem) {
                GameAddedItem lastClickItem = (GameAddedItem) clickItem;
                if (lastClickItem.isDeleteState) {
                    lastClickItem.setStartCloseAnim(true);
                    notifyItemChanged(mLastClickedPosition);
                    lastClickItem.setDeleteState(false);
                }
            }
        }
        mLastClickedPosition = curPosition;
        if (curItem.isDeleteState) {
            startCloseAnimation(helper.getView(R.id.cancel_tv), helper.getView(R.id.delete_img));
        } else {
            startOpenAnimation(helper.getView(R.id.cancel_tv), helper.getView(R.id.delete_img));
        }
        curItem.setDeleteState(!curItem.isDeleteState);
    }

    /**
     * 删除某个已添加游戏 item
     *
     * @param position position
     */
    public void deleteAddedGameItem(int position) {
        remove(position);
        getData().add(new AddGameItem());
        notifyDataSetChanged();
    }

}
