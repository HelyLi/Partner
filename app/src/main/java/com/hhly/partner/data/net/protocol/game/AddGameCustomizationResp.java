package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseResp;

/**
 * Created by dell on 2017/5/4.
 */

public class AddGameCustomizationResp extends BaseResp {

    /**
     * data : {"promotionPosition":"2","gameId":"10137","data":1,"userid":"hb1562","modeId":"11"}
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
         * promotionPosition : 2
         * gameId : 10137
         * data : 1
         * userid : hb1562
         * modeId : 11
         */

        private String promotionPosition;
        private String gameId;
        private int data;
        private String userid;
        private String modeId;

        public String getPromotionPosition() {
            return promotionPosition;
        }

        public void setPromotionPosition(String promotionPosition) {
            this.promotionPosition = promotionPosition;
        }

        public String getGameId() {
            return gameId;
        }

        public void setGameId(String gameId) {
            this.gameId = gameId;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

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
    }
}
