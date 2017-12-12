package com.hhly.partner.presentation.view.me.us.update.business;


import android.content.Context;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UpdateApi;
import com.hhly.partner.data.net.protocol.update.CheckUpdateReq;
import com.hhly.partner.data.net.protocol.update.CheckUpdateResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RxUtil;
import com.hhly.partner.presentation.utils.TelephonyUtil;
import com.hhly.partner.presentation.view.me.us.update.callback.IUpdateCheckCallBack;
import com.orhanobut.logger.Logger;


/**
 * 检测版本更新
 * Created by dell on 2017/5/17.
 */
public class UpdateWorker extends UnifiedWorker {
    private static volatile UpdateWorker mUpdateWorker;
    private IUpdateCheckCallBack mCheckCallBack;
    private UpdateApi mUpdateApi;

    private UpdateWorker() {
        mUpdateApi = RetrofitManager.getInstance(ApiType.UPDATE_API).getUpdateApi();
    }

    public static UpdateWorker getInstance() {
        if (mUpdateWorker == null) {
            synchronized (UpdateWorker.class) {
                if (mUpdateWorker == null) {
                    mUpdateWorker = new UpdateWorker();
                }
            }
        }
        return mUpdateWorker;
    }

    public void setCheckCallBack(IUpdateCheckCallBack checkCallBack) {
        mCheckCallBack = checkCallBack;
    }

    public void startCheck(Context context, final boolean isShowToast) {
        setRunning(true);
        final Context appContext = context.getApplicationContext();
        CheckUpdateReq req = new CheckUpdateReq();
        req.setAppId(TelephonyUtil.getAppId());
        req.setVersionCode(String.valueOf(TelephonyUtil.getAppVersionCode(context)));
        mUpdateApi.getUpdateInfo(req.params())
                .compose(RxUtil.<CheckUpdateResp>io_main())
                .subscribe(new BaseSubscriber<CheckUpdateResp>() {
                    @Override
                    public void onNext(CheckUpdateResp resp) {
                        if (resp != null && resp.isOk() && resp.getData() != null) {
                            int updateType = resp.getData().getUpdateType();
                            if (updateType == 1) {//强制更新
                                sendHasUpdate(resp, true);
                            } else if (updateType == 0) {//非强制更新
                                sendHasUpdate(resp, false);
                            } else {
                                sendNoUpdate(isShowToast);
                            }
                        } else {
                            sendOnErrorMsg(appContext.getString(R.string.partner_request_error));
                        }
                        release();
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError：" + t.getMessage());
                        sendOnErrorMsg(appContext.getString(R.string.partner_request_error));
                        release();
                    }
                });
    }

    private void sendHasUpdate(CheckUpdateResp resp, boolean isMustUpdate) {
        if (mCheckCallBack == null) return;
        mCheckCallBack.hasUpdate(resp, isMustUpdate);
    }

    private void sendNoUpdate(boolean isShowToast) {
        if (mCheckCallBack == null) return;
        mCheckCallBack.noUpdate(isShowToast);
    }

    private void sendOnErrorMsg(String msg) {
        if (mCheckCallBack == null) return;
        mCheckCallBack.onCheckError(msg);
    }

    private void release() {
        this.mCheckCallBack = null;
        setRunning(false);
    }
}
