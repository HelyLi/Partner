package com.hhly.partner.presentation.view.agent;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.ProxyApi;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.protocol.proxy.MyProxyDataReq;
import com.hhly.partner.data.net.protocol.proxy.MyProxyDataResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/4/15
 */

public class AgentPresenter implements AgentContract.Presenter {

    private final AgentContract.View mView;
    private final ProxyApi mProxyApi;

    AgentPresenter(AgentContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mProxyApi = RetrofitManager.getInstance(ApiType.PROXY_API).getProxyApi();
    }

    @Override
    public void getMyProxyData(int orderBy, int pageNo) {
        MyProxyDataReq req = new MyProxyDataReq();
        req.setOrderby(orderBy);
        req.setPageNo(pageNo);
        mProxyApi.getMyProxyData(req.params())
                .compose(RxUtil.<MyProxyDataResp>io_main())
                .compose(mView.<MyProxyDataResp>bindToLife())
                .subscribe(new BaseSubscriber<MyProxyDataResp>() {
                    @Override
                    public void onNext(MyProxyDataResp myProxyDataResp) {
                        if (myProxyDataResp.isOk()) {
                            mView.getMyProxyDataSuccess(myProxyDataResp.getData());
                        } else {
                            mView.getMyProxyDataFailure(myProxyDataResp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        mView.getMyProxyDataFailure(mView.getContext().getString(R.string.partner_request_error));
                        Logger.e(t.getMessage());
                    }
                });

    }
}
