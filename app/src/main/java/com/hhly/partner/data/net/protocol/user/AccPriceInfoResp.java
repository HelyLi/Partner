package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class AccPriceInfoResp extends BaseResp {


    /**
     * data : {"start":"2017-04-12","data":[{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h19","RECHARGE_AMOUNT":1},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h19","RECHARGE_AMOUNT":1},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h19","RECHARGE_AMOUNT":1},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h18","RECHARGE_AMOUNT":1},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h18","RECHARGE_AMOUNT":1},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h18","RECHARGE_AMOUNT":3},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h17","RECHARGE_AMOUNT":3},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h17","RECHARGE_AMOUNT":5},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h19","RECHARGE_AMOUNT":11},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h010","RECHARGE_AMOUNT":3},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h010","RECHARGE_AMOUNT":2}],"userid":"hb1320","memberUserids":["h19","h18","h17","h16","h15","h14","h13","h12","h011","h010","h009","h008","h007","h006","h005","h004","h003","h002","h001","huiyuan107","huiyuan104","huiyuan103","huiyuan102","huihui0510","huiyuan101","huiyuan100","huiyuan009"],"type":"2"}
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
         * start : 2017-04-12
         * data : [{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h19","RECHARGE_AMOUNT":1},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h19","RECHARGE_AMOUNT":1},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h19","RECHARGE_AMOUNT":1},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h18","RECHARGE_AMOUNT":1},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h18","RECHARGE_AMOUNT":1},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h18","RECHARGE_AMOUNT":3},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h17","RECHARGE_AMOUNT":3},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h17","RECHARGE_AMOUNT":5},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h19","RECHARGE_AMOUNT":11},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h010","RECHARGE_AMOUNT":3},{"RECHARGE_TIME":"2017-04-12 17:58:14","MOBILE":"h010","RECHARGE_AMOUNT":2}]
         * userid : hb1320
         * memberUserids : ["h19","h18","h17","h16","h15","h14","h13","h12","h011","h010","h009","h008","h007","h006","h005","h004","h003","h002","h001","huiyuan107","huiyuan104","huiyuan103","huiyuan102","huihui0510","huiyuan101","huiyuan100","huiyuan009"]
         * type : 2
         */

        private String start;
        private String userid;
        private String type;
        private List<DataBean> data;
        private List<String> memberUserids;

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public List<String> getMemberUserids() {
            return memberUserids;
        }

        public void setMemberUserids(List<String> memberUserids) {
            this.memberUserids = memberUserids;
        }

        public static class DataBean {
            /**
             * RECHARGE_TIME : 2017-04-12 17:58:14
             * MOBILE : h19
             * RECHARGE_AMOUNT : 1
             */

            private String RECHARGE_TIME;
            private String MOBILE;
            private String RECHARGE_AMOUNT;

            public String getRECHARGE_TIME() {
                return RECHARGE_TIME;
            }

            public void setRECHARGE_TIME(String RECHARGE_TIME) {
                this.RECHARGE_TIME = RECHARGE_TIME;
            }

            public String getMOBILE() {
                return MOBILE;
            }

            public void setMOBILE(String MOBILE) {
                this.MOBILE = MOBILE;
            }

            public String getRECHARGE_AMOUNT() {
                return RECHARGE_AMOUNT;
            }

            public void setRECHARGE_AMOUNT(String RECHARGE_AMOUNT) {
                this.RECHARGE_AMOUNT = RECHARGE_AMOUNT;
            }
        }
    }
}
