package com.hhly.partner.presentation.view.property.transaction;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.WithdrawalInfoReq;
import com.hhly.partner.data.net.protocol.user.WithdrawalInfoResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/4/24
 */

public class TransactionPresenter implements TransactionContract.Presenter {

    private final UserApi mUserApi;
    private TransactionContract.View mView;

    public TransactionPresenter(TransactionContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void getWithdrawalInfo() {
        final WithdrawalInfoReq req = new WithdrawalInfoReq();
        mUserApi.getWithdrawalInfo(req.params())
                .compose(mView.<WithdrawalInfoResp>bindToLife())
                .compose(RxUtil.<WithdrawalInfoResp>io_main())
                .subscribe(new BaseSubscriber<WithdrawalInfoResp>() {
                    @Override
                    public void onNext(WithdrawalInfoResp resp) {
                        if (resp.isOk()) {
                            mView.getWithdrawalInfoSuccess(resp.getData());
                        } else {
                            mView.getWithdrawalInfoFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getWithdrawalInfoFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }
}
