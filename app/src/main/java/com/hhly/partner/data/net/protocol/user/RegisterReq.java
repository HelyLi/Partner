package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class RegisterReq extends BaseReq {
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
    /**
     * 上级推广编码
     */
    private String spreadNo;
    /**
     * 选择的佣金模式信息
     */
    private String commissionChoseInfo;

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setSpreadNo(String spreadNo) {
        this.spreadNo = spreadNo;
    }

    public void setCommissionChoseInfo(String commissionChoseInfo) {
        this.commissionChoseInfo = commissionChoseInfo;
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
        if (spreadNo != null) {
            params.put("spreadNo", spreadNo);
        }
        if (commissionChoseInfo != null) {
            params.put("commissionChoseInfo", commissionChoseInfo);
        }
        return params;
    }
}
