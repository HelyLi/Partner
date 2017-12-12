package com.hhly.partner.presentation.view.extension;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.hhly.partner.R;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;

/**
 * 自定义推广添加banner footer
 * Created by dell on 2017/5/6.
 */

public class AddBannerFooterView extends FrameLayout {
    @BindView(R.id.confirm_btn)
    Button mConfirmBtn;
    @BindView(R.id.cancel_btn)
    Button mCancelBtn;
    private OnClickCallBack mOnClickCallBack;

    public AddBannerFooterView(@NonNull Context context) {
        this(context, null);
    }

    public AddBannerFooterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddBannerFooterView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.add_extension_banner_footer_view, null);
        ButterKnife.bind(this, view);
        RxView.clicks(mConfirmBtn).throttleFirst(300, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Object o) throws Exception {
                        if (mOnClickCallBack != null) {
                            mOnClickCallBack.onConfirmClick(mConfirmBtn);
                        }
                    }
                });
        RxView.clicks(mCancelBtn).throttleFirst(300, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@io.reactivex.annotations.NonNull Object o) throws Exception {
                        if (mOnClickCallBack != null) {
                            mOnClickCallBack.onCancelClick(mCancelBtn);
                        }
                    }
                });
        addView(view);
    }

    public void setOnClickCallBack(OnClickCallBack onClickCallBack) {
        mOnClickCallBack = onClickCallBack;
    }

    public interface OnClickCallBack {
        void onConfirmClick(View view);

        void onCancelClick(View view);
    }
}
