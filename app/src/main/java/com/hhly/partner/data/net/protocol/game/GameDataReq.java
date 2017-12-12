package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class GameDataReq extends BaseReq {

    /**
     * 游戏类型 ---0 查询全部，8001 查询某一类型下的游戏
     */
    private Integer gameType;
    /**
     * 1.PC 2.安卓 3ios 4.h5  5.其他
     */
    private Integer platType = 2;

    public void setGameType(Integer gameType) {
        this.gameType = gameType;
    }

    public void setPlatType(Integer platType) {
        this.platType = platType;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();

        if (gameType != null) {
            params.put("gameType", String.valueOf(gameType));
        }
        if (platType != null) {
            params.put("platType", String.valueOf(platType));
        }

        return params;
    }
}
