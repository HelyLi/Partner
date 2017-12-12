package com.hhly.partner.presentation.view.salary;

import com.hhly.partner.data.net.protocol.user.CommissionTypeResp;
import com.hhly.partner.data.net.protocol.user.FindAllCommissionTypeResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/26
 */

public interface SalaryModelModifyContract {

    interface Presenter extends BasePresenter {
        void findAllCommissionType();

        void getCommissionType();

        void updateCommissionType(String commissionChoseInfo);

        void register(String phone, String password,String inviteCode,String commissionChoseInfo);
    }

    interface View extends BaseView<Presenter> {
        void findAllCommissionTypeSuccess(List<FindAllCommissionTypeResp.DataBeanX.DataBean> list);

        void findAllCommissionTypeFailure(String msg);

        void getCommissionTypeSuccess(List<CommissionTypeResp.DataBeanX.DataBean> list);

        void getCommissionTypeFailure(String msg);

        void updateCommissionTypeSuccess();

        void updateCommissionTypeFailure(String msg);

        void onRegisterSuccess(String msg);

        void onRegisterFail(String msg);
    }
}
