package com.hhly.partner.presentation.view.member;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.view.BaseActivity;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;

/**
 * description :
 * Created by Flynn
 * 2017/4/24
 */

public class MemberActivity extends BaseActivity implements IImmersiveApply {

    public static final String FROM_TYPE = "from_type";

    public static Intent getCallIntent(Context context, int type) {
        Intent intent = new Intent(context, MemberActivity.class);
        intent.putExtra(FROM_TYPE, type);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MemberFragment fragment = (MemberFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = MemberFragment.newInstance(getIntent().getIntExtra(FROM_TYPE, 1));
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.include_toolbar_and_content;
    }

    @Override
    public boolean applyImmersive() {
        return true;
    }

    @Override
    public boolean applyScroll() {
        return false;
    }

    @Override
    public float initAlpha() {
        return 1;
    }
}
