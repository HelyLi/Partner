package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description
 * Created by dell on 2017/5/3.
 */

public class GetGameCustomizationReq extends BaseReq {
    private Integer modeId;

    public int getModeId() {
        return modeId;
    }

    public void setModeId(int modeId) {
        this.modeId = modeId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (modeId != null) {
            params.put("modeId", String.valueOf(modeId));
        }
        return params;
    }
}
