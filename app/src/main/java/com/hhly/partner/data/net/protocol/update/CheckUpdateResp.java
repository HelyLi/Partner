package com.hhly.partner.data.net.protocol.update;

import com.hhly.partner.data.net.protocol.BaseResp;

import java.io.Serializable;

/**
 * 检测版本更新resp
 * Created by dell on 2017/5/17.
 */

public class CheckUpdateResp extends BaseResp implements Serializable {


    /**
     * data : {"       id":8,"appId":"com.hhly.partner","title":"版本更新","versionCode":101,"url":"http://public.13322.com/20170517160246_533.apk","content":"啦啦啦啦","updateType":1,"createTime":"2017-05-17 16:04:42.0","country":0,"platformTerminal":2}
     */

    private DataBean data;

    public static class DataBean implements Serializable {
        /**
         * id : 8
         * appId : com.hhly.partner
         * title : 版本更新
         * versionCode : 101
         * url : http://public.13322.com/20170517160246_533.apk
         * content : 啦啦啦啦
         * updateType : 1
         * createTime : 2017-05-17 16:04:42.0
         * country : 0
         * platformTerminal : 2
         */

        private int id;
        private String appId;
        private String title;
        private int versionCode;
        private String url;
        private String content;
        private int updateType;
        private String createTime;
        private int country;
        private int platformTerminal;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getUpdateType() {
            return updateType;
        }

        public void setUpdateType(int updateType) {
            this.updateType = updateType;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getCountry() {
            return country;
        }

        public void setCountry(int country) {
            this.country = country;
        }

        public int getPlatformTerminal() {
            return platformTerminal;
        }

        public void setPlatformTerminal(int platformTerminal) {
            this.platformTerminal = platformTerminal;
        }
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }
}
