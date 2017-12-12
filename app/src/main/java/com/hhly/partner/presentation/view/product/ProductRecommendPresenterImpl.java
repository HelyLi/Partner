package com.hhly.partner.presentation.view.product;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.GameApi;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.protocol.game.GameDataReq;
import com.hhly.partner.data.net.protocol.game.GameDataResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;

/**
 * Created by dell on 2017/4/21.
 */

public class ProductRecommendPresenterImpl implements ProductContract.Presenter {
    private ProductContract.RecommendView mView;
    private GameApi mGameApi;

    public ProductRecommendPresenterImpl(ProductContract.RecommendView view) {
        mView = view;
        mView.setPresenter(this);
        mGameApi = RetrofitManager.getInstance(ApiType.GAME_API).getGameApi();
    }

    @Override
    public void getGameData(int gameType) {
        GameDataReq gameDataReq = new GameDataReq();
        gameDataReq.setPlatType(2);
        gameDataReq.setGameType(gameType);
        mGameApi.getGameData(gameDataReq.params())
                .compose(RxUtil.<GameDataResp>io_main())
                .compose(mView.<GameDataResp>bindToLife())
                .subscribe(new BaseSubscriber<GameDataResp>() {
                    @Override
                    public void onNext(GameDataResp gameDataResp) {
                        if (gameDataResp != null && gameDataResp.isOk() && gameDataResp.getData() != null) {
                            mView.getGameDataSuccess(gameDataResp.getData());
                        } else {
                            mView.getGameDataFailure(mView.getContext().getString(R.string.partner_no_data));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        mView.getGameDataFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }
}
