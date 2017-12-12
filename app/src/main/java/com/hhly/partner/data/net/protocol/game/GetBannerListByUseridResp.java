package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class GetBannerListByUseridResp extends BaseResp {

    /**
     * data : {"data":[{"ID":10621,"ICON_URL":"http://public.13322.com/38168860.jpg"}],"userid":"hb2252","typeId":"1"}
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
         * data : [{"ID":10621,"ICON_URL":"http://public.13322.com/38168860.jpg"}]
         * userid : hb2252
         * typeId : 1
         */

        private String userid;
        private String typeId;
        private List<DataBean> data;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * ID : 10621
             * ICON_URL : http://public.13322.com/38168860.jpg
             */

            private int ID;
            private String ICON_URL;

            public int getID() {
                return ID;
            }

            public void setID(int ID) {
                this.ID = ID;
            }

            public String getICON_URL() {
                return ICON_URL;
            }

            public void setICON_URL(String ICON_URL) {
                this.ICON_URL = ICON_URL;
            }
        }
    }
}
