package com.hhly.partner.presentation.view.me.setting;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.LogoutReq;
import com.hhly.partner.data.net.protocol.user.LogoutResp;
import com.hhly.partner.data.net.protocol.user.PayPwdReq;
import com.hhly.partner.data.net.protocol.user.PayPwdResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * 账户设置presenterImpl
 * Created by dell on 2017/4/27.
 */

public class AccountSettingPresenterImpl implements AccountSettingContract.AccountSettingPresenter {
    private AccountSettingContract.AccountSettingView mView;
    private UserApi mUserApi;

    public AccountSettingPresenterImpl(AccountSettingContract.AccountSettingView view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void getPayPsw() {
        PayPwdReq req = new PayPwdReq();
        mUserApi.getPayPwd(req.params())
                .compose(RxUtil.<PayPwdResp>io_main())
                .compose(mView.<PayPwdResp>bindToLife())
                .subscribe(new BaseSubscriber<PayPwdResp>() {
                    @Override
                    public void onNext(PayPwdResp resp) {
                        if (resp != null && resp.isOk() && resp.getData() != null && resp.getData().getData() != null) {
                            PayPwdResp.DataBeanX.DataBean dataBean = resp.getData().getData().get(0);
                            if (dataBean.getPASSWORD() != null && !"null".equals(dataBean.getPASSWORD())) {
                                mView.getPayPswSuccess(true);
                            } else {
                                mView.getPayPswSuccess(false);
                            }
                        } else {
                            mView.getPayPswFail(resp != null ? resp.getMsg() : mView.getContext().getString(R.string.partner_request_error));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError:" + t.getMessage());
                        mView.getPayPswFail(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }

    @Override
    public void exit() {
        LogoutReq req = new LogoutReq();
        mUserApi.logout(req.params())
                .compose(RxUtil.<LogoutResp>io_main())
                .compose(mView.<LogoutResp>bindToLife())
                .subscribe(new BaseSubscriber<LogoutResp>() {
                    @Override
                    public void onNext(LogoutResp resp) {
                        if (resp != null && resp.isOk()) {
                            mView.exitSuccess(mView.getContext().getString(R.string.partner_personal_account_exit_success));
                        } else {
                            mView.exitFailure(mView.getContext().getString(R.string.partner_personal_account_exit_fail));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError:" + t.getMessage());
                        mView.exitFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }
}
