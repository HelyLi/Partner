package com.hhly.partner.presentation.view.rule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import com.hhly.partner.R;
import com.hhly.partner.presentation.view.BaseActivity;
import com.hhly.partner.presentation.view.common.CommonPageAdapter;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;
import com.hhly.partner.presentation.view.web.WebFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/5/3
 */

public class PartnerAgentActivity extends BaseActivity implements IImmersiveApply, ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {


    public final static String KEY = "key";
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private List<Fragment> mFragmentList;
    private static final int[] TAB_TEXT = {
            R.string.home_agent_growup,
            R.string.home_agent_income};

    public static Intent getCallIntent(Context context, int position) {
        Intent intent = new Intent(context, PartnerAgentActivity.class);
        intent.putExtra(KEY, position);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentList = new ArrayList<>();
        for (int i = 0; i < TAB_TEXT.length; i++) {
            mFragmentList.add(WebFragment.newInstance(i));
        }
        CommonPageAdapter adapter = new CommonPageAdapter(getSupportFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(5);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < TAB_TEXT.length; i++) {
            mTabLayout.getTabAt(i).setText(TAB_TEXT[i]);
        }
        mViewPager.addOnPageChangeListener(this);
        int position = getIntent().getIntExtra(KEY, 0);
        mViewPager.setCurrentItem(position, false);
        mTabLayout.addOnTabSelectedListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_partner_agent;
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        //        if (mToolbarHelper != null) {
        //            mToolbarHelper.setTitle(getString(TAB_TEXT[position]));
        //        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mViewPager.setCurrentItem(tab.getPosition(), false);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
