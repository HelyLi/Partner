package com.hhly.partner.data.net.protocol.game;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

import static com.hhly.partner.presentation.view.extension.CustomExtensionHeaderAdapter.GAME_ADDED_TYPE;

/**
 * description
 * Created by dell on 2017/5/3.
 */

public class GetGameCustomizationResp extends BaseResp implements MultiItemEntity {


    /**
     * data : {"data":[{"PROMOTION_GAME_ID":10143,"NAME":"幻影一代","PROMOTION_POSITION":3,"ICON_URL":"http://public.13322.com/4352aed9.jpg"},{"PROMOTION_GAME_ID":10132,"NAME":"袖珍版中国象棋","PROMOTION_POSITION":3,"ICON_URL":"http://public.13322.com/2ef0c2dc.jpg"}],"userid":"hb1562","modeId":"11"}
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
         * data : [{"PROMOTION_GAME_ID":10143,"NAME":"幻影一代","PROMOTION_POSITION":3,"ICON_URL":"http://public.13322.com/4352aed9.jpg"},{"PROMOTION_GAME_ID":10132,"NAME":"袖珍版中国象棋","PROMOTION_POSITION":3,"ICON_URL":"http://public.13322.com/2ef0c2dc.jpg"}]
         * userid : hb1562
         * modeId : 11
         */

        private String userid;
        private String modeId;
        private List<DataBean> data;

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getModeId() {
            return modeId;
        }

        public void setModeId(String modeId) {
            this.modeId = modeId;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * PROMOTION_GAME_ID : 10143
             * NAME : 幻影一代
             * PROMOTION_POSITION : 3
             * ICON_URL : http://public.13322.com/4352aed9.jpg
             */

            private int PROMOTION_GAME_ID;
            private String NAME;
            private int PROMOTION_POSITION;
            private String ICON_URL;

            public int getPROMOTION_GAME_ID() {
                return PROMOTION_GAME_ID;
            }

            public void setPROMOTION_GAME_ID(int PROMOTION_GAME_ID) {
                this.PROMOTION_GAME_ID = PROMOTION_GAME_ID;
            }

            public String getNAME() {
                return NAME;
            }

            public void setNAME(String NAME) {
                this.NAME = NAME;
            }

            public int getPROMOTION_POSITION() {
                return PROMOTION_POSITION;
            }

            public void setPROMOTION_POSITION(int PROMOTION_POSITION) {
                this.PROMOTION_POSITION = PROMOTION_POSITION;
            }

            public String getICON_URL() {
                return ICON_URL;
            }

            public void setICON_URL(String ICON_URL) {
                this.ICON_URL = ICON_URL;
            }
        }
    }

    @Override
    public int getItemType() {
        return GAME_ADDED_TYPE;
    }
}
