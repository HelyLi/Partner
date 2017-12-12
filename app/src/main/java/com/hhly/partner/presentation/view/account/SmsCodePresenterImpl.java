package com.hhly.partner.presentation.view.account;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.CheckAccountReq;
import com.hhly.partner.data.net.protocol.user.CheckAccountResp;
import com.hhly.partner.data.net.protocol.user.SmsCodeReq;
import com.hhly.partner.data.net.protocol.user.SmsCodeResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;

import com.orhanobut.logger.Logger;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dell on 2017/4/13.
 */

public class SmsCodePresenterImpl implements RegisterContract.SmsCodePresenter {
    private UserApi mUserApi;
    private RegisterContract.SmsCodeView mView;

    public SmsCodePresenterImpl(RegisterContract.SmsCodeView view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    /**
     * 获取验证码
     * @param phone
     */
    @Override
    public void requestVerificationCode(final String phone) {
        CheckAccountReq checkAccountReq = new CheckAccountReq();
        checkAccountReq.setPhone(phone);
        mUserApi.isExistAccount(checkAccountReq.params())
                .compose(RxUtil.<CheckAccountResp>io_main())
                .compose(mView.<CheckAccountResp>bindToLife())
                .filter(new Predicate<CheckAccountResp>() {
                    @Override
                    public boolean test(@NonNull CheckAccountResp checkAccountResp) throws Exception {
                        if (!checkAccountResp.isOk()) {
                            mView.onGetCodeFailure(checkAccountResp.getMsg());
                        }
                        return checkAccountResp.isOk();
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<CheckAccountResp, Flowable<SmsCodeResp>>() {
                    @Override
                    public Flowable<SmsCodeResp> apply(@NonNull CheckAccountResp checkAccountResp) throws Exception {
                        SmsCodeReq smsCodeReq = new SmsCodeReq();
                        smsCodeReq.setPhoneNumber(phone);
                        smsCodeReq.setOperateType(1);
                        return mUserApi.getSmsCode(smsCodeReq.params());
                    }
                })
                .compose(mView.<SmsCodeResp>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<SmsCodeResp>() {
                    @Override
                    public boolean test(@NonNull SmsCodeResp smsCodeResp) throws Exception {
                        if (!smsCodeResp.isOk()){
                            mView.onGetCodeFailure(smsCodeResp.getMsg());
                        }
                        return smsCodeResp.isOk();
                    }
                })
                .subscribe(new BaseSubscriber<SmsCodeResp>() {
                    @Override
                    public void onNext(SmsCodeResp smsCodeResp) {
                        mView.onGetCodeSuccess(smsCodeResp.getMsg());
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.onGetCodeFailure(mView.getContext().getString(R.string.register_get_code_fail));
                    }
                });
    }

    /**
     * 确定验证码是否正确
     * @param phone
     * @param authCode
     */
    @Override
    public void confirmAuthCode(String phone, String authCode) {
        SmsCodeReq smsCodeReq=new SmsCodeReq();
        smsCodeReq.setPhoneNumber(phone);
        smsCodeReq.setAuthCode(authCode);
        smsCodeReq.setOperateType(1);
        mUserApi.validateSmsCode(smsCodeReq.params())
                .compose(RxUtil.<SmsCodeResp>io_main())
                .compose(mView.<SmsCodeResp>bindToLife())
                .subscribe(new BaseSubscriber<SmsCodeResp>() {
                    @Override
                    public void onNext(SmsCodeResp smsCodeResp) {
                        if (smsCodeResp.isOk()){
                            mView.onCheckCodeSuccess(smsCodeResp.getMsg());
                        }else{
                            mView.onCheckCodeFailure(smsCodeResp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        mView.onCheckCodeFailure(mView.getContext().getString(R.string.register_auth_code_confirm_error));
                    }
                });
    }
}
