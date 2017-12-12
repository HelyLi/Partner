package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/26
 */

public class CommissionTypeResp extends BaseResp {


    /**
     * data : {"data":[{"USER_ID":"hb1320","RECORD_ID":3145,"COMMISSION_TYPE":1,"GAME_TYPE_ID":48},{"USER_ID":"hb1320","RECORD_ID":3155,"COMMISSION_TYPE":3,"GAME_TYPE_ID":65},{"USER_ID":"hb1320","RECORD_ID":3157,"COMMISSION_TYPE":2,"GAME_TYPE_ID":82},{"USER_ID":"hb1320","RECORD_ID":3149,"COMMISSION_TYPE":2,"GAME_TYPE_ID":62},{"USER_ID":"hb1320","RECORD_ID":3151,"COMMISSION_TYPE":3,"GAME_TYPE_ID":63},{"USER_ID":"hb1320","RECORD_ID":3141,"COMMISSION_TYPE":3,"GAME_TYPE_ID":44},{"USER_ID":"hb1320","RECORD_ID":3143,"COMMISSION_TYPE":1,"GAME_TYPE_ID":46},{"USER_ID":"hb1320","RECORD_ID":3147,"COMMISSION_TYPE":3,"GAME_TYPE_ID":61},{"USER_ID":"hb1320","RECORD_ID":3153,"COMMISSION_TYPE":3,"GAME_TYPE_ID":64},{"USER_ID":"hb1320","RECORD_ID":3159,"COMMISSION_TYPE":3,"GAME_TYPE_ID":101}]}
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
             * USER_ID : hb1320
             * RECORD_ID : 3145
             * COMMISSION_TYPE : 1
             * GAME_TYPE_ID : 48
             */

            /**
             * "USER_ID": "hb1521", --用户id
             */
            private String USER_ID;
            /**
             * "RECORD_ID": 3767,   --记录标识
             */
            private int RECORD_ID;
            /**
             * "COMMISSION_TYPE": 1, --佣金方式
             * <p>
             * 佣金结算方式 1按输赢总额 2按有效金额 3按充值总额。
             */
            private int COMMISSION_TYPE;
            /**
             * "GAME_TYPE_ID": 1   -- 游戏类型
             */
            private int GAME_TYPE_ID;

            public String getUSER_ID() {
                return USER_ID;
            }

            public void setUSER_ID(String USER_ID) {
                this.USER_ID = USER_ID;
            }

            public int getRECORD_ID() {
                return RECORD_ID;
            }

            public void setRECORD_ID(int RECORD_ID) {
                this.RECORD_ID = RECORD_ID;
            }

            public int getCOMMISSION_TYPE() {
                return COMMISSION_TYPE;
            }

            public void setCOMMISSION_TYPE(int COMMISSION_TYPE) {
                this.COMMISSION_TYPE = COMMISSION_TYPE;
            }

            public int getGAME_TYPE_ID() {
                return GAME_TYPE_ID;
            }

            public void setGAME_TYPE_ID(int GAME_TYPE_ID) {
                this.GAME_TYPE_ID = GAME_TYPE_ID;
            }
        }
    }
}
