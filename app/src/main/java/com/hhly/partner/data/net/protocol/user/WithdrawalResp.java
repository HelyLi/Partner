package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class WithdrawalResp extends BaseResp {


    /**
     * data : {"pwd":"5690DDDFA28AE085D23518A035707282","orderNo":"hhly_app1494301426155","data":1,"userid":"hb2344","withdrawalPrice":"200"}
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
         * pwd : 5690DDDFA28AE085D23518A035707282
         * orderNo : hhly_app1494301426155
         * data : 1
         * userid : hb2344
         * withdrawalPrice : 200
         */

        private String pwd;
        private String orderNo;
        private int data;
        private String userid;
        private String withdrawalPrice;

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getWithdrawalPrice() {
            return withdrawalPrice;
        }

        public void setWithdrawalPrice(String withdrawalPrice) {
            this.withdrawalPrice = withdrawalPrice;
        }
    }
}
