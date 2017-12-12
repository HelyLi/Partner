package com.hhly.partner.presentation.view.property.transaction;

import com.hhly.partner.data.net.protocol.user.WithdrawalInfoResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

/**
 * description :
 * Created by Flynn
 * 2017/4/24
 */

public interface TransactionContract {

    interface Presenter extends BasePresenter {
        void getWithdrawalInfo();
    }

    interface View extends BaseView<Presenter> {
        void getWithdrawalInfoSuccess(WithdrawalInfoResp.DataBeanX data);

        void getWithdrawalInfoFailure(String msg);
    }

}
