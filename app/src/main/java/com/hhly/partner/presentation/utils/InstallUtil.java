package com.hhly.partner.presentation.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * 安装util
 * Created by dell on 2017/5/22.
 */

public class InstallUtil {
    private InstallUtil() {
        throw new RuntimeException("can't instance!");
    }

    /**
     * 安装APK
     *
     * @param context context
     * @param apkFile 安装包的路径
     */
    public static void installApk(Context context, File apkFile) {
        Logger.d("apkPath:" + apkFile.getAbsolutePath());
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //判读版本是否在7.0以上
        //            Uri apkUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileProvider",
        //                    apkFile);
        //            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        //            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        //        } else {
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        //        }
        context.startActivity(intent);
    }

    /**
     * 转化contentUri为fileUri
     *
     * @param contentUri 包含content的Uri
     * @param downLoadId 下载方法返回系统为当前下载请求分配的一个唯一的ID
     * @param manager    系统的下载管理
     * @return fileUri
     */
    public static Uri getApkFilePathUri(Uri contentUri, long downLoadId, DownloadManager manager) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downLoadId);
        Cursor c = manager.query(query);
        try {
            if (c.moveToFirst()) {
                int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
                // 下载失败也会返回这个广播，所以要判断下是否真的下载成功
                if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
                    // 获取下载好的 apk 路径
                    String downloadFileLocalUri = c.getString(c.getColumnIndex(DownloadManager
                            .COLUMN_LOCAL_URI));
                    if (downloadFileLocalUri != null) {
                        File mFile = new File(Uri.parse(downloadFileLocalUri).getPath());
                        String uriString = mFile.getAbsolutePath();
                        contentUri = Uri.parse("file://" + uriString);
                    }
                }
            }
        } finally {
            c.close();
        }
        return contentUri;
    }

    /**
     * 获取下载的文件
     *
     * @param downLoadId 下载方法返回系统为当前下载请求分配的一个唯一的ID
     * @param manager    系统的下载管理
     * @return fileUri
     */
    public static File getApkFile(long downLoadId, DownloadManager manager) {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downLoadId);
        Cursor c = manager.query(query);
        File file = null;
        try {
            if (c.moveToFirst()) {
                int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
                // 下载失败也会返回这个广播，所以要判断下是否真的下载成功
                if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
                    // 获取下载好的 apk 路径
                    String downloadFileLocalUri = c.getString(c.getColumnIndex(DownloadManager
                            .COLUMN_LOCAL_URI));
                    if (downloadFileLocalUri != null) {
                        file = new File(Uri.parse(downloadFileLocalUri).getPath());
                    }
                }
            }
        } finally {
            c.close();
        }
        return file;
    }
}
