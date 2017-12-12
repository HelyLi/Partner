package com.hhly.partner.presentation.view.property.incomedetail;

import com.hhly.partner.data.net.protocol.user.AccPriceResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

/**
 * description :
 * Created by Flynn
 * 2017/4/25
 */

public interface IncomeDetailContract {

    interface Presenter extends BasePresenter {

        void getAccPrice(String startDate, String endDate);
    }

    interface View extends BaseView<Presenter> {

        void getAccPriceSuccess(AccPriceResp.DataBeanX dataBean);

        void getAccPriceFailure(String msg);
    }

}
