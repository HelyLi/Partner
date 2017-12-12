package com.hhly.partner.data.net.protocol.proxy;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class AppMemberNumByTimeAreaReq extends BaseProxyReq {

    /**
     * 传query_date=7、30、90区分
     */
    private Integer queryDate;

    public void setQueryDate(Integer queryDate) {
        this.queryDate = queryDate;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (queryDate != null) {
            params.put("query_date", String.valueOf(queryDate));
        }
        return params;
    }
}
