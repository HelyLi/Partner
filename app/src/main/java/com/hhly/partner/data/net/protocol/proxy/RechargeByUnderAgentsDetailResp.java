package com.hhly.partner.data.net.protocol.proxy;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class RechargeByUnderAgentsDetailResp extends BaseResp {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userid : pc81
         * realName : pc81
         * rechargeAmount : 190.35
         * rechargeNum : 12
         * registerDate : 1492589231000
         */

        private String userid;
        private String realName;
        private double rechargeAmount;
        private int rechargeNum;
        private long registerDate;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public double getRechargeAmount() {
            return rechargeAmount;
        }

        public void setRechargeAmount(double rechargeAmount) {
            this.rechargeAmount = rechargeAmount;
        }

        public int getRechargeNum() {
            return rechargeNum;
        }

        public void setRechargeNum(int rechargeNum) {
            this.rechargeNum = rechargeNum;
        }

        public long getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(long registerDate) {
            this.registerDate = registerDate;
        }
    }
}
