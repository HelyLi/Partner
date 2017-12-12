package com.hhly.partner.presentation.view.product.search;

import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GameCustomizationReq;
import com.hhly.partner.data.net.protocol.game.GameCustomizationResp;
import com.hhly.partner.data.net.protocol.game.GameDataReq;
import com.hhly.partner.data.net.protocol.game.GameDataResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.hhly.partner.presentation.utils.SearchRecordPrefsUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 产品搜索初始化presenterImpl
 * Created by dell on 2017/4/26.
 */

public class ProductSearchInitPresenterImpl extends BaseSearchPresenterImpl<ProductSearchContract.ProductSearchInitView>
        implements ProductSearchContract.ProductSearchInitPresenter {

    public ProductSearchInitPresenterImpl(ProductSearchContract.ProductSearchInitView view) {
        super(view);
    }

    @Override
    public void getRecordSearchProduct() {
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> e) throws Exception {
                e.onNext(SearchRecordPrefsUtil.getInstance().getRecordInfoList(mView.getContext()));
            }
        })
                .subscribeOn(Schedulers.io())
                .compose(mView.<List<String>>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull List<String> list) throws Exception {
                        if (list != null && list.size() > 4) {
                            list.subList(4, list.size()).clear();
                        }
                        mView.getRecordSearchProductSuccess(list);
                    }
                });
    }

    @Override
    public void getHotProduct() {
        GameDataReq gameDataReq = new GameDataReq();
        gameDataReq.setPlatType(2);
        gameDataReq.setGameType(0);
        mGameApi.getGameData(gameDataReq.params())
                .compose(RxUtil.<GameDataResp>io_main())
                .compose(mView.<GameDataResp>bindToLife())
                .subscribe(new BaseSubscriber<GameDataResp>() {
                    @Override
                    public void onNext(GameDataResp resp) {
                        if (resp != null && resp.isOk() && resp.getData() != null && resp.getData().getHotGame() != null) {
                            mView.getHotProductSuccess(resp.getData().getHotGame());
                        } else {
                            mView.getHotProductFailure(resp != null ? resp.getMsg() :
                                    mView.getContext().getString(R.string.partner_request_error));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError:" + t.getMessage());
                        mView.getHotProductFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
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
