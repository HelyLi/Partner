package com.hhly.partner.presentation.view.me.notice;

import com.hhly.partner.data.net.protocol.user.NoticeResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

import java.util.List;

/**
 * 通知contract
 * Created by dell on 2017/5/2.
 */

public interface NoticeContract {
    interface Presenter extends BasePresenter {
        void getNotice();
    }

    interface View extends BaseView<Presenter> {
        void getNoticeSuccess(List<NoticeResp.DataBean.PagerBean.ListBean> list, int totalPages);

        void getNoticeFailure(String msg);
    }
}
