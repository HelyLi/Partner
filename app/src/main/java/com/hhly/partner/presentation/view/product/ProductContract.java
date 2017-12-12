package com.hhly.partner.presentation.view.product;

import android.content.Context;

import com.hhly.partner.data.net.protocol.game.GameDataInfoResp;
import com.hhly.partner.data.net.protocol.game.GameDataResp;
import com.hhly.partner.data.net.protocol.game.GameTypeDataResp;
import com.hhly.partner.data.net.protocol.game.GetIndexlbtResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.List;

/**
 * description:
 * Created by dell on 2017/4/21.
 */

public interface ProductContract {
    interface Presenter extends BasePresenter {
        void getGameData(int gameType);
    }

    interface RecommendView extends BaseView<Presenter> {
        void getGameDataSuccess(GameDataResp.DataBean dataBean);

        void getGameDataFailure(String msg);

    }

    interface CommonView extends BaseView<Presenter> {
        void getGameDataSuccess(GameTypeDataResp.DataBean dataBean);

        void getGameDataFailure(String msg);
    }

    interface BannerPresenter extends BasePresenter {
        void getBannerInfo();
    }

    interface BannerView {

        Context getContext();

        <K> LifecycleTransformer<K> bindToLife();

        void getBannerInfoSuccess(List<GetIndexlbtResp.DataBean.ListBean> list);

        void getBannerInfoFailure(String msg);
    }

    interface DetailsPresenter extends BasePresenter {
        void getProductDetailsInfo(int gameId);
    }

    interface DetailsView extends BaseView<DetailsPresenter> {
        void getProductDetailsInfoSuccess(GameDataInfoResp.DataBeanX dataBean);

        void getProductDetailsInfoFailure(String msg);
    }
}
