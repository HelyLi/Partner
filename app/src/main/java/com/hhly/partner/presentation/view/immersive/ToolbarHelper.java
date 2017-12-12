package com.hhly.partner.presentation.view.immersive;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hhly.partner.R;


/**
 * Created by Simon on 16/9/7.
 */
public class ToolbarHelper {
    private static final String EXTRA_TOOLBAR_ALPHA = "com.hhly.partner_toolbar_alpha";

    private Toolbar mToolbar;
    private TextView mTextView;
    private IImmersiveApply mImmersiveApply;

    private Context mContext;

    private float mCurrentAlpha = 1.0f;

    public ToolbarHelper () {
        SCROLL_MAX = 400;
    }

    public int toobarHeight;

    private ViewGroup mContentView;

    public void attach (@NonNull IImmersiveApply immersiveApply, View view) {
        this.mImmersiveApply = immersiveApply;
        if (mImmersiveApply instanceof AppCompatActivity) {
            mContext = (Context) mImmersiveApply;
        } else if (immersiveApply instanceof Fragment) {
            mContext = ((Fragment)mImmersiveApply).getActivity();
        }

        View child = view.findViewById(R.id.toolBar);
        if (child != null) {
            mToolbar = (Toolbar) child;
            mToolbar.setNavigationIcon(R.drawable.ic_back);
        }
//        mToolbar.setOnMenuItemClickListener();
        child = view.findViewById(R.id.toolbar_title_tv);
        if (child != null) {
            mTextView = (TextView) child;
        }

        if (mTextView != null) {
            if (mImmersiveApply instanceof AppCompatActivity) {
                mTextView.setText(((AppCompatActivity) mImmersiveApply).getTitle());
            } else if (immersiveApply instanceof Fragment) {
                mTextView.setText(((Fragment) immersiveApply).getActivity().getTitle());
            }
        }

        if (view instanceof ViewGroup) {
            mContentView = (ViewGroup) view;
        }

        update();

        mCurrentAlpha = mImmersiveApply.initAlpha();

        updateAlpha(mCurrentAlpha);

    }

    public void onSave (Bundle outState) {
        if (outState != null) {
            outState.putFloat(EXTRA_TOOLBAR_ALPHA, mCurrentAlpha);
        }
    }

    public void onRestore (Bundle saveState) {
        if (saveState != null) {
            mCurrentAlpha = saveState.getFloat(EXTRA_TOOLBAR_ALPHA, mImmersiveApply.initAlpha());
            updateAlpha(mCurrentAlpha);
        }
    }

    public void detach() {
        mToolbar = null;
    }

    public void setTitle (CharSequence charSequence) {
        if (mTextView != null) {
            mTextView.setText(charSequence);
        }
    }

    public void update () {
        if (mToolbar == null) {
            return;
        }
        if (mImmersiveApply.applyImmersive() && hasKitkat()) {
            ViewGroup.LayoutParams lp = mToolbar.getLayoutParams();
            mToolbar.setPadding(0, getStatusBarHeight(mContext), 0, 0);
            if (lp instanceof ViewGroup.MarginLayoutParams) {
                lp.height = getStatusBarHeight(mContext) + getToolbarHeight(mContext);
                mToolbar.setLayoutParams(lp);
                toobarHeight = lp.height;
            }
//            mToolbar.setBackgroundColor(mContext.getResources().getColor(R.color.color_3c4444));

        } else {
            ViewGroup.LayoutParams lp = mToolbar.getLayoutParams();
            mToolbar.setPadding(0, 0, 0, 0);
            if (lp instanceof ViewGroup.MarginLayoutParams) {
                lp.height = getToolbarHeight(mContext);
                mToolbar.setLayoutParams(lp);
                toobarHeight = lp.height;
            }
//            mToolbar.setBackgroundColor(mContext.getResources().getColor(R.color.));
            if (mContentView != null) {
                if (mImmersiveApply instanceof Activity) {
                    View parentView = mContentView.getChildAt(0);
                    if (parentView != null && hasIceCreamSandwich()) {
                        parentView.setFitsSystemWindows(true);
                    }
                    StatusBarCompat.compat((Activity) mImmersiveApply);
                } else if (mImmersiveApply instanceof Fragment) {
//                    ViewGroup viewGroup = (ViewGroup) ((Fragment) mImmersiveApply).getActivity().findViewById(android.R.id.content);
//                    View parentView = viewGroup.getChildAt(0);
                    View parentView = mContentView;
                    if (parentView != null && hasIceCreamSandwich()) {
                        parentView.setFitsSystemWindows(true);
                    }
                    StatusBarCompat.compat(((Fragment) mImmersiveApply).getActivity());
                }
            }
        }

        applyScroll(0);
    }

    public static int SCROLL_MAX = 1;
    public void applyScroll (int scrollY) {
        applyScroll(scrollY, SCROLL_MAX);
    }

    public void applyScroll (int scrollY, int max) {
        if (mImmersiveApply.applyScroll() && mImmersiveApply.applyImmersive() && hasKitkat()) {
            float f = scrollY * 1.0f / max;
            float a = Math.min(f, 1);
            updateAlpha(a);
        }
    }

    public void updateAlpha (float alpha) {
        mCurrentAlpha = alpha;
        updateToolbarAlpha(alpha);
        updateTextViewAlpha(alpha);
    }

    private void updateToolbarAlpha (float alpha) {
        if (hasKitkat()) {
            int alphaInt = (int) (0xff * alpha);
            Drawable background = mToolbar.getBackground();
            if (background != null) {
                background.setAlpha(alphaInt);
            }
//            mToolbar.setBackgroundColor(alphaInt << 24 & mContext.getResources().getColor(R.color.color_3c4444));
        }
    }

    private void updateTextViewAlpha (float alpha) {
        /*if (hasKitkat()) {
            if (mTextView != null) {
                mTextView.setAlpha(alpha);
            }
        }*/
    }

    public static boolean hasKitkat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean hasIceCreamSandwich() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getToolbarHeight(Context context) {
        int result = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.title_bar_height, tv, true)){
            result = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        } else if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            result = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return result;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

}
