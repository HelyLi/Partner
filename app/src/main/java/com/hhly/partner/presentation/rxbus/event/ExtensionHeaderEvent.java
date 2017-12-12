package com.hhly.partner.presentation.rxbus.event;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.hhly.partner.presentation.view.extension.CustomExtensionHeaderFragment.OFFLINE_GAME_TYPE;
import static com.hhly.partner.presentation.view.extension.CustomExtensionHeaderFragment.ONLINE_GAME_TYPE;
import static com.hhly.partner.presentation.view.extension.CustomExtensionHeaderFragment.PRIVATE_GAME_TYPE;
import static com.hhly.partner.presentation.view.extension.CustomExtensionHeaderFragment.RECOMMEND_GAME_TYPE;

/**
 * 推广（独家、推荐、单机、网游Event）
 * Created by dell on 2017/4/24.
 */

public class ExtensionHeaderEvent {
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    @IntDef({PRIVATE_GAME_TYPE, RECOMMEND_GAME_TYPE, OFFLINE_GAME_TYPE, ONLINE_GAME_TYPE})
    public @interface ExtensionHeaderEventType {

    }

    private int mEventType;


    public ExtensionHeaderEvent(@ExtensionHeaderEventType int eventType) {
        this.mEventType = eventType;
    }

    public int getEventType() {
        return mEventType;
    }

    public void setEventType(int eventType) {
        mEventType = eventType;
    }
}
