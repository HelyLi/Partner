package com.hhly.partner.presentation.view.property.incomedetail;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.AccPriceReq;
import com.hhly.partner.data.net.protocol.user.AccPriceResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/4/25
 */

public class IncomeDetailPresenter implements IncomeDetailContract.Presenter {

    private final UserApi mUserApi;
    private IncomeDetailContract.View mView;

    public IncomeDetailPresenter(IncomeDetailContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void getAccPrice(String startDate, String endDate) {
        AccPriceReq req = new AccPriceReq();
        req.setStart(startDate);
        req.setEnd(endDate);
        mUserApi.getAccPrice(req.params())
                .compose(mView.<AccPriceResp>bindToLife())
                .compose(RxUtil.<AccPriceResp>io_main())
                .subscribe(new BaseSubscriber<AccPriceResp>() {
                    @Override
                    public void onNext(AccPriceResp resp) {
                        if (resp.isOk()) {
                            mView.getAccPriceSuccess(resp.getData());
                        } else {
                            mView.getAccPriceFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getAccPriceFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }
}
