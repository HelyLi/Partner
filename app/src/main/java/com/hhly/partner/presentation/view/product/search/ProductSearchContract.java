package com.hhly.partner.presentation.view.product.search;

import com.hhly.partner.data.net.protocol.game.GameDataByNameResp;
import com.hhly.partner.data.net.protocol.game.GameDataResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

import java.util.List;

/**
 * 产品搜索Contract
 * Created by dell on 2017/4/26.
 */

interface ProductSearchContract {

    interface BaseSearchPresenter extends BasePresenter {
        //推广页添加游戏
        void addGameCustomization(int modelId, int gameId, int promotionPosition);
    }

    interface BaseSearchView extends BaseView<BaseSearchPresenter> {
        void addGameCustomizationSuccess();

        void addGameCustomizationFail(String msg);
    }

    interface ProductSearchInitPresenter extends BaseSearchPresenter {
        //获取历史搜索
        void getRecordSearchProduct();

        //获取热门产品
        void getHotProduct();
    }

    interface ProductSearchInitView extends BaseSearchView {
        void getRecordSearchProductSuccess(List<String> list);

        void getHotProductSuccess(List<GameDataResp.DataBean.HotGameBean> list);

        void getHotProductFailure(String msg);
    }

    interface ProductSearchResultPresenter extends BaseSearchPresenter {
        //搜索产品
        void searchProduct(String searchKey);

        //记录搜索
        void recordSearchBehavior(String gameName, int gameId);
    }

    interface ProductSearchResultView extends BaseSearchView {
        void searchProductSuccess(List<GameDataByNameResp.DataBeanX.DataBean> list);

        void searchProductFailure(String msg);
    }
}
