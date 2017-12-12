package com.hhly.partner.presentation.view.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.ActivityCompat;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.view.BaseActivity;

/**
 * description :
 * Created by dell on 2017/4/13.
 */

public class LoginActivity extends BaseActivity {

    public static Intent getCallIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();
            ActivityUtil.addFragment(getSupportFragmentManager(), loginFragment, R.id.content_container);
        }
    }
}
