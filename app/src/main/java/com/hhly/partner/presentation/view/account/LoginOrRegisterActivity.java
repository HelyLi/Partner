package com.hhly.partner.presentation.view.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseActivity;
import com.hhly.partner.presentation.view.main.MainTabActivity;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by dell on 2017/4/13.
 */

public class LoginOrRegisterActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginOrRegisterFragment loginOrRegisterFragment = (LoginOrRegisterFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (loginOrRegisterFragment == null) {
            loginOrRegisterFragment = LoginOrRegisterFragment.newInstance();
            ActivityUtil.addFragment(getSupportFragmentManager(), loginOrRegisterFragment, R.id.content_container);
        }
    }

    public static Intent getCallIntent(Context context) {
        Intent intent = new Intent(context, LoginOrRegisterActivity.class);
        return intent;
    }

    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.showShort(this, R.string.partner_exit_app);
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
            //            Process.killProcess(Process.myPid());
            //            System.gc();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Logger.d("LoginOrRegisterActivity.onNewIntent");
        startActivity(MainTabActivity.getCallIntent(this));
        finish();
    }
}
