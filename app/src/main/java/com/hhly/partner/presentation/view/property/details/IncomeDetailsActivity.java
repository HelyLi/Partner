package com.hhly.partner.presentation.view.property.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.view.BaseActivity;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;

/**
 * description :收入明细 --> 收入详情
 * Created by Flynn
 * 2017/4/27
 */

public class IncomeDetailsActivity extends BaseActivity implements IImmersiveApply {


    public static Intent getCallIntent(Context context,Bundle bundle) {
        Intent intent = new Intent(context, IncomeDetailsActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (mToolbarHelper != null) {
            switch (bundle.getInt(IncomeDetailsFragment.KEY_TYPE, 1)) {
                case 1:
                    mToolbarHelper.setTitle(getString(R.string.home_property_income_detail_agent));
                    break;
                case 2:
                    mToolbarHelper.setTitle(getString(R.string.home_property_income_detail_member));
                    break;
            }
        }
        IncomeDetailsFragment fragment = (IncomeDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = IncomeDetailsFragment.newInstance(bundle);
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
