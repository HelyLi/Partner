package com.hhly.partner.presentation.view.product;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.GameApi;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.protocol.game.GameDataInfoReq;
import com.hhly.partner.data.net.protocol.game.GameDataInfoResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * 产品详情presenterImpl
 * Created by dell on 2017/4/25.
 */

public class ProductDetailsPresenterImpl implements ProductContract.DetailsPresenter {
    private ProductContract.DetailsView mView;
    private GameApi mGameApi;

    public ProductDetailsPresenterImpl(ProductContract.DetailsView view) {
        mView = view;
        mView.setPresenter(this);
        mGameApi = RetrofitManager.getInstance(ApiType.GAME_API).getGameApi();
    }

    @Override
    public void getProductDetailsInfo(int gameId) {
        GameDataInfoReq req = new GameDataInfoReq();
        req.setGameId(gameId);
        mGameApi.getGameDataInfo(req.params())
                .compose(RxUtil.<GameDataInfoResp>io_main())
                .compose(mView.<GameDataInfoResp>bindToLife())
                .subscribe(new BaseSubscriber<GameDataInfoResp>() {
                    @Override
                    public void onNext(GameDataInfoResp resp) {
                        if (resp!=null&&resp.isOk()&&resp.getData()!=null){
                            mView.getProductDetailsInfoSuccess(resp.getData());
                        }else {
                            mView.getProductDetailsInfoFailure(resp!=null?resp.getMsg():mView.getContext().getString(R.string.partner_request_error));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError:" + t.getMessage());
                        mView.getProductDetailsInfoFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }
}
