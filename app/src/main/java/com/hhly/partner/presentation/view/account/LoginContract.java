package com.hhly.partner.presentation.view.account;


import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

/**
 * Created by dell on 2017/4/13.
 */

public interface LoginContract {
    interface Presenter extends BasePresenter{
        void login(String account,String password);
        void thirdLogin(String accessToken, int type, String appId);
    }
    interface View extends BaseView<Presenter>{
        void doLoginSuccess();
        void doLoginFail(String msg);
    }
}
