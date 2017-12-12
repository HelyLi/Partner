package com.hhly.partner.presentation.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.common.CommonPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 活动中心
 * Created by dell on 2017/4/12.
 */

public class ActivityCenterFragment extends BaseFragment{
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    private static final int[] TAB_TEXT = {R.string.activity_official, R.string.activity_teamwork};
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private List<Fragment> mFragmentList;

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            mTabLayout.setPadding(0, DisplayUtil.getStatusBarHeight(getContext())+mTabLayout.getPaddingTop(),0,0);
        }
        mFragmentList=new ArrayList<>();
//        mFragmentList.add(OfficialActivityFragment.newInstance());
        mFragmentList.add(CollaborateActivityFragment.newInstance(2));
        mFragmentList.add(CollaborateActivityFragment.newInstance(3));
        CommonPageAdapter adapter=new CommonPageAdapter(getFragmentManager(),mFragmentList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.getTabAt(0).setText(TAB_TEXT[0]).select();
        mTabLayout.getTabAt(1).setText(TAB_TEXT[1]);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_activity_center;
    }




}
