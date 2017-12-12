package com.hhly.partner.presentation.view.property;

import com.hhly.partner.data.net.protocol.user.PriceResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/24
 */

public interface MyPropertyContract {

    interface Presenter extends BasePresenter {

        void getPriceData();

        void withdraw();

    }

    interface View extends BaseView<Presenter> {

        void getPriceSuccess(List<PriceResp.DataBeanX.DataBean> list);

        void getPriceFailure(String msg);

        void findIdCardFailure();

        void getPayPwdFailure();

        void withdrawSuccess();

        void withdrawFailure(String msg);
    }

}
