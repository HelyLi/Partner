package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class SmsCodeReq extends BaseReq {
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 1手机注册2绑定手机3修改登录密码4忘记密码 5重置密保手机6重置密保邮箱7设置二级密码8设置密保邮箱9验证手机10修改银行卡11资金密码
     */
    private Integer operateType;

    /**
     * 手机验证码
     */
    private String authCode;

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (phoneNumber != null) {
            params.put("phoneNumber", phoneNumber);
        }
        if (operateType != null) {
            params.put("operateType", String.valueOf(operateType));
        }
        if (authCode != null) {
            params.put("phoneCode", authCode);
        }
        return params;
    }
}
