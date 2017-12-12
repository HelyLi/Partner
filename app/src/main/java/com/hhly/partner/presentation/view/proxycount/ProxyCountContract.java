package com.hhly.partner.presentation.view.proxycount;

import com.hhly.partner.data.net.protocol.proxy.*;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/20
 */

public interface ProxyCountContract {

    interface Presenter extends BasePresenter {

        void getTodayRechargeSum();

        void getProxyNumsData();

        void queryAppUnderAgentsNumByTimearea(int queryDate, String key);

        void queryAppUunderAgentsNumByTimearea(int queryDate, String key);

        void queryAppMemberNumByTimeArea(int queryDate, String key);

        void queryAppAgentProductRechargeTopNine();

        void getMembersAgentsRechargeNumGraph(int flag, int queryDate, String key);

        void getMembersAgentsRechargeNum();

        void queryAppAgentProductRechargeGraph(String gameId, int queryDate, String key);
    }

    interface View extends BaseView<Presenter> {
        void getTodayRechargeSumSuccess(GetTodayRechargeSumResp.DataBean dataBean);

        void getTodayRechargeSumFailure(String msg);

        void getProxyNumsDataSuccess(GetProxyNumsDataResp.DataBean dataBean);

        void getProxyNumsDataFailure(String msg);

        void queryAgentsNumByTimeareaSuccess(AppUnderAgentsNumByTimeareaResp.DataBean dataBean, String key);
        void getMembersAgentsRechargeNumGraphSuccess(AppUnderAgentsNumByTimeareaResp.DataBean dataBean, String key);

        void queryAgentsNumByTimeareaFailure(String msg);

        void queryAppAgentProductRechargeTopNineSuccess(List<AppAgentProductRechargeTopNineResp.DataBean> list);

        void queryAppAgentProductRechargeTopNineFailure(String msg);

        void getMembersAgentsRechargeNumSuccess(MembersAgentsRechargeNumResp.DataBean dataBean);

        void getMembersAgentsRechargeNumFailure(String msg);

        void queryAppAgentProductRechargeGraphSuccess(AppAgentProductRechargeGraphResp.DataBean dataBean,String key);

        void queryAppAgentProductRechargeGraphFailure(String msg);
    }
}
