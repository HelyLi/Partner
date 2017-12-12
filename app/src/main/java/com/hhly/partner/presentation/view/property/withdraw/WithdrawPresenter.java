package com.hhly.partner.presentation.view.property.withdraw;

import android.text.TextUtils;
import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.*;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.MD5;
import com.hhly.partner.presentation.utils.RxUtil;
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
 * 2017/5/2
 */

public class WithdrawPresenter implements WithdrawContract.Presenter {

    private final UserApi mUserApi;
    private WithdrawContract.View mView;

    public WithdrawPresenter(WithdrawContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void getBank() {
        BankReq req = new BankReq();
        mUserApi.getBank(req.params())
                .compose(mView.<BankResp>bindToLife())
                .compose(RxUtil.<BankResp>io_main())
                .subscribe(new BaseSubscriber<BankResp>() {
                    @Override
                    public void onNext(BankResp resp) {
                        if (resp.isOk()) {
                            mView.getBankSuccess(resp.getData().getData());
                        } else {
                            mView.getBankFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getBankFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    @Override
    public void getPriceData() {
        PriceReq req = new PriceReq();
        mUserApi.getPrice(req.params())
                .compose(mView.<PriceResp>bindToLife())
                .compose(RxUtil.<PriceResp>io_main())
                .subscribe(new BaseSubscriber<PriceResp>() {
                    @Override
                    public void onNext(PriceResp resp) {

                        if (resp.isOk()) {
                            mView.getPriceSuccess(resp.getData().getData());
                        } else {
                            mView.getPriceFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getPriceFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    @Override
    public void getWithdraWal() {
        GetWithdraWalReq req = new GetWithdraWalReq();
        mUserApi.getWithdraWal(req.params())
                .compose(mView.<GetWithdraWalResp>bindToLife())
                .compose(RxUtil.<GetWithdraWalResp>io_main())
                .subscribe(new BaseSubscriber<GetWithdraWalResp>() {
                    @Override
                    public void onNext(GetWithdraWalResp resp) {

                        if (resp.isOk()) {
                            mView.getWithdraWalSuccess(resp.getCurrentWithdrawalConf());
                        } else {
                            mView.getWithdraWalFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getWithdraWalFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    @Override
    public void addWithdrawal(Double withdrawalPrice, final String pwd) {
        final WithdrawalReq req = new WithdrawalReq();
        req.setWithdrawalPrice(withdrawalPrice);
        req.setPwd(new MD5(pwd).getMd5_32());
//        mUserApi.addWithdrawal(req.params())
//                .compose(mView.<WithdrawalResp>bindToLife())
//                .compose(RxUtil.<WithdrawalResp>io_main())
//                .subscribe(new BaseSubscriber<WithdrawalResp>() {
//                    @Override
//                    public void onNext(WithdrawalResp resp) {
//                        if (resp.isOk()) {
//                            mView.addWithdrawalSuccess(resp.getData());
//                        } else {
//                            mView.addWithdrawalFailure(resp.getMsg());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        Logger.e(t.getMessage());
//                        mView.addWithdrawalFailure(mView.getContext().getString(R.string.partner_request_network_error));
//                    }
//                });


        FindWithdraWalReq findWithdraWalReq = new FindWithdraWalReq();
        mUserApi.findWithdraWal(findWithdraWalReq.params())
                .compose(mView.<FindWithdraWalResp>bindToLife())
                .compose(RxUtil.<FindWithdraWalResp>io_main())
                .filter(new Predicate<FindWithdraWalResp>() {
                    @Override
                    public boolean test(@NonNull FindWithdraWalResp resp) throws Exception {
                        if (!(resp.isOk() &&
                                (resp.getData() == null
                                        || resp.getData().getData() == null
                                        || resp.getData().getData().size() == 0
                                        || resp.getData().getData().get(0) == null))) {
                            mView.findWithdraWalFailure(mView.getContext().getString(R.string.home_my_assets_withdraw_failure));
                        }
                        return resp.isOk() &&
                                (resp.getData() == null
                                        || resp.getData().getData() == null
                                        || resp.getData().getData().size() == 0
                                        || resp.getData().getData().get(0) == null);
                    }
                })
                .map(new Function<FindWithdraWalResp, PayPwdReq>() {
                    @Override
                    public PayPwdReq apply(@NonNull FindWithdraWalResp resp) throws Exception {
                        return new PayPwdReq();
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<PayPwdReq, Publisher<PayPwdResp>>() {
                    @Override
                    public Publisher<PayPwdResp> apply(@NonNull PayPwdReq payPwdReq) throws Exception {
                        return mUserApi.getPayPwd(payPwdReq.params());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<PayPwdResp>() {
                    @Override
                    public boolean test(@NonNull PayPwdResp resp) throws Exception {
                        if (TextUtils.isEmpty(resp.getData().getData().get(0).getPASSWORD())) {
                            mView.getPayPwdFailure(mView.getContext().getString(R.string.partner_request_network_error));
                        } else if (!resp.getData().getData().get(0).getPASSWORD().equalsIgnoreCase(new MD5(pwd).getMd5_32())) {
                            mView.getPayPwdFailure(mView.getContext().getString(R.string.home_my_assets_pay_pwd_error));
                        }
                        return !TextUtils.isEmpty(resp.getData().getData().get(0).getPASSWORD())
                                && resp.getData().getData().get(0).getPASSWORD().equalsIgnoreCase(new MD5(pwd).getMd5_32());
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<PayPwdResp, WithdrawalReq>() {
                    @Override
                    public WithdrawalReq apply(@NonNull PayPwdResp resp) throws Exception {
                        return req;
                    }
                })
                .observeOn(Schedulers.io())
                .flatMap(new Function<WithdrawalReq, Publisher<WithdrawalResp>>() {
                    @Override
                    public Publisher<WithdrawalResp> apply(@NonNull WithdrawalReq withdrawalReq) throws Exception {
                        return mUserApi.addWithdrawal(req.params());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<WithdrawalResp>() {
                    @Override
                    public void onNext(WithdrawalResp resp) {
                        if (resp.isOk()) {
                            mView.addWithdrawalSuccess(resp.getData());
                        } else {
                            mView.addWithdrawalFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.addWithdrawalFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });


    }
}
