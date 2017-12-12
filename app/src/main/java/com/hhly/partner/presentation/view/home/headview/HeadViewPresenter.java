package com.hhly.partner.presentation.view.home.headview;

/**
 * description :
 * Created by Flynn
 * 2017/4/15
 */

public class HeadViewPresenter implements HeadViewContract.Presenter {

    HeadViewContract.View mView;

    public HeadViewPresenter(HeadViewContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void getIndexData() {

    }
}
