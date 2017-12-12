package com.hhly.partner.presentation.view.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/5/5
 */

public class NoScrollRecycleView extends RecyclerView {
    private int mLastTouchX;    //按下时 的X坐标
    private int mLastTouchY;    //按下时 的Y坐标

    public NoScrollRecycleView(Context context) {
        super(context);
    }

    public NoScrollRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //        LayoutManager layoutManager = getLayoutManager();
        //        if (layoutManager instanceof LinearLayoutManager) {
        //            if (((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() == 0
        //                    && ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition() == getAdapter().getItemCount() - 1) {
        //                return false;
        //            }
        //        }


        Logger.d("Flynn == > onTouchEvent Action = " + e.getAction());
        return super.onTouchEvent(e);
        //        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
//        LayoutManager layoutManager = getLayoutManager();
//        if (layoutManager instanceof LinearLayoutManager) {
//            Logger.d("Flynn == > onInterceptTouchEvent 00 Action = " + e.getAction());
//            if (((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() == 0
//                    && ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition() == getAdapter().getItemCount() - 1) {
//                return false;
//            }
//        }

//        boolean isVerticalScroll = false;
//        mLastTouchX = (int) e.getX();
//        mLastTouchY = (int) e.getY();
//        switch (e.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                break;
//            case MotionEvent.ACTION_MOVE:
//            case MotionEvent.ACTION_UP:
//                int curX = (int) e.getX();
//                int curY = (int) e.getY();
//                if (Math.abs(curX - mLastTouchX) < Math.abs(curY - mLastTouchY)) {
//                    isVerticalScroll = true;
//                }
//                break;
//        }
//        if (isVerticalScroll) {
//            getParent().requestDisallowInterceptTouchEvent(false);
//            return ((ViewGroup) getParent()).onInterceptTouchEvent(e);
//        }
        return super.onInterceptTouchEvent(e);
    }
}
