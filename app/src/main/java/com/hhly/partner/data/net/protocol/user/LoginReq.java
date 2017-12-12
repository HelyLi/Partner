package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class LoginReq extends BaseReq {
    /**
     * 帐号
     */
    private String account;
    /**
     * 密码
     */
    private String password;

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (account != null) {
            params.put("account", account);
        }
        if (password != null) {
            params.put("password", password);
        }
        return params;
    }
}
