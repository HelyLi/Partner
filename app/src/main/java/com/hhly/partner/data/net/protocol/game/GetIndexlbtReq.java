package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseReq;

import java.util.Map;

/**
 * Created by dell on 2017/4/24.
 */

public class GetIndexlbtReq extends BaseReq{
    private Integer pageNo;
    private Integer terminal;

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setTerminal(Integer terminal) {
        this.terminal = terminal;
    }

    @Override
    public Map<String, String> params() {
        Map<String, String> params = super.params();
        if (pageNo!=null){
            params.put("pageNo",String.valueOf(pageNo));
        }
        if (terminal!=null){
            params.put("terminal",String.valueOf(terminal));
        }
        return params;
    }
}
