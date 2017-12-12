package com.hhly.partner.data.config;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by Simon on 2016/11/18.
 */

abstract class AbsSystemConfig implements ISystemConfig {

    private final Context mContext;

    AbsSystemConfig(Context context) {
        this.mContext = context;
    }

    public abstract String getApiBaseUrl();

    public String getPath() {
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

        if (sdCardExist) {
            return Environment.getExternalStorageDirectory().toString()
                    + File.separator + getPathName();
        } else {
            return mContext.getFilesDir().getAbsolutePath()
                    + File.separator + getPathName();
        }
    }

    public String getPathName() {
        return mContext.getPackageName();
    }

    public abstract boolean isTest();
}
