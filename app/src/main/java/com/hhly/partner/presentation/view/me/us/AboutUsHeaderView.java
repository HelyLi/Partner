package com.hhly.partner.presentation.view.me.us;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.TelephonyUtil;
import com.hhly.partner.presentation.view.me.us.update.VersionUpdateHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 关于我们headView
 * Created by dell on 2017/5/17.
 */

public class AboutUsHeaderView extends FrameLayout {
    @BindView(R.id.version_tv)
    TextView mVersionTv;

    public AboutUsHeaderView(@NonNull Context context) {
        this(context, null);
    }

    public AboutUsHeaderView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AboutUsHeaderView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.about_us_header, null);
        ButterKnife.bind(this, contentView);
        mVersionTv.setText(context.getString(R.string.partner_version, TelephonyUtil.getAppVersion(context)));
        addView(contentView);
    }

    @OnClick(R.id.check_version_tv)
    public void onViewClicked() {
        VersionUpdateHelper.getInstance().checkUpdate(getContext(), true);
    }

}
