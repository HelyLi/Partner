package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class GameDataInfoReq extends BaseReq {

    /**
     * 游戏ID
     */
    private Integer gameId;

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (gameId != null) {
            params.put("gameId", String.valueOf(gameId));
        }

        return params;
    }
}
