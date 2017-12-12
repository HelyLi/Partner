package com.hhly.partner.data.net.protocol.proxy;

import com.hhly.partner.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class IndexDataResp extends BaseResp {


    /**
     * data : {"agentProductNum":0,"agentsNum":0,"incomeMoney":0,"payNum":0,"partnerLevel":"A"}
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
         * agentProductNum : 0
         * agentsNum : 0
         * incomeMoney : 0
         * payNum : 0
         * partnerLevel : A
         */

        private int agentProductNum;
        private int agentsNum;
        private double incomeMoney;
        private int payNum;
        private String partnerLevel;

        public int getAgentProductNum() {
            return agentProductNum;
        }

        public void setAgentProductNum(int agentProductNum) {
            this.agentProductNum = agentProductNum;
        }

        public int getAgentsNum() {
            return agentsNum;
        }

        public void setAgentsNum(int agentsNum) {
            this.agentsNum = agentsNum;
        }

        public double getIncomeMoney() {
            return incomeMoney;
        }

        public void setIncomeMoney(double incomeMoney) {
            this.incomeMoney = incomeMoney;
        }

        public int getPayNum() {
            return payNum;
        }

        public void setPayNum(int payNum) {
            this.payNum = payNum;
        }

        public String getPartnerLevel() {
            return partnerLevel;
        }

        public void setPartnerLevel(String partnerLevel) {
            this.partnerLevel = partnerLevel;
        }
    }
}
