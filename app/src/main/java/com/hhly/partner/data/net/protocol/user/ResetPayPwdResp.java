package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class ResetPayPwdResp extends BaseResp {

    /**
     * data : {"pwd":"5690DDDFA28AE085D23518A035707282","data":0,"userid":"hb1547","mobile":"15999999995"}
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
         * data : 0
         * userid : hb1547
         * mobile : 15999999995
         */

        private String pwd;
        private int data;
        private String userid;
        private String mobile;

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
