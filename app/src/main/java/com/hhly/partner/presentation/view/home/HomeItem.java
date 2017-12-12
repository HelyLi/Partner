package com.hhly.partner.presentation.view.home;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * description :
 * Created by Flynn
 * 2017/4/15
 */

public class HomeItem {

    @DrawableRes
    private int iconIdRes;
    @StringRes
    private int stringCnIdRes;
    @StringRes
    private int stringEnIdRes;

    public HomeItem(@DrawableRes int iconIdRes, @StringRes int stringCnIdRes, @StringRes int stringEnIdRes) {
        this.iconIdRes = iconIdRes;
        this.stringCnIdRes = stringCnIdRes;
        this.stringEnIdRes = stringEnIdRes;
    }

    public int getIconIdRes() {
        return iconIdRes;
    }

    public int getStringCnIdRes() {
        return stringCnIdRes;
    }

    public int getStringEnIdRes() {
        return stringEnIdRes;
    }
}
