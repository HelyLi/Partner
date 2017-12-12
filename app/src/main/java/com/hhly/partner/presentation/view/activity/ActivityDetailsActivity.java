package com.hhly.partner.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.view.BaseActivity;

/**
 * 活动详情
 * Created by dell on 2017/4/25.
 */

public class ActivityDetailsActivity extends BaseActivity {
    public static Intent getCallIntent(Context context, int activityId) {
        Intent intent = new Intent(context, ActivityDetailsActivity.class);
        intent.putExtra("activityId", activityId);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailsFragment fragment = (ActivityDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = ActivityDetailsFragment.newInstance(getIntent().getIntExtra("activityId", 0));
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
    }
}
