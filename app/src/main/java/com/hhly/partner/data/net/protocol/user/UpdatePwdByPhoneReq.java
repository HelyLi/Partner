package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class UpdatePwdByPhoneReq extends BaseReq {

    /**
     * 验证码默认666666
     */
    private String sendNo;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 手机号
     */
    private String mobile;

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (sendNo != null) {
            params.put("sendNo", sendNo);
        }
        if (pwd != null) {
            params.put("pwd", pwd);
        }
        if (mobile != null) {
            params.put("mobile", mobile);
        }
        return params;
    }
}
