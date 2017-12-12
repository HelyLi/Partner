package com.hhly.partner.presentation.view.account;

import android.text.TextUtils;
import android.widget.TextView;
import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.CheckPartnerNoReq;
import com.hhly.partner.data.net.protocol.user.CheckPartnerNoResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/5/25
 */

public class RegisterStepTwoPresenter implements RegisterStepTwoContract.Presenter {

    private final UserApi mUserApi;
    private RegisterStepTwoContract.View mView;

    public RegisterStepTwoPresenter(RegisterStepTwoContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void checkPartnerNo(final String pwd, final String partnerNo) {
        CheckPartnerNoReq req = new CheckPartnerNoReq();
        req.setPartnerNo(partnerNo);
        mUserApi.checkPartnerNo(req.params())
                .compose(RxUtil.<CheckPartnerNoResp>io_main())
                .compose(mView.<CheckPartnerNoResp>bindToLife())
                .subscribe(new BaseSubscriber<CheckPartnerNoResp>() {
                    @Override
                    public void onNext(CheckPartnerNoResp resp) {
                        if (resp.isOk()
                                && resp.getData() != null
                                && resp.getData().getData() != null
                                && resp.getData().getData().size() > 0
                                && !TextUtils.isEmpty(resp.getData().getData().get(0).getPARTNER_NO())) {
                            mView.checkPartnerNoSuccess(pwd, partnerNo);
                        } else {
                            mView.checkPartnerNoFailure(mView.getContext().getString(R.string.register_partner_no_err));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e.getMessage());
                        mView.checkPartnerNoFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }
}
