package com.hhly.partner.presentation.view.me.us.update;

import android.content.Context;
import android.text.TextUtils;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.me.us.update.business.DownloadWorker;
import com.hhly.partner.presentation.view.me.us.update.business.UpdateWorker;
import com.hhly.partner.presentation.view.me.us.update.callback.UpdateCheckCallBack;
import com.orhanobut.logger.Logger;

/**
 * 版本更新辅助类
 * Created by dell on 2017/5/17.
 */
public final class VersionUpdateHelper {
    private static volatile VersionUpdateHelper updater;

    private VersionUpdateHelper() {

    }

    public static VersionUpdateHelper getInstance() {
        if (updater == null) {
            synchronized (VersionUpdateHelper.class) {
                if (updater == null) {
                    updater = new VersionUpdateHelper();
                }
            }
        }
        return updater;
    }

    /**
     * @param context     上下文
     * @param isShowToast true 弹出Toast已经最新版本  false 不弹出
     */
    public void checkUpdate(Context context, boolean isShowToast) {
        UpdateWorker checkWorker = UpdateWorker.getInstance();
        if (checkWorker.isRunning()) {
            Logger.d("is Running!");
            return;
        }
        UpdateCheckCallBack callBack = new UpdateCheckCallBack();
        checkWorker.setCheckCallBack(callBack);
        checkWorker.startCheck(context, isShowToast);
    }

    public void startDownload(String url, Context context) {
        if (TextUtils.isEmpty(url))
            return;
        if (DownloadWorker.getInstance().isRunning()) {
            ToastUtil.showShort(context, context.getString(R.string.partner_update_downloading));
            return;
        }
        DownloadWorker.getInstance().startDownLoad(url, context);
    }
}
