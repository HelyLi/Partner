package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by dell on 2017/4/24.
 */

public class GetIndexlbtResp extends BaseResp{

    /**
     * data : {"totalRows":3,"startRow":0,"pageSize":10,"totalPages":1,"list":[{"id":121,"time":1480748220000,"pictureUrl":"http://public.13322.com/5a79b349.jpg","jumpUrl":"http://mpartner.1332255.com/#/productinfo/10021","pictureOrder":1,"remarks":"红域爵迹，这是一款不错的游戏，技能方面的竞技会让你爱不释手！","country":"0","platformTerminal":4,"pictureType":1},{"id":284,"time":1490856155000,"pictureUrl":"http://public.13322.com/6a8341d3.jpg","jumpUrl":"http://mpartner.1332255.com/#/productinfo/10092","pictureOrder":4,"remarks":"4","country":"0","platformTerminal":4,"pictureType":1},{"id":127,"time":1480757292000,"pictureUrl":"http://public.13322.com/10af2c68.jpg","jumpUrl":"http://mpartner.1332255.com/#/productinfo/20002","pictureOrder":6,"remarks":"生化危机，Hei!  Do you want to fire?!  Come here!","country":"0","platformTerminal":4,"pictureType":1}],"pageNo":"1"}
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
         * totalRows : 3
         * startRow : 0
         * pageSize : 10
         * totalPages : 1
         * list : [{"id":121,"time":1480748220000,"pictureUrl":"http://public.13322.com/5a79b349.jpg","jumpUrl":"http://mpartner.1332255.com/#/productinfo/10021","pictureOrder":1,"remarks":"红域爵迹，这是一款不错的游戏，技能方面的竞技会让你爱不释手！","country":"0","platformTerminal":4,"pictureType":1},{"id":284,"time":1490856155000,"pictureUrl":"http://public.13322.com/6a8341d3.jpg","jumpUrl":"http://mpartner.1332255.com/#/productinfo/10092","pictureOrder":4,"remarks":"4","country":"0","platformTerminal":4,"pictureType":1},{"id":127,"time":1480757292000,"pictureUrl":"http://public.13322.com/10af2c68.jpg","jumpUrl":"http://mpartner.1332255.com/#/productinfo/20002","pictureOrder":6,"remarks":"生化危机，Hei!  Do you want to fire?!  Come here!","country":"0","platformTerminal":4,"pictureType":1}]
         * pageNo : 1
         */

        private int totalRows;
        private int startRow;
        private int pageSize;
        private int totalPages;
        private String pageNo;
        private List<ListBean> list;

        public int getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(int totalRows) {
            this.totalRows = totalRows;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public String getPageNo() {
            return pageNo;
        }

        public void setPageNo(String pageNo) {
            this.pageNo = pageNo;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 121
             * time : 1480748220000
             * pictureUrl : http://public.13322.com/5a79b349.jpg
             * jumpUrl : http://mpartner.1332255.com/#/productinfo/10021
             * pictureOrder : 1
             * remarks : 红域爵迹，这是一款不错的游戏，技能方面的竞技会让你爱不释手！
             * country : 0
             * platformTerminal : 4
             * pictureType : 1
             */

            private int id;
            private long time;
            private String pictureUrl;
            private String jumpUrl;
            private int pictureOrder;
            private String remarks;
            private String country;
            private int platformTerminal;
            private int pictureType;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public long getTime() {
                return time;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public String getPictureUrl() {
                return pictureUrl;
            }

            public void setPictureUrl(String pictureUrl) {
                this.pictureUrl = pictureUrl;
            }

            public String getJumpUrl() {
                return jumpUrl;
            }

            public void setJumpUrl(String jumpUrl) {
                this.jumpUrl = jumpUrl;
            }

            public int getPictureOrder() {
                return pictureOrder;
            }

            public void setPictureOrder(int pictureOrder) {
                this.pictureOrder = pictureOrder;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public int getPlatformTerminal() {
                return platformTerminal;
            }

            public void setPlatformTerminal(int platformTerminal) {
                this.platformTerminal = platformTerminal;
            }

            public int getPictureType() {
                return pictureType;
            }

            public void setPictureType(int pictureType) {
                this.pictureType = pictureType;
            }
        }
    }
}
