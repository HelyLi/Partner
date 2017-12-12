package com.hhly.partner.data.net.protocol.proxy;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class MyProxyDataReq extends BaseProxyReq {

    /**
     * 0收入；1注册时间；2代理人数  代理等级排序的PASS掉。下级代理都是同一级的。
     */
    private Integer orderby;
    private Integer pageNo;

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setOrderby(Integer orderby) {
        this.orderby = orderby;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (orderby != null) {
            params.put("orderby", String.valueOf(orderby));
        }
        if (pageNo != null) {
            params.put("pageNo", String.valueOf(pageNo));
        }
        return params;
    }
}
