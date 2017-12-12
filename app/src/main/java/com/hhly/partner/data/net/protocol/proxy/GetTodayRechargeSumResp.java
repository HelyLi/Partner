package com.hhly.partner.data.net.protocol.proxy;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class GetTodayRechargeSumResp extends BaseResp {


    /**
     * data : {"today_recharge":0,"weekList":[{"QUERY_DATE":"2017-04-14","QUERY_COUNT":32}],"week_recharge":32,"monthList":[{"QUERY_DATE":"2017-04-12","QUERY_COUNT":32},{"QUERY_DATE":"2017-04-13","QUERY_COUNT":32},{"QUERY_DATE":"2017-04-14","QUERY_COUNT":32}],"max_recharge":32,"threeMonthList":[{"QUERY_DATE":"2017-04-12","QUERY_COUNT":32},{"QUERY_DATE":"2017-04-13","QUERY_COUNT":32},{"QUERY_DATE":"2017-04-14","QUERY_COUNT":32}]}
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
         * today_recharge : 0
         * weekList : [{"QUERY_DATE":"2017-04-14","QUERY_COUNT":32}]
         * week_recharge : 32
         * monthList : [{"QUERY_DATE":"2017-04-12","QUERY_COUNT":32},{"QUERY_DATE":"2017-04-13","QUERY_COUNT":32},{"QUERY_DATE":"2017-04-14","QUERY_COUNT":32}]
         * max_recharge : 32
         * threeMonthList : [{"QUERY_DATE":"2017-04-12","QUERY_COUNT":32},{"QUERY_DATE":"2017-04-13","QUERY_COUNT":32},{"QUERY_DATE":"2017-04-14","QUERY_COUNT":32}]
         */

        private String today_recharge;
        private String week_recharge;
        private String max_recharge;
        private List<WeekListBean> weekList;
        private List<WeekListBean> monthList;
        private List<WeekListBean> threeMonthList;

        public String getToday_recharge() {
            return today_recharge;
        }

        public void setToday_recharge(String today_recharge) {
            this.today_recharge = today_recharge;
        }

        public String getWeek_recharge() {
            return week_recharge;
        }

        public void setWeek_recharge(String week_recharge) {
            this.week_recharge = week_recharge;
        }

        public String getMax_recharge() {
            return max_recharge;
        }

        public void setMax_recharge(String max_recharge) {
            this.max_recharge = max_recharge;
        }

        public List<WeekListBean> getWeekList() {
            return weekList;
        }

        public void setWeekList(List<WeekListBean> weekList) {
            this.weekList = weekList;
        }

        public List<WeekListBean> getMonthList() {
            return monthList;
        }

        public void setMonthList(List<WeekListBean> monthList) {
            this.monthList = monthList;
        }

        public List<WeekListBean> getThreeMonthList() {
            return threeMonthList;
        }

        public void setThreeMonthList(List<WeekListBean> threeMonthList) {
            this.threeMonthList = threeMonthList;
        }

        public static class WeekListBean {
            /**
             * QUERY_DATE : 2017-04-14
             * QUERY_COUNT : 32
             */

            private String QUERY_DATE;
            private String QUERY_COUNT;

            public String getQUERY_DATE() {
                return QUERY_DATE;
            }

            public void setQUERY_DATE(String QUERY_DATE) {
                this.QUERY_DATE = QUERY_DATE;
            }

            public String getQUERY_COUNT() {
                return QUERY_COUNT;
            }

            public void setQUERY_COUNT(String QUERY_COUNT) {
                this.QUERY_COUNT = QUERY_COUNT;
            }
        }
    }
}
