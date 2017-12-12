package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class GetRealNameResp extends BaseResp {

    /**
     * data : {"data":[{"CARDNAME":"黄河"}],"userid":"hb1320"}
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
         * data : [{"CARDNAME":"黄河"}]
         * userid : hb1320
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
             * CARDNAME : 黄河
             */

            private String CARDNAME;

            public String getCARDNAME() {
                return CARDNAME;
            }

            public void setCARDNAME(String CARDNAME) {
                this.CARDNAME = CARDNAME;
            }
        }
    }
}
