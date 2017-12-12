package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class GetBannerListReq extends BaseReq {
    /**
     * 页数
     */
    private Integer page;

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (page != null) {
            params.put("page", String.valueOf(page));
        }
        return params;
    }
}
