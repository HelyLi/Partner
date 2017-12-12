package com.hhly.partner.data.net.protocol.user;

import com.google.gson.annotations.SerializedName;
import com.hhly.partner.data.net.protocol.BaseResp;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public class RegisterResp extends BaseResp {

    /**
     * data : {"result":0,"msg":"成功","userId":"hb1124"}
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
         * userId : hb1124
         */

        @SerializedName("result")
        private int result;
        @SerializedName("msg")
        private String msg;
        private String userId;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
