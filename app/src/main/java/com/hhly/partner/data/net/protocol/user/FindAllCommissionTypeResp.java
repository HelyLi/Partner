package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/26
 */

public class FindAllCommissionTypeResp extends BaseResp {


    /**
     * data : {"platformType":"4","data":[{"id":"204","gameTypeId":null,"list":["2","1","4","3"],"gameTypeName":"最新游戏类型","gameTypeFlag":"666"},{"id":"244","gameTypeId":null,"list":["1","4"],"gameTypeName":"输赢","gameTypeFlag":"333"},{"id":"262","gameTypeId":null,"list":["4","3","1","2"],"gameTypeName":"海妖直播","gameTypeFlag":"130"},{"id":"264","gameTypeId":null,"list":["2"],"gameTypeName":"有效投注(乐盈电竞)","gameTypeFlag":"111"},{"id":"266","gameTypeId":null,"list":["1"],"gameTypeName":"输赢（乐盈电竞）","gameTypeFlag":"131"}],"country":"0"}
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
         * platformType : 4
         * data : [{"id":"204","gameTypeId":null,"list":["2","1","4","3"],"gameTypeName":"最新游戏类型","gameTypeFlag":"666"},{"id":"244","gameTypeId":null,"list":["1","4"],"gameTypeName":"输赢","gameTypeFlag":"333"},{"id":"262","gameTypeId":null,"list":["4","3","1","2"],"gameTypeName":"海妖直播","gameTypeFlag":"130"},{"id":"264","gameTypeId":null,"list":["2"],"gameTypeName":"有效投注(乐盈电竞)","gameTypeFlag":"111"},{"id":"266","gameTypeId":null,"list":["1"],"gameTypeName":"输赢（乐盈电竞）","gameTypeFlag":"131"}]
         * country : 0
         */

        private String platformType;
        private String country;
        private List<DataBean> data;

        public String getPlatformType() {
            return platformType;
        }

        public void setPlatformType(String platformType) {
            this.platformType = platformType;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 204
             * gameTypeId : null
             * list : ["2","1","4","3"]
             * gameTypeName : 最新游戏类型
             * gameTypeFlag : 666
             */

            private String id;
            private String gameTypeId;
            private String gameTypeName;
            private String gameTypeFlag;
            /**
             * 添加属性,存储返佣类型所对应的类型 1按输赢总额 2按有效金额 3按充值总额
             */
            private Integer COMMISSION_TYPE;
            /**
             * 添加属性,存储获取到的RECORD_ID
             */
            private Integer RECORD_ID;

            public Integer getRECORD_ID() {
                return RECORD_ID;
            }

            public void setRECORD_ID(Integer RECORD_ID) {
                this.RECORD_ID = RECORD_ID;
            }

            private List<String> list;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Integer getCOMMISSION_TYPE() {
                return COMMISSION_TYPE;
            }

            public void setCOMMISSION_TYPE(Integer COMMISSION_TYPE) {
                this.COMMISSION_TYPE = COMMISSION_TYPE;
            }

            public String getGameTypeId() {
                return gameTypeId;
            }

            public void setGameTypeId(String gameTypeId) {
                this.gameTypeId = gameTypeId;
            }

            public String getGameTypeName() {
                return gameTypeName;
            }

            public void setGameTypeName(String gameTypeName) {
                this.gameTypeName = gameTypeName;
            }

            public String getGameTypeFlag() {
                return gameTypeFlag;
            }

            public void setGameTypeFlag(String gameTypeFlag) {
                this.gameTypeFlag = gameTypeFlag;
            }

            public List<String> getList() {
                return list;
            }

            public void setList(List<String> list) {
                this.list = list;
            }
        }
    }
}
