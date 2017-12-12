package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */

public class WithdrawalReq extends BaseReq {

    private Double withdrawalPrice;
    private String pwd;

    public void setWithdrawalPrice(double withdrawalPrice) {
        this.withdrawalPrice = withdrawalPrice;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (withdrawalPrice != null) {
            params.put("withdrawalPrice", String.valueOf(withdrawalPrice));
        }
        if (pwd != null) {
            params.put("pwd", pwd);
        }

        return params;
    }
}
