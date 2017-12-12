package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class ResetPayPwdReq extends BaseReq {

    /**
     * 密码
     */
    private String pwd;
    /**
     * 验证码（测试环境默认66666）
     */
    private String sendNo;
    /**
     * 手机号码
     */
    private String mobile;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (pwd != null) {
            params.put("pwd", pwd);
        }
        if (sendNo != null) {
            params.put("sendNo", sendNo);
        }
        if (mobile != null) {
            params.put("mobile", mobile);
        }
        return params;
    }
}
