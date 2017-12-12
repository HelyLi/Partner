package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class AccPriceInfoReq extends BaseReq {

    /**
     * 查询日期 yyyy-MM-dd
     */
    private String start;
    /**
     * 1代理 2直属会员
     */
    private Integer type;

    public void setStart(String start) {
        this.start = start;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (start != null) {
            params.put("start", start);
        }
        if (type != null) {
            params.put("type", String.valueOf(type));
        }
        return params;
    }
}
