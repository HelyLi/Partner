package com.hhly.partner.presentation.view.extension;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GetBannerListByUseridResp;
import com.hhly.partner.data.net.protocol.game.GetGameCustomizationResp;
import com.hhly.partner.presentation.rxbus.RxBus;
import com.hhly.partner.presentation.rxbus.event.ExtensionHeaderEvent;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;
import com.hhly.partner.presentation.view.product.search.ExtensionExtraItem;
import com.hhly.partner.presentation.view.product.search.ProductSearchActivity;
import com.hhly.partner.presentation.view.product.search.SearchType;

import java.util.List;

import butterknife.BindView;

import static com.hhly.partner.presentation.view.extension.CustomExtensionHeaderFragment.ADD_HEADER_GAME_REQUEST_CODE;

/**
 * 自定义推广页
 * Created by dell on 2017/5/3.
 */

public class CustomExtensionFragment extends BaseFragment implements IImmersiveApply,
        ExtensionContract.CustomExtensionView, SwipeRefreshLayout.OnRefreshListener,
        CustomExtensionHotAdapter.OnClickCallBack {
    //添加热门游戏requestCode
    public static final int ADD_HOT_GAME_REQUEST_CODE = 0x121;
    //添加banner requestCode
    public static final int ADD_BANNER_REQUEST_CODE = 0x122;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private CustomExtensionHeaderView mHeaderView;
    private CustomExtensionAdapter mAdapter;
    private ExtensionContract.CustomExtensionPresenter mPresenter;
    //banner的数据是否已经加载完成
    private boolean isBannerDataInited;
    //自定义的游戏数据是否已经加载完成
    private boolean isCustomGameDataInited;
    private int mCurrentScrolledY = 0;

    public static CustomExtensionFragment newInstance() {
        Bundle args = new Bundle();
        CustomExtensionFragment fragment = new CustomExtensionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new CustomExtensionPresenterImpl(this);
        mToolbarHelper.updateAlpha(0);
        mHeaderView = new CustomExtensionHeaderView(getContext(), getFragmentManager());
        mAdapter = new CustomExtensionAdapter(getContext());
        mAdapter.setCallBack(this);
        mAdapter.addHeaderView(mHeaderView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mCurrentScrolledY += dy;
                if (mCurrentScrolledY > DisplayUtil.dip2px(getContext(), 200)) {
                    mToolbarHelper.getToolbar().setNavigationIcon(R.drawable.ic_extension_go_back);
                } else {
                    mToolbarHelper.getToolbar().setNavigationIcon(R.drawable.ic_back);
                }
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(this);
        fetchData(false);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        isBannerDataInited = false;
        isCustomGameDataInited = false;
        //获取热门游戏
        mPresenter.getGameCustomization(15);
        mPresenter.getBannerListByUserId();
    }

    @Override
    public void onRefresh() {
        fetchData(false);
        postRxBusEvent();
    }

    /**
     * 让头部viewPager中当前的fragment刷新数据
     */
    private void postRxBusEvent() {
        int curPagerIndex = mHeaderView.getCurViewPagerIndex();
        switch (curPagerIndex) {
            case 0://独家
                RxBus.get().post(new ExtensionHeaderEvent(CustomExtensionHeaderFragment.PRIVATE_GAME_TYPE));
                break;
            case 1://推荐
                RxBus.get().post(new ExtensionHeaderEvent(CustomExtensionHeaderFragment.RECOMMEND_GAME_TYPE));
                break;
            case 2://单机
                RxBus.get().post(new ExtensionHeaderEvent(CustomExtensionHeaderFragment.OFFLINE_GAME_TYPE));
                break;
            case 3://网游
                RxBus.get().post(new ExtensionHeaderEvent(CustomExtensionHeaderFragment.ONLINE_GAME_TYPE));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mHeaderView.startTurning();
    }

    @Override
    public void onStop() {
        super.onStop();
        mHeaderView.stopTurning();
    }

    @Override
    public void addGame(int promotionPosition) {
        ExtensionExtraItem extraItem = new ExtensionExtraItem(15, promotionPosition);
        ActivityCompat.startActivityForResult(getActivity(), ProductSearchActivity.getCallIntent(getContext(),
                SearchType.TYPE_EXTENSION, extraItem), ADD_HOT_GAME_REQUEST_CODE, null);
    }

    @Override
    public void deleteGame(int gameId, int position) {
        mPresenter.deleteGameCustomization(15, gameId, position);
    }

    private void setRefreshCompleted() {
        if (isBannerDataInited && isCustomGameDataInited) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void setPresenter(ExtensionContract.BaseCustomExtensionPresenter presenter) {
        mPresenter = (ExtensionContract.CustomExtensionPresenter) presenter;
    }

    @Override
    public void deleteGameCustomizationSuccess(int position) {
        mAdapter.deleteHotGameItem(position);
    }

    @Override
    public void deleteGameCustomizationFailure(String msg) {
        ToastUtil.showShort(getContext(), msg);
    }

    @Override
    public void getGameCustomizationSuccess(List<GetGameCustomizationResp.DataBeanX.DataBean> list) {
        mAdapter.updateItems(list);
        isCustomGameDataInited = true;
        setRefreshCompleted();
    }

    @Override
    public void getGameCustomizationFailure(String msg) {
        isCustomGameDataInited = true;
        setRefreshCompleted();
        ToastUtil.showShort(getContext(), msg);
    }


    @Override
    public void getBannerListSuccess(List<GetBannerListByUseridResp.DataBeanX.DataBean> list) {
        mHeaderView.updateBanner(list);
        isBannerDataInited = true;
        setRefreshCompleted();
    }

    @Override
    public void getBannerListFail(String msg) {
        ToastUtil.showShort(getContext(), msg);
        isBannerDataInited = true;
        setRefreshCompleted();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_custom_extension;
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
        return 0.0f;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case ADD_HEADER_GAME_REQUEST_CODE://添加独家、推荐、单机、网游中的游戏
                    postRxBusEvent();
                    break;
                case ADD_HOT_GAME_REQUEST_CODE://添加热门游戏
                    isCustomGameDataInited = false;
                    mRefreshLayout.setRefreshing(true);
                    mPresenter.getGameCustomization(15);
                    break;
                case ADD_BANNER_REQUEST_CODE://添加banner
                    isBannerDataInited = false;
                    mRefreshLayout.setRefreshing(true);
                    mPresenter.getBannerListByUserId();
                    break;
            }
        }
    }

}
