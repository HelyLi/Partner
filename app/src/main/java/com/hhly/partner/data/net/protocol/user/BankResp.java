package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class BankResp extends BaseResp {

    /**
     * data : {"data":[{"ACC_NO":"1111111111111111","BANK_ID":"民生银行"}]}
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
             * ACC_NO : 1111111111111111
             * BANK_ID : 民生银行
             */

            private String ACC_NO;
            private String BANK_ID;

            public String getACC_NO() {
                return ACC_NO;
            }

            public void setACC_NO(String ACC_NO) {
                this.ACC_NO = ACC_NO;
            }

            public String getBANK_ID() {
                return BANK_ID;
            }

            public void setBANK_ID(String BANK_ID) {
                this.BANK_ID = BANK_ID;
            }
        }
    }
}
