package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class AddBannerReq extends BaseReq {

    /**
     * 平台类型 H5 1  其余平台以后再加
     */
    private Integer typeId;
    /**
     * 游戏id 单个游戏id - 1  多个游戏id 1,2,3
     */
    private String gameId;

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (typeId != null) {
            params.put("typeId", String.valueOf(typeId));
        }
        if (gameId != null) {
            params.put("gameId", gameId);
        }
        return params;
    }
}
