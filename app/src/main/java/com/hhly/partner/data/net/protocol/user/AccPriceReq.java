package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class AccPriceReq extends BaseReq {

    /**
     * 开始时间  格式: yyyy-MM-dd
     */
    private String start;
    /**
     * 结束时间  格式: yyyy-MM-dd
     */
    private String end;

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (start != null) {
            params.put("start", start);
        }
        if (end != null) {
            params.put("end", end);
        }
        return params;
    }

}
