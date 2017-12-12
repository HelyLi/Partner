package com.hhly.partner.presentation.view.activity;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.NoticeByIdReq;
import com.hhly.partner.data.net.protocol.user.NoticeByIdResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * 活动详情presenter
 * Created by dell on 2017/4/25.
 */

public class ActivityDetailsPresenterImpl implements ActivityCenterContract.ActivityDetailsPresenter {
    private ActivityCenterContract.ActivityDetailsView mView;
    private UserApi mUserApi;

    public ActivityDetailsPresenterImpl(ActivityCenterContract.ActivityDetailsView view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void getActivityDetailsInfo(int activityId) {
        NoticeByIdReq noticeByIdReq = new NoticeByIdReq();
        noticeByIdReq.setId(activityId);
        mUserApi.getNoticeById(noticeByIdReq.params())
                .compose(RxUtil.<NoticeByIdResp>io_main())
                .compose(mView.<NoticeByIdResp>bindToLife())
                .subscribe(new BaseSubscriber<NoticeByIdResp>() {
                    @Override
                    public void onNext(NoticeByIdResp resp) {
                        if (resp != null && resp.isOk() && resp.getData() != null && resp.getData().getNotice() != null) {
                            mView.getActivityDetailsInfoSuccess(resp.getData().getNotice());
                        } else {
                            mView.getActivityDetailsInfoFail(resp.getData() != null ? resp.getData().getMsgX() : "");
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.d("onError:" + t.getMessage());
                        mView.getActivityDetailsInfoFail(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }
}
