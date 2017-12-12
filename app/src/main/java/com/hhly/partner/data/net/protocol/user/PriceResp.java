package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class PriceResp extends BaseResp {


    /**
     * data : {"data":[{"AMOUNT":0,"USERID":"hb2118"}]}
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

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * AMOUNT : 0
             * USERID : hb2118
             */

            private double AMOUNT;
            private String USERID;

            public double getAMOUNT() {
                return AMOUNT;
            }

            public void setAMOUNT(double AMOUNT) {
                this.AMOUNT = AMOUNT;
            }

            public String getUSERID() {
                return USERID;
            }

            public void setUSERID(String USERID) {
                this.USERID = USERID;
            }
        }
    }
}
