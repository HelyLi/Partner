package com.hhly.partner.presentation.view.property.details;

import com.hhly.partner.data.net.protocol.user.AccPriceInfoResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/27
 */

public interface IncomeDetailsContract {

    interface Presenter extends BasePresenter {
        void getAccPriceInfo(int type, String date);
    }

    interface View extends BaseView<Presenter> {
        void getAccPriceInfoSuccess(List<AccPriceInfoResp.DataBeanX.DataBean> dataBean);

        void getAccPriceInfoFailure(String msg);
    }
}
