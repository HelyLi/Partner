package com.hhly.partner.presentation.view.me.code;

import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

/**
 * 邀请码Contract
 * Created by dell on 2017/5/2.
 */

public interface InviteCodeContract {
    interface Presenter extends BasePresenter {
        void getInviteCode();
    }

    interface View extends BaseView<Presenter> {
        void getInviteCodeSuccess(String inviteCode);

        void getInviteCodeFail(String msg);
    }
}
