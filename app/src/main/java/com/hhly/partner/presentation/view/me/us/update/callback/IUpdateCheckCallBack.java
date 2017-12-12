package com.hhly.partner.presentation.view.me.us.update.callback;


import com.hhly.partner.data.net.protocol.update.CheckUpdateResp;

/**
 * 检查版本更新callback
 */
public interface IUpdateCheckCallBack {

    void hasUpdate(CheckUpdateResp resp,boolean isMustUpdate);

    void noUpdate(boolean isShowToast);

    void onCheckError(String msg);

}
