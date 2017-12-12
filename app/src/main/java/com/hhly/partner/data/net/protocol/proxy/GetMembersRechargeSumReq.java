package com.hhly.partner.data.net.protocol.proxy;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class GetMembersRechargeSumReq extends BaseProxyReq {

    private Integer pageNo;

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (pageNo != null) {
            params.put("pageNo", String.valueOf(pageNo));
        }
        return params;
    }
}
