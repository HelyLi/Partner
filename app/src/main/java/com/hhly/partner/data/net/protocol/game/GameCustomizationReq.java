package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class GameCustomizationReq extends BaseReq {

    /**
     * 类型 独家11 推荐12 单机13 网游14 热门15
     */
    private Integer modeId;
    /**
     * 游戏id
     */
    private Integer gameId;

    private Integer promotionPosition;

    public void setModeId(Integer modeId) {
        this.modeId = modeId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public void setPromotionPosition(Integer promotionPosition) {
        this.promotionPosition = promotionPosition;
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
        if (promotionPosition != null) {
            params.put("promotionPosition", String.valueOf(promotionPosition));
        }
        return params;
    }
}
