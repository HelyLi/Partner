package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */

public class UpdatePasswordReq extends BaseReq {

    /**
     * 原密码
     */
    private String pwd;
    /**
     * 新密码
     */
    private String newPwd;
    /**
     * 验证码（测试环境默认666666，其余不通过）
     */
    private String sendNo;
    /**
     * 手机号
     */
    private String mobile;

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
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
        if (newPwd != null) {
            params.put("newPwd", newPwd);
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
