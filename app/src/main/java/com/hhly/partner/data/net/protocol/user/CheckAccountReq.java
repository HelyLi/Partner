package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class CheckAccountReq extends BaseReq {

    private String phone;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (phone != null) {
            params.put("phone", phone);
        }
        return params;
    }
}
