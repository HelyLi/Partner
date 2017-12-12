package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */

public class NoticeReq extends BaseReq {
    /**
     * 3合作活动  4合作通知
     */
    private Integer type;

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (type != null) {
            params.put("type", String.valueOf(type));
        }
        return params;
    }
}
