package com.hhly.partner.presentation.view.home.headview;

import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

/**
 * description :
 * Created by Flynn
 * 2017/4/15
 */

public class HeadViewContract {

    interface Presenter extends BasePresenter {
        void getIndexData();
    }

    interface View extends BaseView<Presenter> {
        void getIndexDataSuccess();

        void getIndexDataFailure(String msg);
    }

}
