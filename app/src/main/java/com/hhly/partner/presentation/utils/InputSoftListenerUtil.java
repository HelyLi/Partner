package com.hhly.partner.presentation.utils;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;

/**
 * description :
 * Created by Flynn
 * 2017/4/19
 */

public class InputSoftListenerUtil {

    //        public static void inputSoftShowLocation(@NonNull final View rootView, @NonNull final View showView, @Nullable final View scrollView) {
    //            rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
    //                private int[] location;
    //
    //                @Override
    //                public void onGlobalLayout() {
    //                    Rect rect = new Rect();
    //                    // 获取rootView在窗体的可视区域
    //                    rootView.getWindowVisibleDisplayFrame(rect);
    //                    // 获取rootView在窗体的不可视区域高度(被其他View遮挡的区域高度)
    //                    int rootInvisibleHeight = rootView.getRootView().getHeight() - rect.bottom;
    //                    // 键盘显示
    //                    if (rootInvisibleHeight > 100) {
    //                        //只记录btn_login位置未变化时的位置
    //                        if (location == null) {
    //                            location = new int[2];
    //                            // 获取btn_login在窗体的坐标
    //                            showView.getLocationInWindow(location);
    //                        }
    //                        // 计算ll_login滚动高度，使btn_login在可见区域
    //                        int scrollHeight = location[1] + showView.getHeight() - rect.bottom;
    //                        if (scrollHeight < 0) {
    //                            return;
    //                        }
    //                        if (scrollView != null) {
    //                            scrollView.scrollTo(0, 0);
    //                        }
    //                        rootView.scrollTo(0, scrollHeight);
    //                    } else {
    //                        // 键盘隐藏
    //                        rootView.scrollTo(0, 0);
    //                    }
    //                }
    //            });
    //        }

    public static void inputSoftShowLocation(@NonNull final View rootView, @NonNull final View showView, @Nullable final View scrollView) {
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            private int[] location;

            @Override
            public void onGlobalLayout() {
                final int softKeyboardHeight = 100;
                Rect rect = new Rect();
                // 获取rootView在窗体的可视区域
                rootView.getWindowVisibleDisplayFrame(rect);
                // 获取rootView在窗体的不可视区域高度(被其他View遮挡的区域高度)
                //                int rootInvisibleHeight = rootView.getRootView().getHeight() - rect.bottom;
                DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
                int heightDiff = rootView.getBottom() - rect.bottom;
                // 键盘显示
                if (heightDiff > softKeyboardHeight * dm.density) {
                    //只记录btn_login位置未变化时的位置
                    if (location == null) {
                        location = new int[2];
                        // 获取btn_login在窗体的坐标
                        showView.getLocationInWindow(location);
                    }
                    // 计算ll_login滚动高度，使btn_login在可见区域
                    int scrollHeight = location[1] + showView.getHeight() - rect.bottom;
                    if (scrollHeight < 0) {
                        return;
                    }
                    if (scrollView != null) {
                        scrollView.scrollTo(0, 0);
                    }
                    rootView.scrollTo(0, scrollHeight);
                } else {
                    // 键盘隐藏
                    rootView.scrollTo(0, 0);
                }
            }
        });
    }

    private static boolean keyboardShown(View rootView) {

        final int softKeyboardHeight = 100;
        Rect r = new Rect();
        rootView.getWindowVisibleDisplayFrame(r);
        DisplayMetrics dm = rootView.getResources().getDisplayMetrics();
        int heightDiff = rootView.getBottom() - r.bottom;
        return heightDiff > softKeyboardHeight * dm.density;
    }

}
