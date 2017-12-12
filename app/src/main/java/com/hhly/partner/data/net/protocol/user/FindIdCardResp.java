package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class FindIdCardResp extends BaseResp {
    /**
     * data : {"data":[{"CARDNO":"411302198908052832"}],"mobile":"13641460007"}
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
         * data : [{"CARDNO":"411302198908052832"}]
         * mobile : 13641460007
         */

        private String mobile;
        private List<DataBean> data;

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * CARDNO : 411302198908052832
             */

            private String CARDNO;

            public String getCARDNO() {
                return CARDNO;
            }

            public void setCARDNO(String CARDNO) {
                this.CARDNO = CARDNO;
            }
        }
    }
}
