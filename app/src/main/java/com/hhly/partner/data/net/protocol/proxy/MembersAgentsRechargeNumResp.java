package com.hhly.partner.data.net.protocol.proxy;

import com.hhly.partner.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class MembersAgentsRechargeNumResp extends BaseResp {


    /**
     * data : {"agentsNum":0,"qualifiedNum":0,"memberNum":33}
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
         * agentsNum : 0
         * qualifiedNum : 0
         * memberNum : 33
         */

        private int agentsNum;
        private int qualifiedNum;
        private int memberNum;

        public int getAgentsNum() {
            return agentsNum;
        }

        public void setAgentsNum(int agentsNum) {
            this.agentsNum = agentsNum;
        }

        public int getQualifiedNum() {
            return qualifiedNum;
        }

        public void setQualifiedNum(int qualifiedNum) {
            this.qualifiedNum = qualifiedNum;
        }

        public int getMemberNum() {
            return memberNum;
        }

        public void setMemberNum(int memberNum) {
            this.memberNum = memberNum;
        }
    }
}
