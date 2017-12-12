package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/26
 */

public class AddSpreadReq extends BaseReq {
    /**
     * 推广出去的用户id
     */
    private String spreadUserId;
    /**
     * 游戏id
     */
    private Long gameId;
    /**
     * 类型 1平台 2游戏
     */
    private Integer type;
    /**
     * 注册成功的用户id
     */
    private String regUserId;

    public void setSpreadUserId(String spreadUserId) {
        this.spreadUserId = spreadUserId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setRegUserId(String regUserId) {
        this.regUserId = regUserId;
    }


    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (spreadUserId != null) {
            params.put("spreadUserId", spreadUserId);
        }
        if (gameId != null) {
            params.put("gameId", String.valueOf(gameId));
        }
        if (type != null) {
            params.put("type", String.valueOf(type));
        }
        if (regUserId != null) {
            params.put("regUserId", regUserId);
        }
        return params;
    }
}
