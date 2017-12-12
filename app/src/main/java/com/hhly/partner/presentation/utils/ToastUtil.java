package com.hhly.partner.presentation.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Flynn
 * 2017/4/14
 */

public class ToastUtil {
    private static Toast toast;

    /**
     * 短时间显示  Toast
     *
     * @param context  上下文
     * @param sequence 字符串
     */
    public static void showShort(Context context, CharSequence sequence) {

        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), sequence, Toast.LENGTH_SHORT);

        } else {
            toast.setText(sequence);
        }
        toast.show();

    }

    /**
     * 短时间显示Toast
     *
     * @param context context
     * @param message string resource
     */
    public static void showShort(Context context, int message) {
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context context
     * @param message 字符串
     */
    public static void showLong(Context context, CharSequence message) {
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context context
     * @param message string resource
     */
    public static void showLong(Context context, int message) {
        if (null == toast) {
            toast = Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_LONG);
            //    toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 自定义显示时间
     *
     * @param context  context
     * @param sequence string
     * @param duration How long to display the message.
     */
    public static void show(Context context, CharSequence sequence, int duration) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), sequence, duration);
        } else {
            toast.setText(sequence);
        }
        toast.show();

    }

    /**
     * 隐藏toast
     */
    public static void hideToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
        }
    }
}
