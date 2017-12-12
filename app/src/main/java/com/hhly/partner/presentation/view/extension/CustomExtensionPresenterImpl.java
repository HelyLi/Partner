package com.hhly.partner.presentation.view.extension;

import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GetBannerListByUseridReq;
import com.hhly.partner.data.net.protocol.game.GetBannerListByUseridResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * 自定义推广页presenterImpl
 * Created by dell on 2017/5/4.
 */

public class CustomExtensionPresenterImpl extends BaseCustomExtensionPresenterImpl<ExtensionContract.CustomExtensionView>
        implements ExtensionContract.CustomExtensionPresenter {
    public CustomExtensionPresenterImpl(ExtensionContract.CustomExtensionView view) {
        super(view);
    }

    @Override
    public void getBannerListByUserId() {
        GetBannerListByUseridReq req = new GetBannerListByUseridReq();
        // TODO: 2017/5/6  android typeId=? 
        req.setTypeId(1);
        mGameApi.getBannerListByUserid(req.params())
                .compose(RxUtil.<GetBannerListByUseridResp>io_main())
                .compose(mView.<GetBannerListByUseridResp>bindToLife())
                .subscribe(new BaseSubscriber<GetBannerListByUseridResp>() {
                    @Override
                    public void onNext(GetBannerListByUseridResp resp) {
                        if (resp != null && resp.isOk() && resp.getData() != null && resp.getData().getData() != null) {
                            mView.getBannerListSuccess(resp.getData().getData());
                        } else {
                            mView.getBannerListFail(resp != null ? resp.getMsg() :
                                    mView.getContext().getString(R.string.partner_request_error));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError:" + t.getMessage());
                        mView.getBannerListFail(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }

}
