package com.hhly.partner.presentation.view.extension;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GetBannerListByUseridResp;
import com.hhly.partner.presentation.utils.CollectionUtil;
import com.hhly.partner.presentation.view.banner.ExtensionBannerHeader;
import com.hhly.partner.presentation.view.common.CommonPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 自定义推广页头部
 * Created by dell on 2017/5/3.
 */

public class CustomExtensionHeaderView extends FrameLayout {

    @BindView(R.id.banner_header)
    ExtensionBannerHeader mBannerHeader;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private List<Fragment> mFragmentList;

    private static final int[] TAB_TEXT = {R.string.home_agent_extension_private, R.string.home_agent_extension_recommend,
            R.string.home_agent_extension_offline, R.string.home_agent_extension_online};


    public CustomExtensionHeaderView(Context context, FragmentManager fragmentManager) {
        super(context);
        mFragmentManager = fragmentManager;
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.custom_extension_header_layout, null);
        ButterKnife.bind(this, view);
        addView(view);
        mFragmentList = new ArrayList<>();
        mFragmentList.add(CustomExtensionHeaderFragment.newInstance(CustomExtensionHeaderFragment.PRIVATE_GAME_TYPE));
        mFragmentList.add(CustomExtensionHeaderFragment.newInstance(CustomExtensionHeaderFragment.RECOMMEND_GAME_TYPE));
        mFragmentList.add(CustomExtensionHeaderFragment.newInstance(CustomExtensionHeaderFragment.OFFLINE_GAME_TYPE));
        mFragmentList.add(CustomExtensionHeaderFragment.newInstance(CustomExtensionHeaderFragment.ONLINE_GAME_TYPE));
        CommonPageAdapter pageAdapter = new CommonPageAdapter(mFragmentManager, mFragmentList);
        mViewPager.setAdapter(pageAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < TAB_TEXT.length; i++) {
            mTabLayout.getTabAt(i).setText(TAB_TEXT[i]);
        }
    }

    public void updateBanner(List<GetBannerListByUseridResp.DataBeanX.DataBean> list) {
        if (CollectionUtil.isNotEmpty(list)) {
            mBannerHeader.updateBanner(list, false);
        } else {//自己随便添加一个bean
            List<GetBannerListByUseridResp.DataBeanX.DataBean> customList = new ArrayList<>();
            customList.add(new GetBannerListByUseridResp.DataBeanX.DataBean());
            mBannerHeader.updateBanner(customList, true);
        }
    }

    public void startTurning() {
        mBannerHeader.startTurning();
    }

    public void stopTurning() {
        mBannerHeader.stopTurning();
    }

    public int getCurViewPagerIndex() {
        return mViewPager.getCurrentItem();
    }
}
