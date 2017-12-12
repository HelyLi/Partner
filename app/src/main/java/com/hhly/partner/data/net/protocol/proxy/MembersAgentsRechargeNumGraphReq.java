package com.hhly.partner.data.net.protocol.proxy;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class MembersAgentsRechargeNumGraphReq extends BaseProxyReq {
    /**
     * 直属会员flag=0,代理=1 合格会员2
     */
    private Integer flag;
    /**
     * query_date=7,30,90
     */
    private Integer queryDate;

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public void setQueryDate(Integer queryDate) {
        this.queryDate = queryDate;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (flag != null) {
            params.put("flag", String.valueOf(flag));
        }
        if (queryDate != null) {
            params.put("query_date", String.valueOf(queryDate));
        }
        return params;
    }
}
