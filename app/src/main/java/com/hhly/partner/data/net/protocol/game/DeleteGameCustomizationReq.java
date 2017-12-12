package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description
 * Created by dell on 2017/5/3.
 */

public class DeleteGameCustomizationReq extends BaseReq {
    private Integer modeId;
    private Integer gameId;

    public int getModeId() {
        return modeId;
    }

    public void setModeId(int modeId) {
        this.modeId = modeId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (modeId != null) {
            params.put("modeId", String.valueOf(modeId));
        }
        if (gameId != null) {
            params.put("gameId", String.valueOf(gameId));
        }
        return params;
    }
}
