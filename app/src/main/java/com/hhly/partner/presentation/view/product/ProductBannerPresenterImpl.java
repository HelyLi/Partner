package com.hhly.partner.presentation.view.product;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.GameApi;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.protocol.game.GetIndexlbtReq;
import com.hhly.partner.data.net.protocol.game.GetIndexlbtResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;

/**
 * Created by dell on 2017/4/24.
 */

public class ProductBannerPresenterImpl implements ProductContract.BannerPresenter {
    private ProductContract.BannerView mView;
    private GameApi mGameApi;

    public ProductBannerPresenterImpl(ProductContract.BannerView view) {
        mView = view;
        mGameApi = RetrofitManager.getInstance(ApiType.GAME_API).getGameApi();
    }

    @Override
    public void getBannerInfo() {
        GetIndexlbtReq getIndexlbtReq = new GetIndexlbtReq();
        getIndexlbtReq.setPageNo(1);
        getIndexlbtReq.setTerminal(2);
        mGameApi.getIndexlbt(getIndexlbtReq.params())
                .compose(RxUtil.<GetIndexlbtResp>io_main())
                .compose(mView.<GetIndexlbtResp>bindToLife())
                .subscribe(new BaseSubscriber<GetIndexlbtResp>() {
                    @Override
                    public void onNext(GetIndexlbtResp resp) {
                        if (resp != null && resp.getData() != null && resp.getData().getList() != null) {
                            mView.getBannerInfoSuccess(resp.getData().getList());
                        } else {
                            mView.getBannerInfoFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        mView.getBannerInfoFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }
}
