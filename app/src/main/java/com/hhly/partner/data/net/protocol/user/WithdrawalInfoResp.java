package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */

public class WithdrawalInfoResp extends BaseResp {

    /**
     * data : {"data":[{"WITHDRAWAL_PRICE":10000,"APPLICATION_TIME":"2017-04-11 11:22:34"},{"WITHDRAWAL_PRICE":10000,"APPLICATION_TIME":"2017-04-11 11:22:36"},{"WITHDRAWAL_PRICE":0,"APPLICATION_TIME":"2017-04-11 11:22:42"},{"WITHDRAWAL_PRICE":10000,"APPLICATION_TIME":"2017-04-10 09:50:54"},{"WITHDRAWAL_PRICE":null,"APPLICATION_TIME":"2017-04-10 10:16:34"},{"WITHDRAWAL_PRICE":10000,"APPLICATION_TIME":"2017-04-11 11:22:22"},{"WITHDRAWAL_PRICE":null,"APPLICATION_TIME":"2017-04-11 19:42:42"}],"userInfo":[{"PHONE":"15888888888","APPLICATION_TIME":"2017-04-21 15:26:03","AMOUNT":0}]}
     */

    private DataBeanX data;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        private List<DataBean> data;
        private List<UserInfoBean> userInfo;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public List<UserInfoBean> getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(List<UserInfoBean> userInfo) {
            this.userInfo = userInfo;
        }

        public static class DataBean {
            /**
             * WITHDRAWAL_PRICE : 10000
             * APPLICATION_TIME : 2017-04-11 11:22:34
             */

            private double WITHDRAWAL_PRICE;
            private String APPLICATION_TIME;

            public double getWITHDRAWAL_PRICE() {
                return WITHDRAWAL_PRICE;
            }

            public void setWITHDRAWAL_PRICE(double WITHDRAWAL_PRICE) {
                this.WITHDRAWAL_PRICE = WITHDRAWAL_PRICE;
            }

            public String getAPPLICATION_TIME() {
                return APPLICATION_TIME;
            }

            public void setAPPLICATION_TIME(String APPLICATION_TIME) {
                this.APPLICATION_TIME = APPLICATION_TIME;
            }
        }

        public static class UserInfoBean {
            /**
             * PHONE : 15888888888
             * APPLICATION_TIME : 2017-04-21 15:26:03
             * AMOUNT : 0
             */

            private String PHONE;
            private String APPLICATION_TIME;
            private double AMOUNT;

            public String getPHONE() {
                return PHONE;
            }

            public void setPHONE(String PHONE) {
                this.PHONE = PHONE;
            }

            public String getAPPLICATION_TIME() {
                return APPLICATION_TIME;
            }

            public void setAPPLICATION_TIME(String APPLICATION_TIME) {
                this.APPLICATION_TIME = APPLICATION_TIME;
            }

            public double getAMOUNT() {
                return AMOUNT;
            }

            public void setAMOUNT(double AMOUNT) {
                this.AMOUNT = AMOUNT;
            }
        }
    }
}
