package com.hhly.partner.presentation.view.agent;

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
 * 2017/4/15
 */

public class AgentActivity extends BaseActivity implements IImmersiveApply {

    public static Intent getCallIntent(Context context) {
        Intent intent = new Intent(context, AgentActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AgentFragment agentFragment = (AgentFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (agentFragment == null) {
            agentFragment = AgentFragment.newInstance();
            ActivityUtil.addFragment(getSupportFragmentManager(), agentFragment, R.id.content_container);
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
