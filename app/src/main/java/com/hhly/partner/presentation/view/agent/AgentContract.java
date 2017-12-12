package com.hhly.partner.presentation.view.agent;

import com.hhly.partner.data.net.protocol.proxy.MyProxyDataResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/15
 */

public interface AgentContract {

    interface Presenter extends BasePresenter {
        void getMyProxyData(int orderBy,int pageNo);
    }

    interface View extends BaseView<Presenter> {
        void getMyProxyDataSuccess(List<MyProxyDataResp.DataBean> mData);

        void getMyProxyDataFailure(String msg);
    }

}
