package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class MobileResp extends BaseResp {

    /**
     * data : {"data":[{"PHONE":"15599999995","PARTNER_NO":"61053"}]}
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
             * PHONE : 15599999995
             * PARTNER_NO : 61053
             */

            private String PHONE;
            private String PARTNER_NO;

            public String getPHONE() {
                return PHONE;
            }

            public void setPHONE(String PHONE) {
                this.PHONE = PHONE;
            }

            public String getPARTNER_NO() {
                return PARTNER_NO;
            }

            public void setPARTNER_NO(String PARTNER_NO) {
                this.PARTNER_NO = PARTNER_NO;
            }
        }
    }
}
