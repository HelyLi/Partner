package com.hhly.partner.presentation.view.property.selectbank;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.BankReq;
import com.hhly.partner.data.net.protocol.user.BankResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/5/2
 */

public class SelectBankPresenter implements SelectBankContract.Presenter {

    private final UserApi mUserApi;
    private SelectBankContract.View mView;

    public SelectBankPresenter(SelectBankContract.View view) {
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
}
