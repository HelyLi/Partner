package com.hhly.partner.presentation.view.me.us.update.receiver;

import android.app.DownloadManager;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;

import com.hhly.partner.presentation.view.me.us.update.business.DownloadWorker;
import com.hhly.partner.presentation.view.me.us.update.callback.IDownloadCallBack;
import com.orhanobut.logger.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 更新下载进度监听
 * Created by dell on 2017/5/18.
 */

public class DownloadProgressObserver extends ContentObserver {
    private DownloadManager mDownloadManager;
    private IDownloadCallBack mCallBack;
    private ScheduledExecutorService mScheduledExecutorService;

    public DownloadProgressObserver(Context context, IDownloadCallBack callBack) {
        super(null);
        mDownloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        mCallBack = callBack;
        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void onChange(boolean selfChange) {
        Logger.d("onChange:" + selfChange);
        mScheduledExecutorService.scheduleAtFixedRate(mDownloadProgressRunnable, 0, 1, TimeUnit.SECONDS);
    }

    private Runnable mDownloadProgressRunnable = new Runnable() {
        @Override
        public void run() {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(DownloadWorker.getInstance().getDownloadId());
            Cursor cursor = null;
            try {
                cursor = mDownloadManager.query(query);
                if (cursor != null && cursor.moveToFirst()) {
                    final int totalColumn = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
                    final int currentColumn = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
                    int totalSize = cursor.getInt(totalColumn);
                    int currentSize = cursor.getInt(currentColumn);
                    float percent = (float) currentSize / (float) totalSize;
                    int progress = Math.round(percent * 100);
                    boolean isCompleted = progress == 100;
                    if (mCallBack != null) {
                        mCallBack.onDownloading(currentSize, totalSize, isCompleted);
                    }
                    if (isCompleted && !mScheduledExecutorService.isShutdown()) {
                        mScheduledExecutorService.shutdown();
                    }
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    };
}