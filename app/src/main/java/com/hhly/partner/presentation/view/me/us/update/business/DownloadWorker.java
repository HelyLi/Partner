package com.hhly.partner.presentation.view.me.us.update.business;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

/**
 * 版本更新下载器
 * Created by dell on 2017/5/17.
 */
public class DownloadWorker extends UnifiedWorker {
    private static volatile DownloadWorker mDownloadWorker;
    private long mDownloadId;

    private DownloadWorker() {

    }

    public static DownloadWorker getInstance() {
        if (mDownloadWorker == null) {
            synchronized (DownloadWorker.class) {
                if (mDownloadWorker == null) {
                    mDownloadWorker = new DownloadWorker();
                }
            }
        }
        return mDownloadWorker;
    }


    public void startDownLoad(String url, Context context) {
        setRunning(true);
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        /**设置用于下载时的网络状态*/
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        /**设置通知栏是否可见*/
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        /**设置漫游状态下是否可以下载*/
        request.setAllowedOverRoaming(false);
        /**如果我们希望下载的文件可以被系统的Downloads应用扫描到并管理，
         我们需要调用Request对象的setVisibleInDownloadsUi方法，传递参数true.*/
        request.setVisibleInDownloadsUi(true);
        String apkName = url.substring(url.lastIndexOf("/"));
        /**设置文件保存路径*/
        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, apkName);
        /**将下载请求放入队列， return下载任务的ID*/
        mDownloadId = downloadManager.enqueue(request);
    }

    public long getDownloadId() {
        return mDownloadId;
    }

}
