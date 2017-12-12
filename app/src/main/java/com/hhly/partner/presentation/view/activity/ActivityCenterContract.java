package com.hhly.partner.presentation.view.activity;

import com.hhly.partner.data.net.protocol.game.GetIndexlbtResp;
import com.hhly.partner.data.net.protocol.user.NoticeByIdResp;
import com.hhly.partner.data.net.protocol.user.NoticeResp;
import com.hhly.partner.presentation.view.BasePresenter;
import com.hhly.partner.presentation.view.BaseView;

import java.util.List;

/**
 * Created by dell on 2017/4/15.
 */

interface ActivityCenterContract {
    interface CollaborateActivityPresenter extends BasePresenter{
        void getActivities(int type);
    }
    interface CollaborateActivityView extends BaseView<CollaborateActivityPresenter>{
        void getActivitiesSuccess(List<NoticeResp.DataBean.PagerBean.ListBean> respList);
        void getActivitiesFailure();
    }

    interface OfficialActivityPresenter extends BasePresenter{
        void getActivities(int pageNo);
    }
    interface OfficialActivityView extends BaseView<OfficialActivityPresenter>{
        void getActivitiesSuccess(List<GetIndexlbtResp.DataBean.ListBean> respList,int totalPages);
        void getActivitiesFailure();
    }

    interface ActivityDetailsPresenter extends BasePresenter{
        void getActivityDetailsInfo(int activityId);
    }

    interface ActivityDetailsView extends BaseView<ActivityCenterContract.ActivityDetailsPresenter>{
        void getActivityDetailsInfoSuccess(NoticeByIdResp.DataBean.NoticeBean noticeBean);
        void getActivityDetailsInfoFail(String msg);
    }
}
