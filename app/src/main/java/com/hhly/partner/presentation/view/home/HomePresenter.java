package com.hhly.partner.presentation.view.home;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.ProxyApi;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.proxy.IndexDataReq;
import com.hhly.partner.data.net.protocol.proxy.IndexDataResp;
import com.hhly.partner.data.net.protocol.proxy.MyProxyDataReq;
import com.hhly.partner.data.net.protocol.proxy.MyProxyDataResp;
import com.hhly.partner.data.net.protocol.user.*;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.MD5;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/4/14
 */

public class HomePresenter implements HomeContract.Presenter {

    private final ProxyApi mProxyApi;
    private final UserApi mUserApi;
    private HomeContract.View mView;


    public HomePresenter(HomeContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mProxyApi = RetrofitManager.getInstance(ApiType.PROXY_API).getProxyApi();
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();


    }

    @Override
    public void getIndexData() {
        IndexDataReq req = new IndexDataReq();
        mProxyApi.getIndexData(req.params())
                .compose(RxUtil.<IndexDataResp>io_main())
                .compose(mView.<IndexDataResp>bindToLife())
                .subscribe(new BaseSubscriber<IndexDataResp>() {
                    @Override
                    public void onNext(IndexDataResp indexDataResp) {
                        if (indexDataResp.isOk()) {
                            mView.getIndexDataSuccess(indexDataResp.getData());
                        } else {
                            mView.getIndexDataFailure(indexDataResp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getIndexDataFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });

        NoticeByIdReq noticeByIdReq = new NoticeByIdReq();
        noticeByIdReq.setId(0);
        //        mUserApi.getNotice()
    }
}
