package com.hhly.partner.presentation.view.proxycount;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.ProxyApi;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.protocol.proxy.*;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/4/20
 */

public class ProxyCountPresenter implements ProxyCountContract.Presenter {

    private final ProxyApi mProxyApi;
    private ProxyCountContract.View mView;

    public ProxyCountPresenter(ProxyCountContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mProxyApi = RetrofitManager.getInstance(ApiType.PROXY_API).getProxyApi();
    }

    @Override
    public void getTodayRechargeSum() {
        GetTodayRechargeSumReq req = new GetTodayRechargeSumReq();
        mProxyApi.getTodayRechargeSum(req.params())
                .compose(mView.<GetTodayRechargeSumResp>bindToLife())
                .compose(RxUtil.<GetTodayRechargeSumResp>io_main())
                .subscribe(new BaseSubscriber<GetTodayRechargeSumResp>() {
                    @Override
                    public void onNext(GetTodayRechargeSumResp getTodayRechargeSumResp) {
                        if (getTodayRechargeSumResp.isOk()) {
                            mView.getTodayRechargeSumSuccess(getTodayRechargeSumResp.getData());
                        } else {
                            mView.getTodayRechargeSumFailure(getTodayRechargeSumResp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getTodayRechargeSumFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    @Override
    public void getProxyNumsData() {
        GetProxyNumsDataReq req = new GetProxyNumsDataReq();
        mProxyApi.getProxyNumsData(req.params())
                .compose(mView.<GetProxyNumsDataResp>bindToLife())
                .compose(RxUtil.<GetProxyNumsDataResp>io_main())
                .subscribe(new BaseSubscriber<GetProxyNumsDataResp>() {
                    @Override
                    public void onNext(GetProxyNumsDataResp getProxyNumsDataResp) {
                        if (getProxyNumsDataResp.isOk()) {
                            mView.getProxyNumsDataSuccess(getProxyNumsDataResp.getData());
                        } else {
                            mView.getProxyNumsDataFailure(getProxyNumsDataResp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getProxyNumsDataFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    @Override
    public void queryAppUnderAgentsNumByTimearea(int queryDate, final String key) {
        AppUnderAgentsNumByTimeareaReq req = new AppUnderAgentsNumByTimeareaReq();
        req.setQueryDate(queryDate);
        mProxyApi.queryAppUnderAgentsNumByTimearea(req.params())
                .compose(mView.<AppUnderAgentsNumByTimeareaResp>bindToLife())
                .compose(RxUtil.<AppUnderAgentsNumByTimeareaResp>io_main())
                .subscribe(new BaseSubscriber<AppUnderAgentsNumByTimeareaResp>() {
                    @Override
                    public void onNext(AppUnderAgentsNumByTimeareaResp appUnderAgentsNumByTimeareaResp) {
                        if (appUnderAgentsNumByTimeareaResp.isOk()) {
                            mView.queryAgentsNumByTimeareaSuccess(appUnderAgentsNumByTimeareaResp.getData(), key);
                        } else {
                            mView.queryAgentsNumByTimeareaFailure(appUnderAgentsNumByTimeareaResp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.queryAgentsNumByTimeareaFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    @Override
    public void queryAppUunderAgentsNumByTimearea(int queryDate, final String key) {
        AppUnderAgentsNumByTimeareaReq req = new AppUnderAgentsNumByTimeareaReq();
        req.setQueryDate(queryDate);
        mProxyApi.queryAppUunderAgentsNumByTimearea(req.params())
                .compose(mView.<AppUnderAgentsNumByTimeareaResp>bindToLife())
                .compose(RxUtil.<AppUnderAgentsNumByTimeareaResp>io_main())
                .subscribe(new BaseSubscriber<AppUnderAgentsNumByTimeareaResp>() {
                    @Override
                    public void onNext(AppUnderAgentsNumByTimeareaResp appUnderAgentsNumByTimeareaResp) {
                        if (appUnderAgentsNumByTimeareaResp.isOk()) {
                            mView.queryAgentsNumByTimeareaSuccess(appUnderAgentsNumByTimeareaResp.getData(), key);
                        } else {
                            mView.queryAgentsNumByTimeareaFailure(appUnderAgentsNumByTimeareaResp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.queryAgentsNumByTimeareaFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    @Override
    public void queryAppMemberNumByTimeArea(int queryDate, final String key) {
        AppUnderAgentsNumByTimeareaReq req = new AppUnderAgentsNumByTimeareaReq();
        req.setQueryDate(queryDate);
        mProxyApi.queryAppMemberNumByTimeArea(req.params())
                .compose(mView.<AppUnderAgentsNumByTimeareaResp>bindToLife())
                .compose(RxUtil.<AppUnderAgentsNumByTimeareaResp>io_main())
                .subscribe(new BaseSubscriber<AppUnderAgentsNumByTimeareaResp>() {
                    @Override
                    public void onNext(AppUnderAgentsNumByTimeareaResp appUnderAgentsNumByTimeareaResp) {
                        if (appUnderAgentsNumByTimeareaResp.isOk()) {
                            mView.queryAgentsNumByTimeareaSuccess(appUnderAgentsNumByTimeareaResp.getData(), key);
                        } else {
                            mView.queryAgentsNumByTimeareaFailure(appUnderAgentsNumByTimeareaResp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.queryAgentsNumByTimeareaFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    @Override
    public void queryAppAgentProductRechargeTopNine() {
        final AppAgentProductRechargeTopNineReq req = new AppAgentProductRechargeTopNineReq();
        mProxyApi.queryAppAgentProductRechargeTopNine(req.params())
                .compose(mView.<AppAgentProductRechargeTopNineResp>bindToLife())
                .compose(RxUtil.<AppAgentProductRechargeTopNineResp>io_main())
                .subscribe(new BaseSubscriber<AppAgentProductRechargeTopNineResp>() {
                    @Override
                    public void onNext(AppAgentProductRechargeTopNineResp resp) {
                        if (resp.isOk()) {
                            mView.queryAppAgentProductRechargeTopNineSuccess(resp.getData());
                        } else {
                            mView.queryAppAgentProductRechargeTopNineFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.queryAppAgentProductRechargeTopNineFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    @Override
    public void getMembersAgentsRechargeNumGraph(int flag, int queryDate, final String key) {
        MembersAgentsRechargeNumGraphReq req = new MembersAgentsRechargeNumGraphReq();
        req.setFlag(flag);
        req.setQueryDate(queryDate);
        mProxyApi.getMembersAgentsRechargeNumGraph(req.params())
                .compose(mView.<AppUnderAgentsNumByTimeareaResp>bindToLife())
                .compose(RxUtil.<AppUnderAgentsNumByTimeareaResp>io_main())
                .subscribe(new BaseSubscriber<AppUnderAgentsNumByTimeareaResp>() {
                    @Override
                    public void onNext(AppUnderAgentsNumByTimeareaResp appUnderAgentsNumByTimeareaResp) {
                        if (appUnderAgentsNumByTimeareaResp.isOk()) {
                            mView.getMembersAgentsRechargeNumGraphSuccess(appUnderAgentsNumByTimeareaResp.getData(), key);
                        } else {
                            mView.queryAgentsNumByTimeareaFailure(appUnderAgentsNumByTimeareaResp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.queryAgentsNumByTimeareaFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    @Override
    public void getMembersAgentsRechargeNum() {
        final MembersAgentsRechargeNumReq req = new MembersAgentsRechargeNumReq();
        mProxyApi.getMembersAgentsRechargeNum(req.params())
                .compose(mView.<MembersAgentsRechargeNumResp>bindToLife())
                .compose(RxUtil.<MembersAgentsRechargeNumResp>io_main())
                .subscribe(new BaseSubscriber<MembersAgentsRechargeNumResp>() {
                    @Override
                    public void onNext(MembersAgentsRechargeNumResp resp) {
                        if (resp.isOk()) {
                            mView.getMembersAgentsRechargeNumSuccess(resp.getData());
                        } else {
                            mView.getMembersAgentsRechargeNumFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getMembersAgentsRechargeNumFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    @Override
    public void queryAppAgentProductRechargeGraph(String gameId, int queryDate, final String key) {

        final AppAgentProductRechargeGraphReq req = new AppAgentProductRechargeGraphReq();
        req.setGameId(gameId);
        req.setQueryDate(queryDate);
        mProxyApi.queryAppAgentProductRechargeGraph(req.params())
                .compose(mView.<AppAgentProductRechargeGraphResp>bindToLife())
                .compose(RxUtil.<AppAgentProductRechargeGraphResp>io_main())
                .subscribe(new BaseSubscriber<AppAgentProductRechargeGraphResp>() {
                    @Override
                    public void onNext(AppAgentProductRechargeGraphResp resp) {
                        if (resp.isOk()){
                            mView.queryAppAgentProductRechargeGraphSuccess(resp.getData(),key);
                        }else {
                            mView.queryAppAgentProductRechargeGraphFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.queryAppAgentProductRechargeGraphFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });

    }


}
