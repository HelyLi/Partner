package com.hhly.partner.presentation.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by dell on 2017/5/9.
 */

public class ParentRecyclerView extends RecyclerView {
    private int mLastTouchX;    //按下时 的X坐标
    private int mLastTouchY;    //按下时 的Y坐标
    private RecyclerView mChildRecyclerView;

    public ParentRecyclerView(Context context) {
        super(context);
    }

    public ParentRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ParentRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setChildRecylerView(RecyclerView childRecyclerView) {
        mChildRecyclerView = childRecyclerView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        boolean isVerticalScroll = false;
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastTouchX = (int) e.getX();
                mLastTouchY = (int) e.getY();
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                int curX = (int) e.getX();
                int curY = (int) e.getY();
                if (Math.abs(curX - mLastTouchX) < Math.abs(curY - mLastTouchY)) {
                    isVerticalScroll = true;
                }
                break;
        }
        mLastTouchX = (int) e.getX();
        mLastTouchY = (int) e.getY();
        if (!isVerticalScroll && mChildRecyclerView != null) {
            return mChildRecyclerView.dispatchTouchEvent(e);
        } else {
            return super.onTouchEvent(e);
        }
    }
}
