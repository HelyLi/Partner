package com.hhly.partner.presentation.view.me.cash;

import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

/**
 * Created by dell on 2017/4/19.
 */

interface AuthenticationContract {
    interface Presenter extends BasePresenter{
        void submitAuthentication(String name,String idNum);
    }
    interface View  extends BaseView<AuthenticationContract.Presenter>{
        void submitAuthenticationSuccess(String msg);
        void submitAuthenticationFailure(String msg);
    }
}
