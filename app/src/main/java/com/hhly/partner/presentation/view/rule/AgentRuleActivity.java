package com.hhly.partner.presentation.view.rule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.view.BaseActivity;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;

/**
 * description : 代理规则 && 产品推广界面
 * Created by Flynn
 * 2017/4/26
 */

public class AgentRuleActivity extends BaseActivity implements IImmersiveApply {

    public static final int RULE = 1;
    public static final int EXTENSION = 2;
    public static final String KEY = "key";


    public static Intent getCallIntent(Context context, int type) {
        Intent intent = new Intent(context, AgentRuleActivity.class);
        intent.putExtra(KEY, type);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AgentRuleFragment fragment = (AgentRuleFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment == null) {
            fragment = AgentRuleFragment.newInstance(getIntent().getIntExtra(KEY, RULE));
            ActivityUtil.addFragment(getSupportFragmentManager(), fragment, R.id.content_container);
        }
        switch (getIntent().getIntExtra(KEY, RULE)) {
            case RULE:
                if (mToolbarHelper != null) {
                    mToolbarHelper.setTitle(getString(R.string.home_agent_rule));
                }
                break;
            case EXTENSION:
                if (mToolbarHelper != null) {
                    mToolbarHelper.setTitle(getString(R.string.home_agent_extension));
                }
                break;
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
