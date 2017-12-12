package com.hhly.partner.presentation.view.account;

import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

/**
 * description :
 * Created by Flynn
 * 2017/4/14
 */

public interface ResetPwdContract {

    interface Presenter extends BasePresenter {
        void resetPwd(String phone, String code, String newPwd);

        void getSmsCode(String phone, int operateType);
    }

    interface View extends BaseView<Presenter> {
        void resetPwdSuccess();

        void resetPwdFailure(String msg);

        void getSmsCodeSuccess();

        void getSmsCodeFailure(String msg);
    }

}
