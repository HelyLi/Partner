package com.hhly.partner.data.net.protocol.user;

import com.google.gson.annotations.SerializedName;
import com.hhly.partner.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */

public class IdCardResp extends BaseResp {

    /**
     * data : {"result":0,"msg":"成功","name":"xx","idcard":"xxxxxx","nameResult":"一致","idcardResult":"一致"}
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
         * name : xx
         * idcard : xxxxxx
         * nameResult : 一致
         * idcardResult : 一致
         */

        @SerializedName("result")
        private int resultX;
        @SerializedName("msg")
        private String msgX;
        private String name;
        private String idcard;
        private String nameResult;
        private String idcardResult;

        public int getResultX() {
            return resultX;
        }

        public void setResultX(int resultX) {
            this.resultX = resultX;
        }

        public String getMsgX() {
            return msgX;
        }

        public void setMsgX(String msgX) {
            this.msgX = msgX;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getNameResult() {
            return nameResult;
        }

        public void setNameResult(String nameResult) {
            this.nameResult = nameResult;
        }

        public String getIdcardResult() {
            return idcardResult;
        }

        public void setIdcardResult(String idcardResult) {
            this.idcardResult = idcardResult;
        }
    }
}
