package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/26
 */

public class FindWithdraWalResp extends BaseResp {


    /**
     * data : {"data":[{"ORDER_NO":"hhly_app1494303401836"}],"userid":"hb2344"}
     */

    private DataBeanX data;

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * data : [{"ORDER_NO":"hhly_app1494303401836"}]
         * userid : hb2344
         */

        private String userid;
        private List<DataBean> data;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * ORDER_NO : hhly_app1494303401836
             */

            private String ORDER_NO;

            public String getORDER_NO() {
                return ORDER_NO;
            }

            public void setORDER_NO(String ORDER_NO) {
                this.ORDER_NO = ORDER_NO;
            }
        }
    }
}
