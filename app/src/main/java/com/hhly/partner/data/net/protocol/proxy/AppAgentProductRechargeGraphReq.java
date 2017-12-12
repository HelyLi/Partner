package com.hhly.partner.data.net.protocol.proxy;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class AppAgentProductRechargeGraphReq extends BaseProxyReq {
    /**
     * 游戏ID
     */
    private String gameId;
    /**
     * 7、30、90区分
     */
    private Integer queryDate;

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setQueryDate(Integer queryDate) {
        this.queryDate = queryDate;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (gameId != null) {
            params.put("gameid", gameId);
        }
        if (queryDate != null){
            params.put("query_date", String.valueOf(queryDate));
        }
        return params;
    }
}
