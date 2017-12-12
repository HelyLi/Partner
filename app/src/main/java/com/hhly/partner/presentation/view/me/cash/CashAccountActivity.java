package com.hhly.partner.presentation.view.me.cash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.view.BaseActivity;

/**
 * 提现账户
 * Created by dell on 2017/4/17.
 */

public class CashAccountActivity extends BaseActivity {

    public static Intent getCallIntent(Context context) {
        Intent intent = new Intent(context, CashAccountActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CashAccountFragment cashAccountFragment = (CashAccountFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (cashAccountFragment == null) {
            cashAccountFragment = CashAccountFragment.newInstance();
            cashAccountFragment.setUserVisibleHint(true);
            ActivityUtil.addFragment(getSupportFragmentManager(), cashAccountFragment, R.id.content_container);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment!=null){
            fragment.onActivityResult(requestCode,resultCode,data);
        }
    }
}
