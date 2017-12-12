package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */

public class PayPwdResp extends BaseResp {

    /**
     * data : {"data":[{"PASSWORD":null}]}
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
             * PASSWORD : null
             */

            private String PASSWORD;

            public String getPASSWORD() {
                return PASSWORD;
            }

            public void setPASSWORD(String PASSWORD) {
                this.PASSWORD = PASSWORD;
            }
        }
    }
}
