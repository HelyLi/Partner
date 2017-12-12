package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class UpdatePasswordResp extends BaseResp {

    /**
     * data : {"newPwd":"6846860684F05029ABCCC09A53CD66F1","pwd":"C5FE25896E49DDFE996DB7508CF00534","data":0,"userid":"hb1562","mobile":"18820239427"}
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
         * newPwd : 6846860684F05029ABCCC09A53CD66F1
         * pwd : C5FE25896E49DDFE996DB7508CF00534
         * data : 0
         * userid : hb1562
         * mobile : 18820239427
         */

        private String newPwd;
        private String pwd;
        private int data;
        private String userid;
        private String mobile;

        public String getNewPwd() {
            return newPwd;
        }

        public void setNewPwd(String newPwd) {
            this.newPwd = newPwd;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
