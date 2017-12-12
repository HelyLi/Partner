package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */

public class UpdatePayPwdReq extends BaseReq {

    /**
     * 密码
     */
    private String pwd;

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (pwd != null) {
            params.put("pwd", pwd);
        }
        return params;
    }
}
