package com.hhly.partner.presentation.view.property.incomedetail;

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
 * 2017/4/25
 */

public class IncomeDetailActivity extends BaseActivity implements IImmersiveApply {

    public static Intent getCallIntent(Context context) {
        Intent intent = new Intent(context, IncomeDetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IncomeDetailFragment fragment = (IncomeDetailFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = IncomeDetailFragment.newInstance();
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
