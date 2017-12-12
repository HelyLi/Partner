package com.hhly.partner.data.net.protocol.proxy;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class MyProxyDataResp extends BaseResp {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * userid : hb1481
         * realName : 介绍
         * partnerNo : 67145
         * partnerLevel : B
         * higherPartnerNo : 383545
         * phone : 15100000000
         * email : null
         * ip : null
         * ipArea : null
         * agentsNum : 0
         * rechargeAmount : 0
         * totalBetAmount : 0
         * totalLosingAmount : 0
         * totalGiftAmount : 0
         * registerDate : 1491961529000
         */

        private String userid;
        private String realName;
        private String partnerNo;
        private String partnerLevel;
        private String higherPartnerNo;
        private String phone;
        private Object email;
        private Object ip;
        private Object ipArea;
        private String agentsNum;
        private String rechargeAmount;
        private String totalBetAmount;
        private String totalLosingAmount;
        private String totalGiftAmount;
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

        public String getPartnerNo() {
            return partnerNo;
        }

        public void setPartnerNo(String partnerNo) {
            this.partnerNo = partnerNo;
        }

        public String getPartnerLevel() {
            return partnerLevel;
        }

        public void setPartnerLevel(String partnerLevel) {
            this.partnerLevel = partnerLevel;
        }

        public String getHigherPartnerNo() {
            return higherPartnerNo;
        }

        public void setHigherPartnerNo(String higherPartnerNo) {
            this.higherPartnerNo = higherPartnerNo;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Object getEmail() {
            return email;
        }

        public void setEmail(Object email) {
            this.email = email;
        }

        public Object getIp() {
            return ip;
        }

        public void setIp(Object ip) {
            this.ip = ip;
        }

        public Object getIpArea() {
            return ipArea;
        }

        public void setIpArea(Object ipArea) {
            this.ipArea = ipArea;
        }

        public String getAgentsNum() {
            return agentsNum;
        }

        public void setAgentsNum(String agentsNum) {
            this.agentsNum = agentsNum;
        }

        public String getRechargeAmount() {
            return rechargeAmount;
        }

        public void setRechargeAmount(String rechargeAmount) {
            this.rechargeAmount = rechargeAmount;
        }

        public String getTotalBetAmount() {
            return totalBetAmount;
        }

        public void setTotalBetAmount(String totalBetAmount) {
            this.totalBetAmount = totalBetAmount;
        }

        public String getTotalLosingAmount() {
            return totalLosingAmount;
        }

        public void setTotalLosingAmount(String totalLosingAmount) {
            this.totalLosingAmount = totalLosingAmount;
        }

        public String getTotalGiftAmount() {
            return totalGiftAmount;
        }

        public void setTotalGiftAmount(String totalGiftAmount) {
            this.totalGiftAmount = totalGiftAmount;
        }

        public long getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(long registerDate) {
            this.registerDate = registerDate;
        }
    }
}
