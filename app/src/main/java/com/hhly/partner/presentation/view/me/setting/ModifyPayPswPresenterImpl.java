package com.hhly.partner.presentation.view.me.setting;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.MobileReq;
import com.hhly.partner.data.net.protocol.user.MobileResp;
import com.hhly.partner.data.net.protocol.user.ResetPayPwdReq;
import com.hhly.partner.data.net.protocol.user.ResetPayPwdResp;
import com.hhly.partner.data.net.protocol.user.SmsCodeReq;
import com.hhly.partner.data.net.protocol.user.SmsCodeResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.MD5;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * Created by dell on 2017/4/27.
 */

public class ModifyPayPswPresenterImpl implements AccountSettingContract.ModifyPayPswPresenter {
    private AccountSettingContract.ModifyPayPswView mView;
    private UserApi mUserApil;

    public ModifyPayPswPresenterImpl(AccountSettingContract.ModifyPayPswView view) {
        mView = view;
        mView.setPresenter(this);
        mUserApil = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void getMobile(final String phone) {
        MobileReq req = new MobileReq();
        mUserApil.getMobile(req.params())
                .compose(RxUtil.<MobileResp>io_main())
                .compose(mView.<MobileResp>bindToLife())
                .subscribe(new BaseSubscriber<MobileResp>() {
                    @Override
                    public void onNext(MobileResp resp) {
                        if (resp != null && resp.isOk() && resp.getData() != null && resp.getData().getData() != null) {
                            MobileResp.DataBeanX.DataBean dataBean = resp.getData().getData().get(0);
                            if (dataBean != null && phone.equals(dataBean.getPHONE())) {
                                getSmsCode(phone);
                            } else {
                                mView.getMobileSuccess(mView.getContext().getString(R.string.partner_account_reset_register_phone));
                            }
                        } else {
                            mView.getSmsCodeFailure(resp != null ? resp.getMsg() :
                                    mView.getContext().getString(R.string.partner_request_error));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.d("onError:" + t.getMessage());
                        mView.getMobileSuccess(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }

    @Override
    public void getSmsCode(String phone) {
        SmsCodeReq req = new SmsCodeReq();
        req.setOperateType(11);
        req.setPhoneNumber(phone);
        mUserApil.getSmsCode(req.params())
                .compose(RxUtil.<SmsCodeResp>io_main())
                .compose(mView.<SmsCodeResp>bindToLife())
                .subscribe(new BaseSubscriber<SmsCodeResp>() {
                    @Override
                    public void onNext(SmsCodeResp resp) {
                        if (resp != null && resp.isOk() && resp.getMsg() != null) {
                            mView.getSmsCodeSuccess(resp.getMsg());
                        } else {
                            mView.getSmsCodeFailure(mView.getContext().getString(R.string.partner_request_error));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.d("onError:" + t.getMessage());
                        mView.getSmsCodeFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }

    @Override
    public void modifyPayPsw(final String phone, final String authCode, final String password) {
        SmsCodeReq req = new SmsCodeReq();
        req.setOperateType(11);
        req.setAuthCode(authCode);
        req.setPhoneNumber(phone);
        mUserApil.validateSmsCode(req.params())
                .compose(RxUtil.<SmsCodeResp>io_main())
                .compose(mView.<SmsCodeResp>bindToLife())
                .subscribe(new BaseSubscriber<SmsCodeResp>() {
                    @Override
                    public void onNext(SmsCodeResp resp) {
                        if (resp != null && resp.isOk()) {
                            updatePayPsw(phone, authCode, password);
                        } else {
                            mView.modifyPayPswFailure(resp != null ? resp.getMsg() :
                                    mView.getContext().getString(R.string.partner_request_error));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.d("onError:" + t.getMessage());
                        mView.modifyPayPswFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }

    /**
     * 更新支付密码
     *
     * @param phone    注册时的手机号
     * @param authCode 验证码
     * @param password 支付密码
     */
    private void updatePayPsw(String phone, String authCode, String password) {
        ResetPayPwdReq req = new ResetPayPwdReq();
        req.setMobile(phone);
        req.setSendNo(authCode);
        req.setPwd(new MD5(password).getMd5_32());
        mUserApil.resetPayPwd(req.params())
                .compose(RxUtil.<ResetPayPwdResp>io_main())
                .compose(mView.<ResetPayPwdResp>bindToLife())
                .subscribe(new BaseSubscriber<ResetPayPwdResp>() {
                    @Override
                    public void onNext(ResetPayPwdResp resp) {
                        if (resp != null && resp.isOk()) {
                            mView.modifyPayPswSuccess(mView.getContext().getString(R.string.partner_personal_account_modify_pay_psw_success));
                        } else {
                            mView.modifyPayPswFailure(mView.getContext().getString(R.string.partner_personal_account_modify_pay_psw_fail));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.d("onError:" + t.getMessage());
                        mView.modifyPayPswFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }
}
