package com.hhly.partner.presentation.view.me.cash;

import android.text.TextUtils;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.BankReq;
import com.hhly.partner.data.net.protocol.user.BankResp;
import com.hhly.partner.data.net.protocol.user.FindIdCardReq;
import com.hhly.partner.data.net.protocol.user.FindIdCardResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.CollectionUtil;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

/**
 * 提现账户presenterImpl
 * Created by dell on 2017/4/17.
 */

public class CashAccountPresenterImpl implements CashAccountContract.Presenter {
    private CashAccountContract.View mView;
    private UserApi mUserApi;

    public CashAccountPresenterImpl(CashAccountContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void getBankCardInfo() {
        BankReq bankReq = new BankReq();
        mUserApi.getBank(bankReq.params()).compose(RxUtil.<BankResp>io_main())
                .compose(mView.<BankResp>bindToLife())
                .subscribe(new BaseSubscriber<BankResp>() {
                    @Override
                    public void onNext(BankResp bankResp) {
                        if (bankResp != null && bankResp.isOk() && bankResp.getData() != null
                                && CollectionUtil.isNotEmpty(bankResp.getData().getData())) {
                            if (!TextUtils.isEmpty(bankResp.getData().getData().get(0).getBANK_ID())) {
                                mView.getBankCardInfoSuccess(bankResp.getData().getData());
                            } else {
                                mView.getBankCardInfoSuccess(new ArrayList<BankResp.DataBeanX.DataBean>());
                            }
                        } else {
                            mView.getBankCardInfoFailure();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError:" + t.getMessage());
                        mView.getBankCardInfoFailure();
                    }
                });
    }

    @Override
    public void checkAccountIsAuthed() {
        FindIdCardReq findIdCardReq = new FindIdCardReq();
        mUserApi.findIdCard(findIdCardReq.params())
                .compose(RxUtil.<FindIdCardResp>io_main())
                .compose(mView.<FindIdCardResp>bindToLife())
                .subscribe(new BaseSubscriber<FindIdCardResp>() {
                    @Override
                    public void onNext(FindIdCardResp resp) {
                        if (resp != null && resp.isOk() && resp.getData() != null
                                && CollectionUtil.isNotEmpty(resp.getData().getData())) {
                            FindIdCardResp.DataBeanX.DataBean dataBean = resp.getData().getData().get(0);
                            if (dataBean != null && !TextUtils.isEmpty(dataBean.getCARDNO())
                                    && !"null".equalsIgnoreCase(dataBean.getCARDNO())) {
                                mView.onCheckIsAuthedSuccess(true);
                            } else {
                                mView.onCheckIsAuthedSuccess(false);
                            }
                        } else {
                            mView.onCheckIsAuthedFailure(mView.getContext().getString(R.string.partner_request_error));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError:"+t.getMessage());
                        mView.onCheckIsAuthedFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }
}
