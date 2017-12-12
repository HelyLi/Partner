package com.hhly.partner.presentation.view.agent;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.ProxyApi;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.protocol.proxy.RechargeByUnderAgentsDetailReq;
import com.hhly.partner.data.net.protocol.proxy.RechargeByUnderAgentsDetailResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/4/24
 */

public class PayAgentPresenter implements PayAgentContract.Presenter {

    private final ProxyApi mProxyApi;
    private PayAgentContract.View mView;

    public PayAgentPresenter(PayAgentContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mProxyApi = RetrofitManager.getInstance(ApiType.PROXY_API).getProxyApi();
    }


    @Override
    public void getRechargeByUnderAgentsDetail(String flag, int pageNo) {
        RechargeByUnderAgentsDetailReq req = new RechargeByUnderAgentsDetailReq();
        req.setFlag(flag);
        req.setPageNo(pageNo);
        mProxyApi.getRechargeByUnderAgentsDetail(req.params())
                .compose(mView.<RechargeByUnderAgentsDetailResp>bindToLife())
                .compose(RxUtil.<RechargeByUnderAgentsDetailResp>io_main())
                .subscribe(new BaseSubscriber<RechargeByUnderAgentsDetailResp>() {
                    @Override
                    public void onNext(RechargeByUnderAgentsDetailResp resp) {
                        if (resp.isOk()) {
                            mView.getRechargeByUnderAgentsDetailSuccess(resp.getData());
                        } else {
                            mView.getRechargeByUnderAgentsDetailFailure(resp.getMsg());
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getRechargeByUnderAgentsDetailFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }
}
