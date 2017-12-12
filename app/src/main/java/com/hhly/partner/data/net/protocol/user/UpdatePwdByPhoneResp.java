package com.hhly.partner.data.net.protocol.user;

import com.google.gson.Gson;
import com.hhly.partner.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */

public class UpdatePwdByPhoneResp extends BaseResp {


    /**
     * data : {"pwd":"123456789","data":1,"mobile":"18820239427"}
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
         * pwd : 123456789
         * data : 1
         * mobile : 18820239427
         */

        private String pwd;
        private int data;
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
