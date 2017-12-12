package com.hhly.partner.presentation.view.proxycount;

/**
 * description :
 * Created by Flynn
 * 2017/4/20
 */

public class ProxyItem {

    public ProxyItem(String name, Runnable action) {
        this.name = name;
        mAction = action;
    }

    private String name;
    private Runnable mAction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Runnable getAction() {
        return mAction;
    }

    public void setAction(Runnable action) {
        mAction = action;
    }
}
