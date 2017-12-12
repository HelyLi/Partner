package com.hhly.partner.presentation.view.activity;

import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.NoticeReq;
import com.hhly.partner.data.net.protocol.user.NoticeResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;

/**
 * 合作活动presenterImpl
 * Created by dell on 2017/4/15.
 */

public class CollaborateActivityPresenterImpl implements ActivityCenterContract.CollaborateActivityPresenter {
    private UserApi mUserApi;
    private ActivityCenterContract.CollaborateActivityView mView;

    public CollaborateActivityPresenterImpl(ActivityCenterContract.CollaborateActivityView view) {
        mView = view;
        view.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void getActivities(int type) {
        final NoticeReq noticeReq = new NoticeReq();
        noticeReq.setType(type);
        mUserApi.getNotice(noticeReq.params())
                .compose(RxUtil.<NoticeResp>io_main())
                .compose(mView.<NoticeResp>bindToLife())
                .subscribe(new BaseSubscriber<NoticeResp>() {
                    @Override
                    public void onNext(NoticeResp noticeResp) {
                        if (noticeResp != null && noticeResp.isOk() && noticeResp.getData() != null
                                && noticeResp.getData().getPager() != null
                                && noticeResp.getData().getPager().getList() != null) {
                            mView.getActivitiesSuccess(noticeResp.getData().getPager().getList());
                        } else {
                            mView.getActivitiesFailure();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        mView.getActivitiesFailure();
                    }
                });
    }
}
