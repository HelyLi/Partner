package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class UpdateBankInfoReq extends BaseReq {

    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 银行卡号
     */
    private String bankNo;
    /**
     * 名称
     */
    private String name;
    /**
     * 验证码（测试环境默认666666，其余不通过）
     */
    private String sendNo;
    /**
     * 手机号
     */
    private String mobile;

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public void setName(String name) {
        this.name = name;
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

        if (bankName != null) {
            params.put("bankName", bankName);
        }
        if (bankNo != null) {
            params.put("bankNo", bankNo);
        }
        if (name != null) {
            params.put("name", name);
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
