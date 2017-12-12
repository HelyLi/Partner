package com.hhly.partner.presentation.view.product.search;

/**
 * 独代产品item
 * Created by dell on 2017/5/2.
 */

public class PrivateProxyProductItem {
    private String mGameName;
    private int mGameDrawable;
    private String mJumpUrl;

    public PrivateProxyProductItem(String gameName, int gameDrawable,String url) {
        mGameName = gameName;
        mGameDrawable = gameDrawable;
        mJumpUrl=url;
    }

    public String getGameName() {
        return mGameName;
    }

    public void setGameName(String gameName) {
        mGameName = gameName;
    }

    public int getGameDrawable() {
        return mGameDrawable;
    }

    public void setGameDrawable(int gameDrawable) {
        mGameDrawable = gameDrawable;
    }

    public String getJumpUrl() {
        return mJumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        mJumpUrl = jumpUrl;
    }
}
