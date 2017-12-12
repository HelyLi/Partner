package com.hhly.partner.presentation.view.property.selectbank;

import com.hhly.partner.data.net.protocol.user.BankResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/5/2
 */

public interface SelectBankContract {
    interface Presenter extends BasePresenter {
        void getBank();
    }

    interface View extends BaseView<Presenter> {
        void getBankSuccess(List<BankResp.DataBeanX.DataBean> list);

        void getBankFailure(String msg);
    }
}
