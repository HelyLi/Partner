package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class CheckPartnerNoReq extends BaseReq {
    /**
     * 推广编码
     */
    private String partnerNo;

    public void setPartnerNo(String partnerNo) {
        this.partnerNo = partnerNo;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (partnerNo != null) {
            params.put("partnerNo", partnerNo);
        }
        return params;
    }
}
