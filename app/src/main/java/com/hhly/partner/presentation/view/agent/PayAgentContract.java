package com.hhly.partner.presentation.view.agent;

import com.hhly.partner.data.net.protocol.proxy.RechargeByUnderAgentsDetailResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/24
 */

public interface PayAgentContract {

    interface Presenter extends BasePresenter {


        void getRechargeByUnderAgentsDetail(String flag, int pageNo);

    }

    interface View extends BaseView<Presenter> {

        void getRechargeByUnderAgentsDetailSuccess(List<RechargeByUnderAgentsDetailResp.DataBean> list);

        void getRechargeByUnderAgentsDetailFailure(String msg);
    }

}
