package com.hhly.partner.presentation.view.me.us.update.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hhly.partner.presentation.utils.InstallUtil;
import com.hhly.partner.presentation.view.me.us.update.business.DownloadWorker;
import com.hhly.partner.presentation.view.me.us.update.callback.IDownloadCallBack;

import java.io.File;

/**
 * 接受下载完成广播
 * Created by dell on 2017/5/18.
 */

public class DownloadCompleteReceiver extends BroadcastReceiver {
    private DownloadManager mDownloadManager;
    private IDownloadCallBack mDownloadCallBack;

    public DownloadCompleteReceiver(Context context, IDownloadCallBack callBack) {
        mDownloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        mDownloadCallBack = callBack;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        switch (intent.getAction()) {
            case DownloadManager.ACTION_DOWNLOAD_COMPLETE:
                if (DownloadWorker.getInstance().getDownloadId() == downId && downId != -1) {
//                    Uri downIdUri = TelephonyUtil.getApkFilePathUri(mDownloadManager.getUriForDownloadedFile(downId),
//                            downId, mDownloadManager);
                    if (mDownloadCallBack != null) {
                        mDownloadCallBack.onDownloadComplete();
                    }
                    File file = InstallUtil.getApkFile(downId, mDownloadManager);
                    if (file != null) {
                        InstallUtil.installApk(context, file);
                    }
                }
                break;

            default:
                break;
        }
    }

}
