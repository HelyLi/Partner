package com.hhly.partner.presentation.view.extension;

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
 * 2017/5/3
 */

public class ExtensionActivity extends BaseActivity implements IImmersiveApply {

    public static final String KEY_TYPE = "key_type";
    public static final String KEY_ID = "key_id";
    /**
     * 代理推广
     */
    public static final int EXTENSION_AGENT = 1;
    /**
     * H5平台推广
     */
    public static final int EXTENSION_H5 = 2;
    /**
     * android平台推广
     */
    public static final int EXTENSION_ANDROID = 3;
    /**
     * 从产品推广跳过来
     */
    public static final int EXTENSION_PRODUCT = 4;

    /**
     * @param context 上下文
     * @param type    来源  1,代理推广
     * @return
     */
    public static Intent getCallIntent(Context context, int type) {
        Intent intent = new Intent(context, ExtensionActivity.class);
        intent.putExtra(KEY_TYPE, type);
        return intent;
    }

    /**
     * @param context 上下文
     * @param type    来源  1,代理推广  2,H5  3,Android  4,从产品跳过来
     * @return
     */
    public static Intent getCallIntent(Context context, int type, int id) {
        Intent intent = new Intent(context, ExtensionActivity.class);
        intent.putExtra(KEY_TYPE, type);
        intent.putExtra(KEY_ID, id);
        return intent;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mToolbarHelper.updateAlpha(1);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int type = getIntent().getIntExtra(KEY_TYPE, EXTENSION_AGENT);
        int id = getIntent().getIntExtra(KEY_ID, 0);
        switch (type) {
            case EXTENSION_AGENT:
                setTitleForToolbar(getString(R.string.home_extension_agent));
                break;
            case EXTENSION_H5:
                setTitleForToolbar(getString(R.string.home_agent_extension_h5));
                break;
            case EXTENSION_PRODUCT:
                setTitleForToolbar(getString(R.string.home_agent_extension_member));
                break;
        }
        ExtensionFragment agentFragment = (ExtensionFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (agentFragment == null) {
            agentFragment = ExtensionFragment.newInstance(type, id);
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
