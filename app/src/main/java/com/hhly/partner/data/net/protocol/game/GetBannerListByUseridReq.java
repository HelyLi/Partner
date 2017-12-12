package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class GetBannerListByUseridReq extends BaseReq {

    /**
     * 平台类型 H5 1  其余平台以后再加
     */
    private Integer typeId;

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (typeId != null) {
            params.put("typeId", String.valueOf(typeId));
        }
        return params;
    }
}
