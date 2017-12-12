package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */

public class IdCardReq extends BaseReq {
    /**
     * 号码
     */
    private String cardNo;
    /**
     * 姓名
     */
    private String cardName;

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (cardNo != null) {
            params.put("cardNo", cardNo);
        }
        if (cardName != null) {
            params.put("cardName", cardName);
        }
        return params;
    }
}
