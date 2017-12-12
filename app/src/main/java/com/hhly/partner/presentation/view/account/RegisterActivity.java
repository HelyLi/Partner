package com.hhly.partner.presentation.view.account;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.view.BaseActivity;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;

/**
 * Created by dell on 2017/4/13.
 */

public class RegisterActivity extends BaseActivity implements IImmersiveApply {
    public static Intent getCallIntent(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        return intent;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        RegisterFragment registerFragment = (RegisterFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (registerFragment == null) {
            registerFragment = RegisterFragment.newInstance();
            ActivityUtil.addFragment(getSupportFragmentManager(), registerFragment, R.id.content_container);
        }
    }

    public void doRegisterStepTwo(String phone) {
        RegisterStepTwoFragment stepTwoFragment = RegisterStepTwoFragment.newInstance(phone);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_container, stepTwoFragment);
        transaction.addToBackStack("stepTwoFragment");
        transaction.commitAllowingStateLoss();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
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
