package com.hhly.partner.presentation.view.home;

import com.hhly.partner.data.net.protocol.proxy.IndexDataResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

/**
 * description :
 * Created by Flynn
 * 2017/4/14
 */

public interface HomeContract {

    interface Presenter extends BasePresenter {

        void getIndexData();
    }

    interface View extends BaseView<Presenter> {
        void getIndexDataSuccess(IndexDataResp.DataBean dataBean);

        void getIndexDataFailure(String msg);
    }

}
