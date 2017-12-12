package com.hhly.partner.data.net.protocol.game;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

/**
 * Created by dell on 2017/4/24.
 */

public class GameTypeDataResp extends BaseResp {

    /**
     * data : {"GameTypeData":[{"AGENT":0,"NAME":"冰河世纪","ID":10146,"TITTLEIMG_URL":"http://public.13322.com/3ba9398b.jpg"}],"gameType":"8001","platType":"4","country":"0"}
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
         * GameTypeData : [{"AGENT":0,"NAME":"冰河世纪","ID":10146,"TITTLEIMG_URL":"http://public.13322.com/3ba9398b.jpg"}]
         * gameType : 8001
         * platType : 4
         * country : 0
         */

        private String gameType;
        private String platType;
        private String country;
        private List<GameTypeDataBean> GameTypeData;

        public String getGameType() {
            return gameType;
        }

        public void setGameType(String gameType) {
            this.gameType = gameType;
        }

        public String getPlatType() {
            return platType;
        }

        public void setPlatType(String platType) {
            this.platType = platType;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public List<GameTypeDataBean> getGameTypeData() {
            return GameTypeData;
        }

        public void setGameTypeData(List<GameTypeDataBean> GameTypeData) {
            this.GameTypeData = GameTypeData;
        }

        public static class GameTypeDataBean {
            /**
             * AGENT : 0
             * NAME : 冰河世纪
             * ID : 10146
             * TITTLEIMG_URL : http://public.13322.com/3ba9398b.jpg
             */

            private int AGENT;
            private String NAME;
            private int ID;
            private String TITTLEIMG_URL;

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
        }
    }
}
