package com.hhly.partner.presentation.view;

/**
 * Created by Simon on 2016/11/18.
 */

public interface BaseLoadingView {

    void showMsg(int resId);
    void showMsg(String msg);

    void showLoading();
    void showLoading(int resId);
    void showLoading(String msg);

    void dismissLoading();

}
