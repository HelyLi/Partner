package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class GameDataByNameReq extends BaseReq {
    /**
     * 游戏名称
     */
    private String gameName;

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (gameName != null) {
            params.put("gameName", gameName);
        }
        return params;
    }
}
