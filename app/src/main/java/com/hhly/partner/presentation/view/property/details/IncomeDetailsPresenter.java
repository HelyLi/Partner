package com.hhly.partner.presentation.view.property.details;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.AccPriceInfoReq;
import com.hhly.partner.data.net.protocol.user.AccPriceInfoResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/4/27
 */

public class IncomeDetailsPresenter implements IncomeDetailsContract.Presenter {

    private final UserApi mUserApi;
    private IncomeDetailsContract.View mView;

    public IncomeDetailsPresenter(IncomeDetailsContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void getAccPriceInfo(int type, String date) {
        AccPriceInfoReq req = new AccPriceInfoReq();
        req.setStart(date);
        req.setType(type);
        mUserApi.getAccPriceInfo(req.params())
                .compose(mView.<AccPriceInfoResp>bindToLife())
                .compose(RxUtil.<AccPriceInfoResp>io_main())
                .subscribe(new BaseSubscriber<AccPriceInfoResp>() {
                    @Override
                    public void onNext(AccPriceInfoResp resp) {

                        if (resp.isOk()) {
                            mView.getAccPriceInfoSuccess(resp.getData().getData());
                        } else {
                            mView.getAccPriceInfoFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getAccPriceInfoFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }
}
