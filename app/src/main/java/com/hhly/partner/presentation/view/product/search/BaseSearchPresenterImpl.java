package com.hhly.partner.presentation.view.product.search;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.GameApi;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.protocol.game.GameCustomizationReq;
import com.hhly.partner.data.net.protocol.game.GameCustomizationResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * baseSearchPresenterImpl
 * Created by dell on 2017/5/6.
 */

public class BaseSearchPresenterImpl<T extends ProductSearchContract.BaseSearchView>
        implements ProductSearchContract.BaseSearchPresenter {
    protected T mView;
    protected GameApi mGameApi;

    public BaseSearchPresenterImpl(ProductSearchContract.BaseSearchView view) {
        mView = (T) view;
        mView.setPresenter(this);
        mGameApi = new RetrofitManager(ApiType.GAME_API).getGameApi();
    }

    @Override
    public void addGameCustomization(int modelId, int gameId, int promotionPosition) {
        GameCustomizationReq req = new GameCustomizationReq();
        req.setGameId(gameId);
        req.setModeId(modelId);
        req.setPromotionPosition(promotionPosition);
        mGameApi.addGameCustomization(req.params())
                .compose(RxUtil.<GameCustomizationResp>io_main())
                .compose(mView.<GameCustomizationResp>bindToLife())
                .subscribe(new BaseSubscriber<GameCustomizationResp>() {
                    @Override
                    public void onNext(GameCustomizationResp resp) {
                        if (resp != null && resp.isOk()) {
                            mView.addGameCustomizationSuccess();
                        } else {
                            mView.addGameCustomizationFail(resp != null ? resp.getMsg() :
                                    mView.getContext().getString(R.string.partner_request_error));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError:" + t.getMessage());
                        mView.addGameCustomizationFail(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }
}
