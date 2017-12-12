package com.hhly.partner.data.config;

/**
 * Created by dell on 2017/4/11.
 */

interface ISystemConfig {
    String getApiBaseUrl();

    String getFileBaseUrl();

    String getPath();

    String getPathName();

    String getImageCachePath();

    boolean isTest();

    String getHost(int hostType);
}
