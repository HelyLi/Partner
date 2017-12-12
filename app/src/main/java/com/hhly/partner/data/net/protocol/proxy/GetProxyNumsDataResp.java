package com.hhly.partner.data.net.protocol.proxy;

import com.hhly.partner.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class GetProxyNumsDataResp extends BaseResp {

    /**
     * data : {"memberUserids":27,"underAgentsNum":24,"uunderAgentsNum":9}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * memberUserids : 27
         * underAgentsNum : 24
         * uunderAgentsNum : 9
         */

        private int memberUserids;
        private int underAgentsNum;
        private int uunderAgentsNum;

        public int getMemberUserids() {
            return memberUserids;
        }

        public void setMemberUserids(int memberUserids) {
            this.memberUserids = memberUserids;
        }

        public int getUnderAgentsNum() {
            return underAgentsNum;
        }

        public void setUnderAgentsNum(int underAgentsNum) {
            this.underAgentsNum = underAgentsNum;
        }

        public int getUunderAgentsNum() {
            return uunderAgentsNum;
        }

        public void setUunderAgentsNum(int uunderAgentsNum) {
            this.uunderAgentsNum = uunderAgentsNum;
        }
    }
}
