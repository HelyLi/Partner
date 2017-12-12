package com.hhly.partner.presentation.view.extension;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.view.BaseActivity;

/**
 * 自定义推广页
 * Created by dell on 2017/5/3.
 */

public class CustomExtensionActivity extends BaseActivity {
    public static Intent getCallIntent(Context context) {
        Intent intent = new Intent(context, CustomExtensionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomExtensionFragment customExtensionFragment = (CustomExtensionFragment)
                getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (customExtensionFragment == null) {
            customExtensionFragment = CustomExtensionFragment.newInstance();
            ActivityUtil.addFragment(getSupportFragmentManager(), customExtensionFragment,
                    R.id.content_container);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_container);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
