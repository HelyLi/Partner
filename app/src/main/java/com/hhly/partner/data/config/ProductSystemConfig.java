package com.hhly.partner.data.config;

import android.content.Context;

import com.hhly.partner.data.net.ApiType;


/**
 * 正式环境配置
 */

class ProductSystemConfig extends AbsSystemConfig {

    ProductSystemConfig(Context context) {
        super(context);
    }

    @Override
    public String getApiBaseUrl() {
        return "http://m.game.13322.com/web/core/";
    }

    @Override
    public String getFileBaseUrl() {
        return "http://file.13322.com/upload/";
    }

    @Override
    public String getImageCachePath() {
        return null;
    }

    @Override
    public boolean isTest() {
        return false;
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
