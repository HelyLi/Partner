package com.hhly.partner.presentation.view.property.withdraw;

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
 * 2017/4/28
 */

public class WithdrawActivity extends BaseActivity implements IImmersiveApply {

    public static Intent getCallIntent(Context context) {
        Intent intent = new Intent(context, WithdrawActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WithdrawFragment fragment = (WithdrawFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = WithdrawFragment.newInstance();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        WithdrawFragment fragment = (WithdrawFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
