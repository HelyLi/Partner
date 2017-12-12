package com.hhly.partner.data.net.protocol.proxy;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class AppAgentProductRechargeGraphResp extends BaseResp {


    /**
     * data : {"list":[{"QUERY_DATE":"2017-04-21","QUERY_COUNT":50}]}
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
             * QUERY_DATE : 2017-04-21
             * QUERY_COUNT : 50
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
