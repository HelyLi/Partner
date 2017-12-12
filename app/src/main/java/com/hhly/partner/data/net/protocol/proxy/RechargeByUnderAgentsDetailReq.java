package com.hhly.partner.data.net.protocol.proxy;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class RechargeByUnderAgentsDetailReq extends BaseProxyReq {
    /**
     * flag="DAY" "WEEK" "MONTH" 点哪个传哪个  DAY 的没数据，用MONTH  默认全部  传flag="ALL"  不区分大小写
     */
    private String flag;

    private Integer pageNo;

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (flag != null) {
            params.put("flag", flag);
        }
        if (pageNo != null) {
            params.put("pageNo", String.valueOf(pageNo));
        }
        return params;
    }
}
