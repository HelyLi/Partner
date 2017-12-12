package com.hhly.partner.presentation.view.product.search;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.hhly.partner.R;

import static com.hhly.partner.application.App.mContext;

/**
 * 推广搜索初始化header
 * Created by dell on 2017/4/26.
 */

public class ExtensionSearchInitHeaderView extends FrameLayout {
    private LayoutInflater mLayoutInflater;

    public ExtensionSearchInitHeaderView(@NonNull Context context) {
        this(context, null);
    }

    public ExtensionSearchInitHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExtensionSearchInitHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext=context;
        mLayoutInflater = LayoutInflater.from(mContext);
        View headerView = mLayoutInflater.inflate(R.layout.extension_search_init_header_layout, null);
        addView(headerView);
    }
}
