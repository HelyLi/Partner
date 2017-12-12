package com.hhly.partner.presentation.view.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by Simon on 2016/11/24.
 */

public class NestedSwipeRefreshLayout extends SwipeRefreshLayout {

    private int mLastX;

    private int mTouchSlop;

    public NestedSwipeRefreshLayout(Context context) {
        super(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    public NestedSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int currentX = (int) ev.getX();
                if (Math.abs(currentX - mLastX) > mTouchSlop) {
                    return false;
                }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }
}
