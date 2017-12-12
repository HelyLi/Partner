package com.hhly.partner.presentation.view.member;

import com.hhly.partner.data.net.protocol.proxy.GetMembersRechargeSumResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/24
 */

public interface MemberContract {

    interface Presenter extends BasePresenter {

        void getMembersRechargeSum(int pageNo);

        void getMemberDaySum(int pageNo);

        void getMemberWeekSum(int pageNo);

        void getMemberMonthSum(int pageNo);

        void getRechargeByMemberDetail(String flag, int pageNo);

    }

    interface View extends BaseView<Presenter> {

        void getMembersRechargeSumSuccess(List<GetMembersRechargeSumResp.DataBean> list);

        void getMembersRechargeSumFailure(String msg);
    }

}
