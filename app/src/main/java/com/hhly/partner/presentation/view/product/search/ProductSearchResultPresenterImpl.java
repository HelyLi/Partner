package com.hhly.partner.presentation.view.product.search;

import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GameDataByNameReq;
import com.hhly.partner.data.net.protocol.game.GameDataByNameResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.hhly.partner.presentation.utils.SearchRecordPrefsUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Flowable;

/**
 * 产品搜索结果presenterImpl
 * Created by dell on 2017/4/26.
 */

public class ProductSearchResultPresenterImpl extends BaseSearchPresenterImpl<ProductSearchContract.ProductSearchResultView>
        implements ProductSearchContract.ProductSearchResultPresenter {

    public ProductSearchResultPresenterImpl(ProductSearchContract.ProductSearchResultView view) {
        super(view);
    }

    @Override
    public void searchProduct(String searchKey) {
        GameDataByNameReq req = new GameDataByNameReq();
        req.setGameName(searchKey);
        mGameApi.getGameDataByName(req.params())
                .compose(RxUtil.<GameDataByNameResp>io_main())
                .compose(mView.<GameDataByNameResp>bindToLife())
                .subscribe(new BaseSubscriber<GameDataByNameResp>() {
                    @Override
                    public void onNext(GameDataByNameResp resp) {
                        if (resp != null && resp.getData() != null && resp.getData().getData() != null) {
                            mView.searchProductSuccess(resp.getData().getData());
                        } else {
                            mView.searchProductFailure(resp != null ? resp.getMsg() : mView.getContext().getString(R.string.partner_request_error));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError:" + t.getMessage());
                        mView.searchProductFailure(mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }

    @Override
    public void recordSearchBehavior(String gameName, int gameId) {
        String recordContent = SearchRecordPrefsUtil.compoundRecordContent(gameName, gameId);
        Flowable.just(recordContent).compose(RxUtil.<String>io_io())
                .subscribe(new BaseSubscriber<String>() {
                    @Override
                    public void onNext(String recordContent) {
                        List<String> recordList = SearchRecordPrefsUtil.getInstance()
                                .getRecordInfoList(mView.getContext());
                        if (!recordList.contains(recordContent)) {
                            recordList.add(0, recordContent);
                            SearchRecordPrefsUtil.getInstance().putRecordInfoList(mView.getContext(),
                                    recordList);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError:" + t.getMessage());
                    }
                });
    }

}
