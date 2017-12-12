package com.hhly.partner.presentation.view.me.us.update.callback;

/**
 * 版本更新下载callback
 * Created by dell on 2017/5/17.
 */

public interface IDownloadCallBack {
    void onDownloadError(String msg);

    void onDownloadComplete();

    void onDownloading(long currentBytes, long totalBytes,boolean isCompleted);
}
