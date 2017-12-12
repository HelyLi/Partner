package com.hhly.partner.data.net.protocol.game;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.hhly.partner.data.net.protocol.BaseResp;

import java.util.List;

import static com.hhly.partner.presentation.view.product.ProductRecommendAdapter.RECOMMEND_TYPE_GRID_DISPLAY;


/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class GameDataResp extends BaseResp {
    /**
     * data : {"maxAgent":[{"NAME":"神秘X","ID":10105,"TITTLEIMG_URL":"http://public.13322.com/5661e655.jpg"},{"NAME":"使命召唤","ID":10120,"TITTLEIMG_URL":"http://public.13322.com/40a7e396.jpg"},{"NAME":"神飞手","ID":10102,"TITTLEIMG_URL":"http://public.13322.com/484d3b4b.png"},{"NAME":"仙侠道","ID":10104,"TITTLEIMG_URL":"http://public.13322.com/6d7bafec.jpg"},{"NAME":"乐盈竞猜","ID":10110,"TITTLEIMG_URL":"http://public.13322.com/77fa2cdc.jpg"},{"NAME":"全民武媚娘","ID":10136,"TITTLEIMG_URL":"http://public.13322.com/2e8866f9.jpg"},{"NAME":"虹焰之火","ID":10106,"TITTLEIMG_URL":"http://public.13322.com/4a291018.jpg"},{"NAME":"神刀手之2","ID":10103,"TITTLEIMG_URL":"http://public.13322.com/37638d4f.jpg"}],"maxNews":[{"AGENT":0,"NAME":"H5测试3","ID":10562,"TITTLEIMG_URL":"http://public.13322.com/79b448fb.jpg"},{"AGENT":0,"NAME":"H5测试2","ID":10561,"TITTLEIMG_URL":"http://public.13322.com/38ad6a31.jpg"},{"AGENT":0,"NAME":"H5测试","ID":10560,"TITTLEIMG_URL":"http://public.13322.com/5d750d82.jpg"},{"AGENT":0,"NAME":"海妖直播","ID":10540,"TITTLEIMG_URL":"http://public.13322.com/74aca764.jpg"}],"hotGame":[{"NAME":"乐盈盈游戏平台H4","ID":4,"TITTLEIMG_URL":null},{"NAME":"传奇皇朝","ID":20009,"TITTLEIMG_URL":null},{"NAME":"怒火传奇","ID":20010,"TITTLEIMG_URL":null},{"NAME":"全民运转","ID":10005,"TITTLEIMG_URL":"http://public.13322.com/5b6cc5d6.jpg"},{"NAME":"一比分APP","ID":10002,"TITTLEIMG_URL":"http://public.13322.com/23ad9946.jpg"},{"NAME":"乐盈电竞（马来西亚）","ID":10096,"TITTLEIMG_URL":"http://public.13322.com/276595b.png"},{"NAME":"4177游戏平台","ID":30001,"TITTLEIMG_URL":null},{"NAME":"乐盈电竞（泰国）","ID":10093,"TITTLEIMG_URL":"http://public.13322.com/276595b.png"}],"maxGains":[{"NAME":"崩溃的神经猫","ID":10107,"TITTLEIMG_URL":"http://public.13322.com/6de8715b.jpg"},{"NAME":"神道1","ID":20000,"TITTLEIMG_URL":"http://public.13322.com/4c3ae7ad.png"},{"NAME":"新乱世隋唐","ID":20008,"TITTLEIMG_URL":"http://public.13322.com/12e2d57a.jpg"},{"NAME":"天问","ID":20011,"TITTLEIMG_URL":"http://public.13322.com/2a0ff166.jpg"},{"NAME":"乐盈电竞","ID":10092,"TITTLEIMG_URL":"http://public.13322.com/eb48c20.jpg"},{"NAME":"天神战","ID":20005,"TITTLEIMG_URL":"http://public.13322.com/31a6539.jpg"},{"NAME":"丽华传奇","ID":20004,"TITTLEIMG_URL":"http://public.13322.com/699217de.jpg"},{"NAME":"斗三国","ID":20003,"TITTLEIMG_URL":"http://public.13322.com/1f043cf3.jpg"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<MaxAgentBean> maxAgent;
        private List<MaxNewsBean> maxNews;
        private List<HotGameBean> hotGame;
        private List<MaxGainsBean> maxGains;

        public List<MaxAgentBean> getMaxAgent() {
            return maxAgent;
        }

        public void setMaxAgent(List<MaxAgentBean> maxAgent) {
            this.maxAgent = maxAgent;
        }

        public List<MaxNewsBean> getMaxNews() {
            return maxNews;
        }

        public void setMaxNews(List<MaxNewsBean> maxNews) {
            this.maxNews = maxNews;
        }

        public List<HotGameBean> getHotGame() {
            return hotGame;
        }

        public void setHotGame(List<HotGameBean> hotGame) {
            this.hotGame = hotGame;
        }

        public List<MaxGainsBean> getMaxGains() {
            return maxGains;
        }

        public void setMaxGains(List<MaxGainsBean> maxGains) {
            this.maxGains = maxGains;
        }

        public static class MaxAgentBean implements BaseGameBean {
            /**
             * NAME : 神秘X
             * ID : 10105
             * TITTLEIMG_URL : http://public.13322.com/5661e655.jpg
             */

            private String NAME;
            private int ID;
            private String TITTLEIMG_URL;

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

        public static class MaxNewsBean implements BaseGameBean, MultiItemEntity {
            /**
             * AGENT : 0
             * NAME : H5测试3
             * ID : 10562
             * TITTLEIMG_URL : http://public.13322.com/79b448fb.jpg
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

            @Override
            public int getItemType() {
                return RECOMMEND_TYPE_GRID_DISPLAY;
            }
        }

        public static class HotGameBean implements BaseGameBean {
            /**
             * NAME : 乐盈盈游戏平台H4
             * ID : 4
             * TITTLEIMG_URL : null
             */

            private String NAME;
            private int ID;
            private String TITTLEIMG_URL;

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

        public static class MaxGainsBean implements BaseGameBean {
            /**
             * NAME : 崩溃的神经猫
             * ID : 10107
             * TITTLEIMG_URL : http://public.13322.com/6de8715b.jpg
             */

            private String NAME;
            private int ID;
            private String TITTLEIMG_URL;

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

    public interface BaseGameBean {
        String getNAME();

        String getTITTLEIMG_URL();

        int getID();
    }
}
