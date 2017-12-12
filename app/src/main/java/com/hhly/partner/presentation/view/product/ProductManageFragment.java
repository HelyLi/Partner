package com.hhly.partner.presentation.view.product;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MenuItem;
import android.view.View;

import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GetIndexlbtResp;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.banner.ProductBannerHeader;
import com.hhly.partner.presentation.view.common.CommonPageAdapter;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;
import com.hhly.partner.presentation.view.product.search.ProductSearchActivity;
import com.hhly.partner.presentation.view.product.search.SearchType;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import com.orhanobut.logger.Logger;

/**
 * 产品管理
 * Created by dell on 2017/4/12.
 */

public class ProductManageFragment extends BaseFragment implements IImmersiveApply, ProductContract.BannerView, SwipeRefreshLayout.OnRefreshListener, AppBarLayout.OnOffsetChangedListener {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.banner_view)
    ProductBannerHeader mBannerView;
    @BindView(R.id.appBarLayout)
    AppBarLayout mAppBarLayout;
    private List<Fragment> mFragmentList;
    private ProductContract.BannerPresenter mProductBannerPresenter;

    //    private static final int[] TAB_TEXT = {R.string.partner_product_recommend, R.string.partner_product_action,
    //            R.string.partner_product_recreation, R.string.partner_product_adventure, R.string.partner_product_strategy
    //            , R.string.partner_product_role, R.string.partner_product_chess};
    private static final int[] TAB_TEXT = {R.string.partner_product_recommend, R.string.partner_product_strategy,
            R.string.partner_product_role, R.string.partner_product_shoot, R.string.partner_product_adventure
            , R.string.partner_product_action, R.string.partner_product_chess};

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mRefreshLayout.setOnRefreshListener(this);
        initToolBar();
        mProductBannerPresenter = new ProductBannerPresenterImpl(this);
        mFragmentList = new ArrayList<>();
        for (int i = 0; i < TAB_TEXT.length; i++) {
            if (i == 0) {
                mFragmentList.add(ProductRecommendFragment.newInstance());
            } else {
                mFragmentList.add(getProductCommonFragmentInstance(i));
            }
        }
        CommonPageAdapter adapter = new CommonPageAdapter(getChildFragmentManager(), mFragmentList);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < TAB_TEXT.length; i++) {
            mTabLayout.getTabAt(i).setText(TAB_TEXT[i]);
        }
        mAppBarLayout.addOnOffsetChangedListener(this);
        fetchData(false);
    }

    private void initToolBar() {
        mToolbarHelper.getToolbar().getMenu().clear();
        mToolbarHelper.getToolbar().inflateMenu(R.menu.product_menu);
        mToolbarHelper.getToolbar().getMenu().findItem(R.id.menu_product_search)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        ActivityCompat.startActivity(getContext(),
                                ProductSearchActivity.getCallIntent(getContext(), SearchType.TYPE_PRODUCT_SEARCH), null);
                        return true;
                    }
                });
    }

    private ProductCommonFragment getProductCommonFragmentInstance(int index) {
        int gameType = 0;
        switch (index) {
            case 1:
                gameType = 8001;//策略
                break;
            case 2:
                gameType = 8005;//角色
                break;
            case 3:
                gameType = 8006;//射击
                break;
            case 4:
                gameType = 8012;//冒险
                break;
            case 5:
                gameType = 8014;//动作
                break;
            case 6:
                gameType = 8015;//棋牌
                break;
        }
        return ProductCommonFragment.newInstance(gameType);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mProductBannerPresenter.getBannerInfo();
        //        fetchChildData();
    }

    private void fetchChildData() {
        int position = mViewPager.getCurrentItem();
        //        BaseFragment fragment = (BaseFragment) mFragmentList.get(position);

        FragmentStatePagerAdapter f = (FragmentStatePagerAdapter) mViewPager.getAdapter();
        BaseFragment fragment = (BaseFragment) f.instantiateItem(mViewPager, position);
        Logger.d(fragment);

        if (fragment instanceof ProductRecommendFragment) {
            ((ProductRecommendFragment) fragment).fetchData(false);
        } else if (fragment instanceof ProductCommonFragment) {
            ((ProductCommonFragment) fragment).fetchData(false);
        }
    }

    @Override
    public void getBannerInfoSuccess(List<GetIndexlbtResp.DataBean.ListBean> list) {
        mRefreshLayout.setRefreshing(false);
        if (list != null && !list.isEmpty()) {
            mBannerView.updateBanner(list);
            mBannerView.startTurning();
        }
    }

    @Override
    public void getBannerInfoFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showShort(getContext(), msg);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mToolbarHelper != null) {
            mToolbarHelper.getToolbar().setNavigationIcon(null);
            mToolbarHelper.setTitle(getString(R.string.partner_product_manage));
        }
        mBannerView.startTurning();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBannerView.stopTurning();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_manage;
    }


    @Override
    protected boolean enableSetActionBar() {
        return false;
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
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        fetchData(false);
        fetchChildData();
    }

    public void hideRefresh() {

        postDelay(new Runnable() {
            @Override
            public void run() {
                if (mRefreshLayout != null)
                    mRefreshLayout.setRefreshing(false);
            }
        }, 250);

    }

    private boolean isCanRefresh = true;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        isCanRefresh = verticalOffset == 0;
        int position = mViewPager.getCurrentItem();
//        BaseFragment fragment = (BaseFragment) mFragmentList.get(position);
        FragmentStatePagerAdapter f = (FragmentStatePagerAdapter) mViewPager.getAdapter();
        BaseFragment fragment = (BaseFragment) f.instantiateItem(mViewPager, position);
        if (fragment instanceof ProductRecommendFragment) {
            //            ((ProductRecommendFragment) fragment).fetchData(false);
            if (!mRefreshLayout.isRefreshing()) {
                mRefreshLayout.setEnabled(isCanRefresh);
            }
        } else if (fragment instanceof ProductCommonFragment) {
            mRefreshLayout.setEnabled(((ProductCommonFragment) fragment).isFirst() && isCanRefresh);
        }
        //        if (!mRefreshLayout.isRefreshing()) {
        //            mRefreshLayout.setEnabled(verticalOffset == 0 );
        //        }

    }

}
