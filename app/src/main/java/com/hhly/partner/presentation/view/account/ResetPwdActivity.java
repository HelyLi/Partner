package com.hhly.partner.presentation.view.account;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.view.BaseActivity;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;

/**
 * description :
 * Created by Flynn
 * 2017/4/14
 */

public class ResetPwdActivity extends BaseActivity implements IImmersiveApply {

    public static Intent getCallIntent(Context context) {
        Intent intent = new Intent(context, ResetPwdActivity.class);
        return intent;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ResetPwdFragment resetPwdFragment = (ResetPwdFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (resetPwdFragment == null) {
            resetPwdFragment = ResetPwdFragment.newInstance();
            ActivityUtil.addFragment(getSupportFragmentManager(), resetPwdFragment, R.id.content_container);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_pwd;
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
