package com.hhly.partner.presentation.view.account;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.*;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.MD5;
import com.hhly.partner.presentation.utils.RxUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;

/**
 * description :
 * Created by Flynn
 * 2017/4/14
 */

public class ResetPwdPresenter implements ResetPwdContract.Presenter {

    private final UserApi mUserApi;
    ResetPwdContract.View mView;

    public ResetPwdPresenter(ResetPwdContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }


    @Override
    public void resetPwd(String phone, String code, String newPwd) {
        final UpdatePwdByPhoneReq req = new UpdatePwdByPhoneReq();
        req.setMobile(phone);
        req.setPwd(new MD5(newPwd).getMd5_32());
        req.setSendNo(code);

        final SmsCodeReq smsCodeReq = new SmsCodeReq();
        smsCodeReq.setPhoneNumber(phone);
        smsCodeReq.setAuthCode(code);
        smsCodeReq.setOperateType(4);
        mUserApi.validateSmsCode(smsCodeReq.params())
                .compose(mView.<SmsCodeResp>bindToLife())
                .compose(RxUtil.<SmsCodeResp>io_main())
                .filter(new Predicate<SmsCodeResp>() {
                    @Override
                    public boolean test(@NonNull SmsCodeResp smsCodeResp) throws Exception {
                        if (!smsCodeResp.isOk()) {
                            mView.resetPwdFailure(mView.getContext().getString(R.string.partner_account_reset_code_err));
                        }
                        return smsCodeResp.isOk();
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<SmsCodeResp, UpdatePwdByPhoneReq>() {
                    @Override
                    public UpdatePwdByPhoneReq apply(@NonNull SmsCodeResp smsCodeResp) throws Exception {
                        return req;
                    }
                })
                .flatMap(new Function<UpdatePwdByPhoneReq, Publisher<UpdatePwdByPhoneResp>>() {
                    @Override
                    public Publisher<UpdatePwdByPhoneResp> apply(@NonNull UpdatePwdByPhoneReq updatePwdByPhoneReq) throws Exception {
                        return mUserApi.updatePwdByPhone(updatePwdByPhoneReq.params());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<UpdatePwdByPhoneResp>() {
                    @Override
                    public void onNext(UpdatePwdByPhoneResp updatePwdByPhoneResp) {
                        if (updatePwdByPhoneResp.isOk()) {
                            mView.resetPwdSuccess();
                        } else {
                            mView.resetPwdFailure(updatePwdByPhoneResp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.resetPwdFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });


        //        mUserApi.updatePwdByPhone(req.params())
        //                .compose(mView.<UpdatePwdByPhoneResp>bindToLife())
        //                .compose(RxUtil.<UpdatePwdByPhoneResp>io_main())
        //                .subscribe(new BaseSubscriber<UpdatePwdByPhoneResp>() {
        //                    @Override
        //                    public void onNext(UpdatePwdByPhoneResp updatePwdByPhoneResp) {
        //                        if (updatePwdByPhoneResp.isOk()) {
        //                            mView.resetPwdSuccess();
        //                        } else {
        //                            ToastUtil.showShort(mView.getContext(), updatePwdByPhoneResp.getMsg());
        //                        }
        //                    }
        //
        //                    @Override
        //                    public void onError(Throwable t) {
        //                        Logger.e(t.getMessage());
        //                        mView.resetPwdFailure();
        //                    }
        //                });
    }

    @Override
    public void getSmsCode(String phone, int operateType) {
        final SmsCodeReq req = new SmsCodeReq();
        req.setPhoneNumber(phone);
        req.setOperateType(operateType);
        CheckAccountReq checkAccountReq = new CheckAccountReq();
        checkAccountReq.setPhone(phone);
        mUserApi.isExistAccount(checkAccountReq.params())
                .compose(mView.<CheckAccountResp>bindToLife())
                .compose(RxUtil.<CheckAccountResp>io_main())
                .filter(new Predicate<CheckAccountResp>() {
                    @Override
                    public boolean test(@NonNull CheckAccountResp checkAccountResp) throws Exception {
                        if (checkAccountResp.isOk()) {
                            mView.getSmsCodeFailure(mView.getContext().getString(R.string.partner_account_reset_empty_phone));
                        }
                        return !checkAccountResp.isOk();
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<CheckAccountResp, SmsCodeReq>() {
                    @Override
                    public SmsCodeReq apply(@NonNull CheckAccountResp checkAccountResp) throws Exception {
                        return req;
                    }
                })
                .flatMap(new Function<SmsCodeReq, Publisher<SmsCodeResp>>() {
                    @Override
                    public Publisher<SmsCodeResp> apply(@NonNull SmsCodeReq smsCodeReq) throws Exception {
                        return mUserApi.getSmsCode(smsCodeReq.params());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<SmsCodeResp>() {
                    @Override
                    public void onNext(SmsCodeResp smsCodeResp) {
                        if (smsCodeResp.isOk()) {
                            mView.getSmsCodeSuccess();
                        } else {
                            mView.getSmsCodeFailure(smsCodeResp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getSmsCodeFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
        //        mUserApi.getSmsCode(req.params())
        //                .compose(mView.<SmsCodeResp>bindToLife())
        //                .compose(RxUtil.<SmsCodeResp>io_main())
        //                .subscribe(new BaseSubscriber<SmsCodeResp>() {
        //                    @Override
        //                    public void onNext(SmsCodeResp smsCodeResp) {
        //                        if (smsCodeResp.isOk()) {
        //                            mView.getSmsCodeSuccess();
        //                        } else {
        //                            mView.getSmsCodeFailure(smsCodeResp.getMsg());
        //                        }
        //                    }
        //
        //                    @Override
        //                    public void onError(Throwable t) {
        //
        //                    }
        //                });
    }


}
