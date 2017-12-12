package com.hhly.partner.presentation.view.extension;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.GameApi;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.protocol.game.DeleteGameCustomizationReq;
import com.hhly.partner.data.net.protocol.game.DeleteGameCustomizationResp;
import com.hhly.partner.data.net.protocol.game.GetGameCustomizationReq;
import com.hhly.partner.data.net.protocol.game.GetGameCustomizationResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * 自定义推广页BaseCustomExtensionPresenterImpl
 * Created by dell on 2017/5/4.
 */

public class BaseCustomExtensionPresenterImpl<T extends ExtensionContract.BaseCustomExtensionView>
        implements ExtensionContract.BaseCustomExtensionPresenter {
    protected T mView;
    protected GameApi mGameApi;

    public BaseCustomExtensionPresenterImpl(T view) {
        mView = view;
        mView.setPresenter(this);
        mGameApi = RetrofitManager.getInstance(ApiType.GAME_API).getGameApi();
    }

    @Override
    public void deleteGameCustomization(int modeId, int gameId, final int position) {
        DeleteGameCustomizationReq req = new DeleteGameCustomizationReq();
        req.setModeId(modeId);
        req.setGameId(gameId);
        mGameApi.deleteGameCustomization(req.params())
                .compose(RxUtil.<DeleteGameCustomizationResp>io_main())
                .compose(mView.<DeleteGameCustomizationResp>bindToLife())
                .subscribe(new BaseSubscriber<DeleteGameCustomizationResp>() {
                    @Override
                    public void onNext(DeleteGameCustomizationResp resp) {
                        if (resp != null && resp.isOk()) {
                            mView.deleteGameCustomizationSuccess(position);
                        } else {
                            mView.deleteGameCustomizationFailure(resp != null ? resp.getMsg() :
                                    mView.getContext().getString(R.string.partner_request_error));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError:" + t.getMessage());
                        mView.deleteGameCustomizationFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }

    @Override
    public void getGameCustomization(int gameType) {
        GetGameCustomizationReq req = new GetGameCustomizationReq();
        req.setModeId(gameType);
        mGameApi.getGameCustomization(req.params())
                .compose(RxUtil.<GetGameCustomizationResp>io_main())
                .compose(mView.<GetGameCustomizationResp>bindToLife())
                .subscribe(new BaseSubscriber<GetGameCustomizationResp>() {
                    @Override
                    public void onNext(GetGameCustomizationResp resp) {
                        if (resp != null && resp.isOk() && resp.getData() != null
                                && resp.getData().getData() != null) {
                            mView.getGameCustomizationSuccess(resp.getData().getData());
                        } else {
                            mView.getGameCustomizationFailure(resp != null ? resp.getMsg() :
                                    mView.getContext().getString(R.string.partner_request_error));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError:" + t.getMessage());
                        mView.getGameCustomizationFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }
}
