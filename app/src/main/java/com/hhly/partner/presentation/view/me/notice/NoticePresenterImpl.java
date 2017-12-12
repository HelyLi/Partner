package com.hhly.partner.presentation.view.me.notice;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.NoticeReq;
import com.hhly.partner.data.net.protocol.user.NoticeResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * Created by dell on 2017/5/2.
 */

public class NoticePresenterImpl implements NoticeContract.Presenter {
    private NoticeContract.View mView;
    private UserApi mUserApi;

    public NoticePresenterImpl(NoticeContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void getNotice() {
        NoticeReq req = new NoticeReq();
        req.setType(4);
        mUserApi.getNotice(req.params())
                .compose(RxUtil.<NoticeResp>io_main())
                .compose(mView.<NoticeResp>bindToLife())
                .subscribe(new BaseSubscriber<NoticeResp>() {
                    @Override
                    public void onNext(NoticeResp resp) {
                        if (resp != null && resp.isOk() && resp.getData() != null && resp.getData().getPager() != null) {
                            mView.getNoticeSuccess(resp.getData().getPager().getList(), resp.getData().getPager().getTotalPages());
                        } else {
                            mView.getNoticeFailure(resp != null ? resp.getMsg() :
                                    mView.getContext().getString(R.string.partner_request_error));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError:" + t.getMessage());
                        mView.getNoticeFailure(
                                mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }
}
