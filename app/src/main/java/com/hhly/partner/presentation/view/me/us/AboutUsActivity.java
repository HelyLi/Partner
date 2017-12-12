package com.hhly.partner.presentation.view.me.us;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.view.BaseActivity;

/**
 * 关于我们
 * Created by dell on 2017/4/20.
 */

public class AboutUsActivity extends BaseActivity{
    public static Intent getCallIntent(Context context) {
        Intent intent=new Intent(context,AboutUsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AboutUsFragment fragment= (AboutUsFragment) getSupportFragmentManager().findFragmentById(R.id.content_container);
        if (fragment==null){
            fragment=AboutUsFragment.newInstance();
            ActivityUtil.addFragment(getSupportFragmentManager(),fragment,R.id.content_container);
        }
    }

}
