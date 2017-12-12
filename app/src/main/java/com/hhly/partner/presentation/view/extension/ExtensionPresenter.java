package com.hhly.partner.presentation.view.extension;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.BaseReq;
import com.hhly.partner.data.net.protocol.user.GetUserIdResp;
import com.hhly.partner.data.net.protocol.user.MobileReq;
import com.hhly.partner.data.net.protocol.user.MobileResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/5/3
 */

public class ExtensionPresenter implements ExtensionContract.Presenter {

    private final UserApi mUserApi;
    private ExtensionContract.View mView;

    public ExtensionPresenter(ExtensionContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void getMobile() {
        MobileReq req = new MobileReq();
        mUserApi.getMobile(req.params())
                .compose(mView.<MobileResp>bindToLife())
                .compose(RxUtil.<MobileResp>io_main())
                .subscribe(new BaseSubscriber<MobileResp>() {
                    @Override
                    public void onNext(MobileResp resp) {
                        if (resp.isOk()) {
                            mView.getMobileSuccess(resp.getData().getData());
                        } else {
                            mView.getMobileFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getMobileFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    @Override
    public void getUserId() {
        BaseReq req = new BaseReq();
        mUserApi.getUserId(req.params())
                .compose(mView.<GetUserIdResp>bindToLife())
                .compose(RxUtil.<GetUserIdResp>io_main())
                .subscribe(new BaseSubscriber<GetUserIdResp>() {
                    @Override
                    public void onNext(GetUserIdResp resp) {
                        if (resp.isOk()) {
                            mView.getUserIdSuccess(resp.getUserid());
                        } else {
                            mView.getUserIdFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getUserIdFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });


    }
}
