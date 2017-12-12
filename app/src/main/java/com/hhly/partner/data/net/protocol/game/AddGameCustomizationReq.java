package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by dell on 2017/5/4.
 */

public class AddGameCustomizationReq extends BaseReq {
    private String modelId;
    private String gameId;
    private String promotionPosition;

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (modelId != null) {
            params.put("modeId", modelId);
        }
        if (gameId != null) {
            params.put("gameId", gameId);
        }
        if (promotionPosition != null) {
            params.put("promotionPosition", promotionPosition);
        }
        return params;
    }
}
