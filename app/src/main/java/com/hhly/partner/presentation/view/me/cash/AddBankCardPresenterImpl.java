package com.hhly.partner.presentation.view.me.cash;

import android.text.TextUtils;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.GetRealNameReq;
import com.hhly.partner.data.net.protocol.user.GetRealNameResp;
import com.hhly.partner.data.net.protocol.user.SmsCodeReq;
import com.hhly.partner.data.net.protocol.user.SmsCodeResp;
import com.hhly.partner.data.net.protocol.user.UpdateBankInfoReq;
import com.hhly.partner.data.net.protocol.user.UpdateBankInfoResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;

/**
 * Created by dell on 2017/4/18.
 */

public class AddBankCardPresenterImpl implements AddBankCardContract.Presenter {
    private AddBankCardContract.View mView;
    private UserApi mUserApi;

    public AddBankCardPresenterImpl(AddBankCardContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void getRealName() {
        GetRealNameReq getRealNameReq = new GetRealNameReq();
        mUserApi.getRealName(getRealNameReq.params())
                .compose(RxUtil.<GetRealNameResp>io_main())
                .compose(mView.<GetRealNameResp>bindToLife())
                .subscribe(new BaseSubscriber<GetRealNameResp>() {
                    @Override
                    public void onNext(GetRealNameResp resp) {
                        if (resp != null && resp.isOk() && resp.getData() != null
                                && resp.getData().getData() != null
                                && resp.getData().getData().get(0) != null
                                && !TextUtils.isEmpty(resp.getData().getData().get(0).getCARDNAME())) {
                            mView.getRealNameSuccess(resp.getData().getData().get(0).getCARDNAME());
                        } else {
                            mView.getRealNameFailure(mView.getContext().getString(R.string.partner_request_error));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        mView.getRealNameFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }

    @Override
    public void getAuthCode(String phone) {
        final SmsCodeReq smsCodeReq = new SmsCodeReq();
        smsCodeReq.setPhoneNumber(phone);
        smsCodeReq.setOperateType(10);
        mUserApi.getSmsCode(smsCodeReq.params())
                .compose(RxUtil.<SmsCodeResp>io_main())
                .compose(mView.<SmsCodeResp>bindToLife())
                .subscribe(new BaseSubscriber<SmsCodeResp>() {
                    @Override
                    public void onNext(SmsCodeResp smsCodeResp) {
                        if (smsCodeResp != null && smsCodeResp.isOk()) {
                            mView.getAuthCodeSuccess(smsCodeResp.getMsg());
                        } else {
                            mView.getAuthCodeFailure(mView.getContext().getString(R.string.register_get_code_fail));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getAuthCodeFailure(mView.getContext().getString(R.string.register_get_code_fail));
                    }
                });
    }

    @Override
    public void addBankCard(String bankName, String bankNo, String name, String mobile, String authCode) {
        final UpdateBankInfoReq updateBankInfoReq = new UpdateBankInfoReq();
        updateBankInfoReq.setBankName(bankName);
        updateBankInfoReq.setBankNo(bankNo);
        updateBankInfoReq.setName(name);
        updateBankInfoReq.setSendNo(authCode);
        updateBankInfoReq.setMobile(mobile);

        final SmsCodeReq smsCodeReq = new SmsCodeReq();
        smsCodeReq.setPhoneNumber(mobile);
        smsCodeReq.setAuthCode(authCode);
        smsCodeReq.setOperateType(10);
        mUserApi.validateSmsCode(smsCodeReq.params())
                .compose(mView.<SmsCodeResp>bindToLife())
                .compose(RxUtil.<SmsCodeResp>io_main())
                .filter(new Predicate<SmsCodeResp>() {
                    @Override
                    public boolean test(@NonNull SmsCodeResp smsCodeResp) throws Exception {
                        if (!smsCodeResp.isOk()) {
                            mView.addBankCardFailure(mView.getContext().getString(R.string.partner_account_reset_code_err));
                        }
                        return smsCodeResp.isOk();
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<SmsCodeResp, UpdateBankInfoReq>() {
                    @Override
                    public UpdateBankInfoReq apply(@NonNull SmsCodeResp smsCodeResp) throws Exception {
                        return updateBankInfoReq;
                    }
                })
                .flatMap(new Function<UpdateBankInfoReq, Publisher<UpdateBankInfoResp>>() {
                    @Override
                    public Publisher<UpdateBankInfoResp> apply(@NonNull UpdateBankInfoReq updateBankInfoReq) throws Exception {
                        return mUserApi.updateBankInfo(updateBankInfoReq.params());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<UpdateBankInfoResp>() {
                    @Override
                    public void onNext(UpdateBankInfoResp resp) {
                        if (resp != null && resp.isOk()) {
                            mView.addBankCardSuccess(mView.getContext().getString(R.string.partner_personal_account_add_card_success));
                        } else {
                            mView.addBankCardFailure(TextUtils.isEmpty(resp.getMsg()) ? resp.getMsg()
                                    : mView.getContext().getString(R.string.partner_personal_account_add_card_fail));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.addBankCardFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });


        //        mUserApi.updateBankInfo(updateBankInfoReq.params())
        //                .compose(RxUtil.<UpdateBankInfoResp>io_main())
        //                .compose(mView.<UpdateBankInfoResp>bindToLife())
        //                .subscribe(new BaseSubscriber<UpdateBankInfoResp>() {
        //                    @Override
        //                    public void onNext(UpdateBankInfoResp resp) {
        //                        if (resp != null && resp.isOk()) {
        //                            mView.addBankCardSuccess(mView.getContext().getString(R.string.partner_personal_account_add_card_success));
        //                        } else {
        //                            mView.addBankCardFailure(TextUtils.isEmpty(resp.getMsg()) ? resp.getMsg()
        //                                    : mView.getContext().getString(R.string.partner_personal_account_add_card_fail));
        //                        }
        //                    }
        //
        //                    @Override
        //                    public void onError(Throwable t) {
        //                        mView.addBankCardFailure(mView.getContext().getString(R.string.partner_request_error));
        //                    }
        //                });
    }

}
