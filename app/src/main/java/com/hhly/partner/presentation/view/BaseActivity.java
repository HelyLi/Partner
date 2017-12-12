package com.hhly.partner.presentation.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.InputUtil;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;
import com.hhly.partner.presentation.view.immersive.ToolbarHelper;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description
 * Created by dell on 2017/4/11.
 */

public class BaseActivity extends RxAppCompatActivity {

    protected Unbinder mUnbinder;

    protected ToolbarHelper mToolbarHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d(this.getClass().getSimpleName());
        setContentView(getLayoutId());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);

            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }

        mUnbinder = ButterKnife.bind(this);

        if (this instanceof IImmersiveApply) {
            mToolbarHelper = new ToolbarHelper();
            mToolbarHelper.attach((IImmersiveApply) this, getContentView());
        }

        View view = getContentView().findViewById(R.id.toolBar);
        if (view != null) {
            Toolbar toolbar = (Toolbar) view;
            //            toolbar.setNavigationIcon(R.drawable.ic_navigation_up);
            setSupportActionBar(toolbar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle("");
                if (enableHomeAsUp()) {
                    actionBar.setDisplayShowHomeEnabled(true);
                    actionBar.setDisplayHomeAsUpEnabled(true);
                } else {
                    actionBar.setDisplayShowHomeEnabled(false);
                    actionBar.setDisplayHomeAsUpEnabled(false);
                }
            }
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            View focusView = null;
            if ((focusView = getCurrentFocus()) != null && enableHideInputSoft((int) ev.getX(), (int) ev.getY())) {
                Rect hitRect = new Rect();
                focusView.getHitRect(hitRect);
                if (!(focusView instanceof EditText) || !hitRect.contains((int) ev.getX(), (int) ev.getY())) {
                    InputUtil.hideInputSoftFromWindowMethod(getContext(), getCurrentFocus());
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    protected boolean enableHideInputSoft(int x, int y) {
        return true;
    }

    protected int getLayoutId() {
        return R.layout.activity_base_layout;
    }

    protected Context getContext() {
        return this;
    }

    protected View getContentView() {
        return getWindow().getDecorView().findViewById(android.R.id.content);
    }


    protected boolean enableFullScreen() {
        return false;
    }

    protected boolean enableHomeAsUp() {
        return true;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (enableFullScreen()) {
            if (hasFocus && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            } else {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        }
    }

    protected void setTitleForToolbar(String title) {
        if (mToolbarHelper != null) {
            mToolbarHelper.setTitle(title);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mToolbarHelper != null) {
            mToolbarHelper.detach();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //        StatisticalUtil.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //        StatisticalUtil.onPause(this);
    }

    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }
}
