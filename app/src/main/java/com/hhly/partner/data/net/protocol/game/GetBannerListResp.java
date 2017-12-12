package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class GetBannerListResp extends BaseResp {


    /**
     * data : {"page":"1","data":[{"ID":10621,"ICON_URL":"http://public.13322.com/38168860.jpg"},{"ID":10620,"ICON_URL":"http://public.13322.com/2bc9e90c.png"},{"ID":11112,"ICON_URL":"http://public.13322.com/1f3eff94.png"},{"ID":10606,"ICON_URL":null},{"ID":10604,"ICON_URL":"http://public.13322.com/594f5044.jpg"},{"ID":10603,"ICON_URL":"http://public.13322.com/53ab1b13.jpg"}]}
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
         * page : 1
         * data : [{"ID":10621,"ICON_URL":"http://public.13322.com/38168860.jpg"},{"ID":10620,"ICON_URL":"http://public.13322.com/2bc9e90c.png"},{"ID":11112,"ICON_URL":"http://public.13322.com/1f3eff94.png"},{"ID":10606,"ICON_URL":null},{"ID":10604,"ICON_URL":"http://public.13322.com/594f5044.jpg"},{"ID":10603,"ICON_URL":"http://public.13322.com/53ab1b13.jpg"}]
         */

        private String page;
        private List<DataBean> data;

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
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
            private boolean isSelected;

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

            public void setSelected(boolean selected) {
                isSelected = selected;
            }

            public boolean isSelected() {
                return isSelected;
            }
        }
    }
}
