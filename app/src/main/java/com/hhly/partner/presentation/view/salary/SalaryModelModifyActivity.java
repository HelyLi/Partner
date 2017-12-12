package com.hhly.partner.presentation.view.salary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.view.BaseActivity;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;

/**
 * description
 * Created by dell on 2017/4/14.
 */

public class SalaryModelModifyActivity extends BaseActivity implements IImmersiveApply {


    /**
     * bundle.putInt(SalaryModelModifyFragment.KEY_TYPE, SalaryModelModifyFragment.REBATE);
     *
     * @param context 上下文
     * @param bundle  包含参数  必须要有 bundle.putInt(SalaryModelModifyFragment.KEY_TYPE, SalaryModelModifyFragment.REBATE)
     * @return Intent
     */
    public static Intent getCallIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SalaryModelModifyActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        SalaryModelModifyFragment salaryModelModifyFragment = (SalaryModelModifyFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (salaryModelModifyFragment == null) {
            salaryModelModifyFragment = SalaryModelModifyFragment.newInstance(getIntent().getExtras());
            ActivityUtil.addFragment(getSupportFragmentManager(), salaryModelModifyFragment, R.id.content_container);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null && mToolbarHelper != null) {
            int type = getIntent().getExtras().getInt(SalaryModelModifyFragment.KEY_TYPE, SalaryModelModifyFragment.REBATE);
            if (type == SalaryModelModifyFragment.REGISTER) {
                mToolbarHelper.setTitle(getString(R.string.salary_modify_model_select));
            } else {
                mToolbarHelper.setTitle(getString(R.string.salary_modify_model));
            }
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_salary_model_modify;
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
