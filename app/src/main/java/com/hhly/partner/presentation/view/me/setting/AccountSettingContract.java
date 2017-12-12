package com.hhly.partner.presentation.view.me.setting;

import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

/**
 * Created by dell on 2017/4/20.
 */

interface AccountSettingContract {

    interface AccountSettingPresenter extends BasePresenter {
        //判断是否有设置支付密码
        void getPayPsw();

        void exit();
    }

    interface AccountSettingView extends BaseView<AccountSettingPresenter> {
        void getPayPswSuccess(boolean isHasPayPsw);

        void getPayPswFail(String msg);

        void exitSuccess(String msg);

        void exitFailure(String msg);
    }

    interface ModifyPswPresenter extends BasePresenter {
        void modifyPsw(String oldPassword,String newPassword);
    }

    interface ModifyPswView extends BaseView<ModifyPswPresenter> {
        void modifyPswSuccess(String msg);

        void modifyPswFailure(String msg);
    }

    interface SetPayPswPresenter extends BasePresenter {
        void setPayPsw(String payPsw);
    }

    interface SetPayPswView extends BaseView<SetPayPswPresenter> {
        void setPayPswSuccess(String msg);

        void setPayPswFailure(String msg);
    }

    interface ModifyPayPswPresenter extends BasePresenter {
        void getMobile(String phone);

        void getSmsCode(String phone);

        void modifyPayPsw(String phone, String authCode, String password);
    }

    interface ModifyPayPswView extends BaseView<ModifyPayPswPresenter> {
        void getMobileSuccess(String msg);

        void getMobileFailure(String msg);

        void getSmsCodeSuccess(String msg);

        void getSmsCodeFailure(String msg);

        void modifyPayPswSuccess(String msg);

        void modifyPayPswFailure(String msg);
    }
}
