package com.hhly.partner.data.config;

import android.content.Context;
import android.text.TextUtils;

import com.hhly.partner.data.net.ApiType;


/**
 * Created by Simon on 2016/11/18.
 */

public class SystemConfig implements ISystemConfig {

    private AbsSystemConfig mSystemConfig;

    private SystemConfig() {}

    private static SystemConfig sInstance = null;

    public static SystemConfig get() {
        if (sInstance == null) {
            synchronized (SystemConfig.class) {
                if (sInstance == null) {
                    sInstance = new SystemConfig();
                }
            }
        }
        return sInstance;
    }
    
    public void initialize(Context context, String environment) {
        if (TextUtils.isEmpty(environment)) {
            mSystemConfig = new ProductSystemConfig(context);
        } else if (environment.equalsIgnoreCase("product")) {
            mSystemConfig = new ProductSystemConfig(context);
        } else if (environment.equalsIgnoreCase("test")) {
            mSystemConfig = new TestSystemConfig(context);
        } else {
            mSystemConfig = new ProductSystemConfig(context);
        }
    }

    @Override
    public String getApiBaseUrl() {
        return mSystemConfig.getApiBaseUrl();
    }

    @Override
    public String getFileBaseUrl() {
        return mSystemConfig.getFileBaseUrl();
    }

    @Override
    public String getPath() {
        return mSystemConfig.getPath();
    }

    @Override
    public String getPathName() {
        return mSystemConfig.getPathName();
    }

    @Override
    public String getImageCachePath() {
        return mSystemConfig.getImageCachePath();
    }

    @Override
    public boolean isTest() {
        return mSystemConfig.isTest();
    }

    @Override
    public String getHost(@ApiType.ApiTypeChecker int apiType) {
        String host;
        switch (apiType) {
            case ApiType.BANNER_API:
            case ApiType.GAME_API:
            case ApiType.GOODS_API:
            case ApiType.UPDATE_API:
            case ApiType.USER_API:
            case ApiType.PROXY_API:
                host = getApiBaseUrl();
                break;
            case ApiType.FILE_API:
                host = getFileBaseUrl();
                break;
            default:
                host = "";
                break;
        }
        return host;
    }

}
