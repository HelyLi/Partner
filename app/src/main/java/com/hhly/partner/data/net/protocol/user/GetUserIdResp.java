package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/5/3
 */

public class GetUserIdResp extends BaseResp {


    /**
     * userid : hb1320
     */

    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
