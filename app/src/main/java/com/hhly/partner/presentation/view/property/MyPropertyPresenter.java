package com.hhly.partner.presentation.view.property;

import android.text.TextUtils;
import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.*;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;

/**
 * description :
 * Created by Flynn
 * 2017/4/24
 */

public class MyPropertyPresenter implements MyPropertyContract.Presenter {

    private final UserApi mUserApi;
    MyPropertyContract.View mView;

    public MyPropertyPresenter(MyPropertyContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
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
    public void withdraw() {
        final FindIdCardReq req = new FindIdCardReq();
        mUserApi.findIdCard(req.params())
                .compose(mView.<FindIdCardResp>bindToLife())
                .compose(RxUtil.<FindIdCardResp>io_main())
                .filter(new Predicate<FindIdCardResp>() {
                    @Override
                    public boolean test(@NonNull FindIdCardResp resp) throws Exception {
                        if (TextUtils.isEmpty(resp.getData().getData().get(0).getCARDNO())) {
                            mView.findIdCardFailure();
                        }
                        return !TextUtils.isEmpty(resp.getData().getData().get(0).getCARDNO());
                    }
                })
                .map(new Function<FindIdCardResp, PayPwdReq>() {
                    @Override
                    public PayPwdReq apply(@NonNull FindIdCardResp resp) throws Exception {
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
                            mView.getPayPwdFailure();
                        }
                        return !TextUtils.isEmpty(resp.getData().getData().get(0).getPASSWORD());
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Function<PayPwdResp, FindWithdraWalReq>() {
                    @Override
                    public FindWithdraWalReq apply(@NonNull PayPwdResp resp) throws Exception {
                        return new FindWithdraWalReq();
                    }
                })
                .flatMap(new Function<FindWithdraWalReq, Publisher<FindWithdraWalResp>>() {
                    @Override
                    public Publisher<FindWithdraWalResp> apply(@NonNull FindWithdraWalReq findWithdraWalReq) throws Exception {
                        return mUserApi.findWithdraWal(findWithdraWalReq.params());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<FindWithdraWalResp>() {
                    @Override
                    public void onNext(FindWithdraWalResp resp) {
                        if (resp.isOk() &&
                                (resp.getData() == null
                                        || resp.getData().getData() == null
                                        || resp.getData().getData().size() == 0
                                        || resp.getData().getData().get(0) == null)) {
                            mView.withdrawSuccess();
                        } else {
                            mView.withdrawFailure(mView.getContext().getString(R.string.home_my_assets_withdraw_failure));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.withdrawFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }
}
