package com.hhly.partner.presentation.view.me.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.view.BaseActivity;

/**
 * 账户设置
 * Created by dell on 2017/4/17.
 */

public class AccountSettingActivity extends BaseActivity {

    public static final String KEY_TYPE = "key_type";

    public static Intent getCallIntent(Context context) {
        Intent intent = new Intent(context, AccountSettingActivity.class);
        return intent;
    }

    public static Intent getCallIntent(Context context, int type) {
        Intent intent = new Intent(context, AccountSettingActivity.class);
        intent.putExtra(KEY_TYPE, type);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra(KEY_TYPE, 0);

        if (type == 0) {

            AccountSettingFragment fragment = (AccountSettingFragment) (getSupportFragmentManager().findFragmentById(R.id.content_container));
            if (fragment == null) {
                fragment = AccountSettingFragment.newInstance();
                ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
            }
        } else if (type == 1) {
            SetPayPswFragment fragment = (SetPayPswFragment) (getSupportFragmentManager().findFragmentById(R.id.content_container));
            if (fragment == null) {
                fragment = SetPayPswFragment.newInstance();
                ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
            }
        }
    }

    /**
     * 跳转设置支付密码界面
     */
    public void startSetPayPswFragment() {
        SetPayPswFragment setPayPswFragment = SetPayPswFragment.newInstance();
        ActivityUtil.replaceFragment(getSupportFragmentManager(), setPayPswFragment, R.id.content_container, true);
    }

    /**
     * 跳转修改密码界面
     */
    public void startModifyPswFragment() {
        ModifyPswFragment modifyPswFragment = ModifyPswFragment.newInstance();
        ActivityUtil.replaceFragment(getSupportFragmentManager(), modifyPswFragment, R.id.content_container, true);
    }

    /**
     * 跳转修改支付密码界面
     */
    public void startModifyPayFragment() {
        ModifyPayPswFragment modifyPayPswFragment = ModifyPayPswFragment.newInstance();
        ActivityUtil.replaceFragment(getSupportFragmentManager(), modifyPayPswFragment, R.id.content_container, true);
    }
}
