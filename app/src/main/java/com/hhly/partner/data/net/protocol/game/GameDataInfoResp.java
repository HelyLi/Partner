package com.hhly.partner.data.net.protocol.game;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hhly.partner.data.net.protocol.BaseResp;
import com.hhly.partner.presentation.view.product.ProductDetailsAdapter;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class GameDataInfoResp extends BaseResp {


    /**
     * data : {"phone":"15599999995","among":[{"PHONE":"15599999995","RECHARGE_AMOUNT":1730,"RN":1},{"PHONE":"13588888888","RECHARGE_AMOUNT":131,"RN":2},{"PHONE":"18688888888","RECHARGE_AMOUNT":102,"RN":3},{"PHONE":"15599999999","RECHARGE_AMOUNT":100,"RN":4},{"PHONE":"15599999994","RECHARGE_AMOUNT":67,"RN":5},{"PHONE":"13877770004","RECHARGE_AMOUNT":18,"RN":6},{"PHONE":"13877770001","RECHARGE_AMOUNT":2,"RN":7}],"gameId":"10440","userid":"hb1547","data":[{"AGENT":0,"NAME":"王者荣耀","DESC":"王者荣耀","ID":10440,"TITTLEIMG_URL":"http://public.13322.com/428426c8.jpg","ICON_URL":"http://public.13322.com/3445f0d1.jpg"}],"my":[{"PHONE":"15599999995","RECHARGE_AMOUNT":1730,"RN":1}]}
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
         * phone : 15599999995
         * among : [{"PHONE":"15599999995","RECHARGE_AMOUNT":1730,"RN":1},{"PHONE":"13588888888","RECHARGE_AMOUNT":131,"RN":2},{"PHONE":"18688888888","RECHARGE_AMOUNT":102,"RN":3},{"PHONE":"15599999999","RECHARGE_AMOUNT":100,"RN":4},{"PHONE":"15599999994","RECHARGE_AMOUNT":67,"RN":5},{"PHONE":"13877770004","RECHARGE_AMOUNT":18,"RN":6},{"PHONE":"13877770001","RECHARGE_AMOUNT":2,"RN":7}]
         * gameId : 10440
         * userid : hb1547
         * data : [{"AGENT":0,"NAME":"王者荣耀","DESC":"王者荣耀","ID":10440,"TITTLEIMG_URL":"http://public.13322.com/428426c8.jpg","ICON_URL":"http://public.13322.com/3445f0d1.jpg"}]
         * my : [{"PHONE":"15599999995","RECHARGE_AMOUNT":1730,"RN":1}]
         */

        private String phone;
        private String gameId;
        private String userid;
        private List<AmongBean> among;
        private List<DataBean> data;
        private List<MyBean> my;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public List<AmongBean> getAmong() {
            return among;
        }

        public void setAmong(List<AmongBean> among) {
            this.among = among;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public List<MyBean> getMy() {
            return my;
        }

        public void setMy(List<MyBean> my) {
            this.my = my;
        }

        public static class AmongBean implements MultiItemEntity{
            /**
             * PHONE : 15599999995
             * RECHARGE_AMOUNT : 1730
             * RN : 1
             */

            private String PHONE;
            private String RECHARGE_AMOUNT;
            private int RN;

            public String getPHONE() {
                return PHONE;
            }

            public void setPHONE(String PHONE) {
                this.PHONE = PHONE;
            }

            public String getRECHARGE_AMOUNT() {
                return RECHARGE_AMOUNT;
            }

            public void setRECHARGE_AMOUNT(String RECHARGE_AMOUNT) {
                this.RECHARGE_AMOUNT = RECHARGE_AMOUNT;
            }

            public int getRN() {
                return RN;
            }

            public void setRN(int RN) {
                this.RN = RN;
            }

            @Override
            public int getItemType() {
                return ProductDetailsAdapter.NORMAL_ITEM;
            }
        }

        public static class DataBean {
            /**
             * AGENT : 0
             * NAME : 王者荣耀
             * DESC : 王者荣耀
             * ID : 10440
             * TITTLEIMG_URL : http://public.13322.com/428426c8.jpg
             * ICON_URL : http://public.13322.com/3445f0d1.jpg
             */

            private int AGENT;
            private String NAME;
            private String DESC;
            private int ID;
            private String TITTLEIMG_URL;
            private String ICON_URL;

            public int getAGENT() {
                return AGENT;
            }

            public void setAGENT(int AGENT) {
                this.AGENT = AGENT;
            }

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            public String getDESC() {
                return DESC;
            }

            public void setDESC(String DESC) {
                this.DESC = DESC;
            }

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getTITTLEIMG_URL() {
                return TITTLEIMG_URL;
            }

            public void setTITTLEIMG_URL(String TITTLEIMG_URL) {
                this.TITTLEIMG_URL = TITTLEIMG_URL;
            }

            public String getICON_URL() {
                return ICON_URL;
            }

            public void setICON_URL(String ICON_URL) {
                this.ICON_URL = ICON_URL;
            }
        }

        public static class MyBean {
            /**
             * PHONE : 15599999995
             * RECHARGE_AMOUNT : 1730
             * RN : 1
             */

            private String PHONE;
            private String RECHARGE_AMOUNT;
            private int RN;

            public String getPHONE() {
                return PHONE;
            }

            public void setPHONE(String PHONE) {
                this.PHONE = PHONE;
            }

            public String getRECHARGE_AMOUNT() {
                return RECHARGE_AMOUNT;
            }

            public void setRECHARGE_AMOUNT(String RECHARGE_AMOUNT) {
                this.RECHARGE_AMOUNT = RECHARGE_AMOUNT;
            }

            public int getRN() {
                return RN;
            }

            public void setRN(int RN) {
                this.RN = RN;
            }
        }
    }
}
