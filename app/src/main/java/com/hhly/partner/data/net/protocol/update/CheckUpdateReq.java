package com.hhly.partner.data.net.protocol.update;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * 检测版本更新req
 * Created by dell on 2017/5/17.
 */

public class CheckUpdateReq extends BaseReq {
    private String appId;
    private String versionCode;

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (appId != null) {
            params.put("appId", appId);
        }
        if (versionCode != null) {
            params.put("versionCode", versionCode);
        }
        return params;
    }
}
