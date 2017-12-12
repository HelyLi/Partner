package com.hhly.partner.presentation.view.me.setting;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.ResetPayPwdReq;
import com.hhly.partner.data.net.protocol.user.UpdatePayPwdResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.MD5;
import com.hhly.partner.presentation.utils.RxUtil;

/**
 * 设置支付密码presenterImpl
 * Created by dell on 2017/4/20.
 */

public class SetPayPswPresenterImpl implements AccountSettingContract.SetPayPswPresenter {
    private AccountSettingContract.SetPayPswView mView;
    private UserApi mUserApi;

    public SetPayPswPresenterImpl(AccountSettingContract.SetPayPswView view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void setPayPsw(String payPsw) {
        ResetPayPwdReq resetPayPwdReq = new ResetPayPwdReq();
        resetPayPwdReq.setPwd(new MD5(payPsw).getMd5_32());
        mUserApi.updatePayPwd(resetPayPwdReq.params())
                .compose(RxUtil.<UpdatePayPwdResp>io_main())
                .compose(mView.<UpdatePayPwdResp>bindToLife())
                .subscribe(new BaseSubscriber<UpdatePayPwdResp>() {
                    @Override
                    public void onNext(UpdatePayPwdResp resp) {
                        if (resp != null && resp.isOk()) {
                            mView.setPayPswSuccess(mView.getContext().getString(R.string.partner_set_success));
                        } else {
                            mView.setPayPswSuccess(mView.getContext().getString(R.string.partner_set_failure));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        mView.setPayPswFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }
}
