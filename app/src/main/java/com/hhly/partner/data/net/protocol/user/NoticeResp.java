package com.hhly.partner.data.net.protocol.user;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class NoticeResp extends BaseResp {

    /**
     * data : {"result":0,"msg":"成功","pager":{"totalRows":2,"startRow":0,"pageSize":10,"totalPages":1,"list":[{"id":625,"title":"颤抖的人生，只能勇往直前","creatorTime":1481099086000,"creator":"mrdeepen","type":2,"status":1,"isTop":1,"titleHref":"http://www.baidu.com","terminalsType":4,"country":"0","explain":"人生路漫漫，谁道人生不坎坷，没有仙人的指路，没有彼此的相俯相诚，唯有只影形单的自已，靠着自己的双脚，一步一个脚印，艰难履历，穿别人的鞋，走自己的路吧，让别人光着脚...","imageUrl":"http://public.13322.com/6d70d158.jpg"},{"id":597,"title":"人生如戏，戏如人生","creatorTime":1480840293000,"creator":"mrdeepen","type":2,"status":1,"isTop":1,"titleHref":"http://37.com","terminalsType":4,"country":"0","explain":"人生就像一场博弈的游戏，而我们就是当局中的一名弈人，游戏的开局和弈人的思维将会影响着这场博弈的成功与否，煮酒论英雄，谁将会博得最后的喝彩和荣耀，让我们拭目以待.","imageUrl":"http://public.13322.com/544a2597.jpg"}],"pageNo":"1"}}
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
         * result : 0
         * msg : 成功
         * pager : {"totalRows":2,"startRow":0,"pageSize":10,"totalPages":1,"list":[{"id":625,"title":"颤抖的人生，只能勇往直前","creatorTime":1481099086000,"creator":"mrdeepen","type":2,"status":1,"isTop":1,"titleHref":"http://www.baidu.com","terminalsType":4,"country":"0","explain":"人生路漫漫，谁道人生不坎坷，没有仙人的指路，没有彼此的相俯相诚，唯有只影形单的自已，靠着自己的双脚，一步一个脚印，艰难履历，穿别人的鞋，走自己的路吧，让别人光着脚...","imageUrl":"http://public.13322.com/6d70d158.jpg"},{"id":597,"title":"人生如戏，戏如人生","creatorTime":1480840293000,"creator":"mrdeepen","type":2,"status":1,"isTop":1,"titleHref":"http://37.com","terminalsType":4,"country":"0","explain":"人生就像一场博弈的游戏，而我们就是当局中的一名弈人，游戏的开局和弈人的思维将会影响着这场博弈的成功与否，煮酒论英雄，谁将会博得最后的喝彩和荣耀，让我们拭目以待.","imageUrl":"http://public.13322.com/544a2597.jpg"}],"pageNo":"1"}
         */

        private PagerBean pager;

        public PagerBean getPager() {
            return pager;
        }

        public void setPager(PagerBean pager) {
            this.pager = pager;
        }

        public static class PagerBean {
            /**
             * totalRows : 2
             * startRow : 0
             * pageSize : 10
             * totalPages : 1
             * list : [{"id":625,"title":"颤抖的人生，只能勇往直前","creatorTime":1481099086000,"creator":"mrdeepen","type":2,"status":1,"isTop":1,"titleHref":"http://www.baidu.com","terminalsType":4,"country":"0","explain":"人生路漫漫，谁道人生不坎坷，没有仙人的指路，没有彼此的相俯相诚，唯有只影形单的自已，靠着自己的双脚，一步一个脚印，艰难履历，穿别人的鞋，走自己的路吧，让别人光着脚...","imageUrl":"http://public.13322.com/6d70d158.jpg"},{"id":597,"title":"人生如戏，戏如人生","creatorTime":1480840293000,"creator":"mrdeepen","type":2,"status":1,"isTop":1,"titleHref":"http://37.com","terminalsType":4,"country":"0","explain":"人生就像一场博弈的游戏，而我们就是当局中的一名弈人，游戏的开局和弈人的思维将会影响着这场博弈的成功与否，煮酒论英雄，谁将会博得最后的喝彩和荣耀，让我们拭目以待.","imageUrl":"http://public.13322.com/544a2597.jpg"}]
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
                 * id : 625
                 * title : 颤抖的人生，只能勇往直前
                 * creatorTime : 1481099086000
                 * creator : mrdeepen
                 * type : 2
                 * status : 1
                 * isTop : 1
                 * titleHref : http://www.baidu.com
                 * terminalsType : 4
                 * country : 0
                 * explain : 人生路漫漫，谁道人生不坎坷，没有仙人的指路，没有彼此的相俯相诚，唯有只影形单的自已，靠着自己的双脚，一步一个脚印，艰难履历，穿别人的鞋，走自己的路吧，让别人光着脚...
                 * imageUrl : http://public.13322.com/6d70d158.jpg
                 */

                private int id;
                private String title;
                private long creatorTime;
                private String creator;
                private int type;
                private int status;
                private int isTop;
                private String titleHref;
                private int terminalsType;
                private String country;
                private String explain;
                private String imageUrl;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public long getCreatorTime() {
                    return creatorTime;
                }

                public void setCreatorTime(long creatorTime) {
                    this.creatorTime = creatorTime;
                }

                public String getCreator() {
                    return creator;
                }

                public void setCreator(String creator) {
                    this.creator = creator;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getIsTop() {
                    return isTop;
                }

                public void setIsTop(int isTop) {
                    this.isTop = isTop;
                }

                public String getTitleHref() {
                    return titleHref;
                }

                public void setTitleHref(String titleHref) {
                    this.titleHref = titleHref;
                }

                public int getTerminalsType() {
                    return terminalsType;
                }

                public void setTerminalsType(int terminalsType) {
                    this.terminalsType = terminalsType;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public String getExplain() {
                    return explain;
                }

                public void setExplain(String explain) {
                    this.explain = explain;
                }

                public String getImageUrl() {
                    return imageUrl;
                }

                public void setImageUrl(String imageUrl) {
                    this.imageUrl = imageUrl;
                }
            }
        }
    }
}
