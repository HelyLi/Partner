package com.github.xmxu.cf;

/**
 * 第三方配置信息
 * Created by Simon on 2016/11/21.
 */

public class Config {

    private String mQQAppId = "101377991";
    private String mQQAppKey = "cc9aa07a7d8f3155643147cb008d976a";

//    wxdd4a10d4e8c7cac0

    private String mWechatAppId = "wxdd4a10d4e8c7cac0";//wx2a5538052969956e
//    private String mWFTAppId = "wx4503d46fa027fe4a";//
    private String mWechatAppKey = "3424a3398dd46449a3d02a9160184bdd";

    private String mWeiboAppId = "428661569";
    private String mWeiboAppKey = "0295a46581fff961a24e81644546eea1";

    private String mWeiboRedirectUrl = "https://api.weibo.com/oauth2/default.html";
    private String mWeiboScope = "email,direct_messages_read,direct_messages_write,"
            + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
            + "follow_app_official_microblog," + "invitation_write";
    private String mWechatScope = "snsapi_userinfo";
    //    private String mQQScope = "get_simple_userinfo";
    private String mQQScope = "all";

    private static Config sInstance = null;

    private Config() {
    }

    public static Config get() {
        if (sInstance == null) {
            synchronized (Config.class) {
                if (sInstance == null) {
                    sInstance = new Config();
                }
            }
        }
        return sInstance;
    }

    public Config qq(String appId, String appKey) {
        mQQAppId = appId;
        mQQAppKey = appKey;
        return this;
    }

    public Config weibo(String appId, String appKey) {
        mWeiboAppId = appId;
        mWeiboAppKey = appKey;
        return this;
    }

    public Config wechat(String appId, String appKey) {
        mWechatAppId = appId;
        mWechatAppKey = appKey;
        return this;
    }

//    public String getWFTAppId() {
//        return mWFTAppId;
//    }
//
//    public Config setWFTAppId(String WFTAppId) {
//        mWFTAppId = WFTAppId;
//        return this;
//    }

    public Config weiboRedirectUrl(String url) {
        mWeiboRedirectUrl = url;
        return this;
    }

    public Config weiboScope(String scope) {
        mWeiboScope = scope;
        return this;
    }

    public Config wechatScope(String scope) {
        mWechatScope = scope;
        return this;
    }

    public Config qqScope(String scope) {
        mQQScope = scope;
        return this;
    }

    public String getQQAppId() {
        return mQQAppId;
    }

    public String getQQAppKey() {
        return mQQAppKey;
    }

    public String getWechatAppId() {
        return mWechatAppId;
    }

    public String getWechatAppKey() {
        return mWechatAppKey;
    }

    public String getWeiboAppId() {
        return mWeiboAppId;
    }

    public String getWeiboAppKey() {
        return mWeiboAppKey;
    }

    public String getWeiboRedirectUrl() {
        return mWeiboRedirectUrl;
    }

    public String getWeiboScope() {
        return mWeiboScope;
    }

    public String getWechatScope() {
        return mWechatScope;
    }

    public String getQQScope() {
        return mQQScope;
    }
}
