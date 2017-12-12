package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/26
 */

public class UpdateCommissionTypeReq extends BaseReq {

    /**
     * 佣金计算方式   格式：1_3_3767,2_2_3769,3_1_3771
     */
    private String commissionChoseInfo;

    public void setCommissionChoseInfo(String commissionChoseInfo) {
        this.commissionChoseInfo = commissionChoseInfo;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (commissionChoseInfo != null) {
            params.put("commissionChoseInfo", commissionChoseInfo);
        }
        return params;
    }
}
