package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class NoticeByIdReq extends BaseReq {
    /**
     * id
     */
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (id != null) {
            params.put("id", String.valueOf(id));
        }
        return params;
    }
}
