package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class CheckPartnerNoResp extends BaseResp {

    /**
     * data : {"data":[{"PARTNER_NO":"56669"}],"partnerNo":"56669"}
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
         * data : [{"PARTNER_NO":"56669"}]
         * partnerNo : 56669
         */

        private String partnerNo;
        private List<DataBean> data;

        public String getPartnerNo() {
            return partnerNo;
        }

        public void setPartnerNo(String partnerNo) {
            this.partnerNo = partnerNo;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * PARTNER_NO : 56669
             */

            private String PARTNER_NO;

            public String getPARTNER_NO() {
                return PARTNER_NO;
            }

            public void setPARTNER_NO(String PARTNER_NO) {
                this.PARTNER_NO = PARTNER_NO;
            }
        }
    }
}
