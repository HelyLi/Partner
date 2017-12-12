package com.hhly.partner.data.net.protocol.proxy;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class AppUnderAgentsNumByTimeareaResp extends BaseResp {


    /**
     * data : {"list":[{"QUERY_DATE":"2017-04-06","QUERY_COUNT":2},{"QUERY_DATE":"2017-04-07","QUERY_COUNT":3},{"QUERY_DATE":"2017-04-08","QUERY_COUNT":2},{"QUERY_DATE":"2017-04-09","QUERY_COUNT":2},{"QUERY_DATE":"2017-04-10","QUERY_COUNT":3},{"QUERY_DATE":"2017-04-12","QUERY_COUNT":3}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * QUERY_DATE : 2017-04-06
             * QUERY_COUNT : 2
             */

            private String QUERY_DATE;
            private int QUERY_COUNT;

            public String getQUERY_DATE() {
                return QUERY_DATE;
            }

            public void setQUERY_DATE(String QUERY_DATE) {
                this.QUERY_DATE = QUERY_DATE;
            }

            public int getQUERY_COUNT() {
                return QUERY_COUNT;
            }

            public void setQUERY_COUNT(int QUERY_COUNT) {
                this.QUERY_COUNT = QUERY_COUNT;
            }
        }
    }
}
