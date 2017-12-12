package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */

public class AccPriceResp extends BaseResp {


    /**
     * data : {"start":"2017-4-19","data":[{"creatime":"2017-4-19","recharge_amount":"0","acc_amount":"0"},{"creatime":"2017-04-20","recharge_amount":"0","acc_amount":"0"},{"creatime":"2017-04-21","recharge_amount":"0","acc_amount":"0"},{"creatime":"2017-04-22","recharge_amount":"0","acc_amount":"0"},{"creatime":"2017-04-23","recharge_amount":"0","acc_amount":"0"},{"creatime":"2017-04-24","recharge_amount":"0","acc_amount":"0"},{"creatime":"2017-04-25","recharge_amount":"0","acc_amount":"0"}],"mbsallList":[],"memberUserids":["h19","h18","h17","h16","h15","h14","h13","h12","h011","h010","h009","h008","h007","h006","h005","h004","h003","h002","h001","huiyuan107","huiyuan104","huiyuan103","huiyuan102","huihui0510","huiyuan101","huiyuan100","huiyuan009"],"end":"2017-4-25"}
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
         * start : 2017-4-19
         * data : [{"creatime":"2017-4-19","recharge_amount":"0","acc_amount":"0"},{"creatime":"2017-04-20","recharge_amount":"0","acc_amount":"0"},{"creatime":"2017-04-21","recharge_amount":"0","acc_amount":"0"},{"creatime":"2017-04-22","recharge_amount":"0","acc_amount":"0"},{"creatime":"2017-04-23","recharge_amount":"0","acc_amount":"0"},{"creatime":"2017-04-24","recharge_amount":"0","acc_amount":"0"},{"creatime":"2017-04-25","recharge_amount":"0","acc_amount":"0"}]
         * mbsallList : []
         * memberUserids : ["h19","h18","h17","h16","h15","h14","h13","h12","h011","h010","h009","h008","h007","h006","h005","h004","h003","h002","h001","huiyuan107","huiyuan104","huiyuan103","huiyuan102","huihui0510","huiyuan101","huiyuan100","huiyuan009"]
         * end : 2017-4-25
         */

        private String start;
        private String end;
        private List<DataBean> data;
        private List<?> mbsallList;
        private List<String> memberUserids;

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getEnd() {
            return end;
        }

        public void setEnd(String end) {
            this.end = end;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public List<?> getMbsallList() {
            return mbsallList;
        }

        public void setMbsallList(List<?> mbsallList) {
            this.mbsallList = mbsallList;
        }

        public List<String> getMemberUserids() {
            return memberUserids;
        }

        public void setMemberUserids(List<String> memberUserids) {
            this.memberUserids = memberUserids;
        }

        public static class DataBean {
            /**
             * creatime : 2017-4-19
             * recharge_amount : 0
             * acc_amount : 0
             */

            private String creatime;
            private double recharge_amount;
            private double acc_amount;

            public String getCreatime() {
                return creatime;
            }

            public void setCreatime(String creatime) {
                this.creatime = creatime;
            }

            public double getRecharge_amount() {
                return recharge_amount;
            }

            public void setRecharge_amount(double recharge_amount) {
                this.recharge_amount = recharge_amount;
            }

            public double getAcc_amount() {
                return acc_amount;
            }

            public void setAcc_amount(double acc_amount) {
                this.acc_amount = acc_amount;
            }
        }
    }
}
