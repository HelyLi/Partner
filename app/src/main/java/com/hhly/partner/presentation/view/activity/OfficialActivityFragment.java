package com.hhly.partner.presentation.view.activity;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GetIndexlbtResp;
import com.hhly.partner.presentation.view.BaseFragment;

import java.util.List;

import butterknife.BindView;

/**
 * 合作活动
 * Created by dell on 2017/4/14.
 */

public class OfficialActivityFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener
        , ActivityCenterContract.OfficialActivityView, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private BaseQuickAdapter mAdapter;
    private ActivityCenterContract.OfficialActivityPresenter mPresenter;

    public static OfficialActivityFragment newInstance() {
        Bundle args = new Bundle();
        OfficialActivityFragment fragment = new OfficialActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new OfficialActivityPresenterImpl(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new OfficialActivityAdapter();
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GetIndexlbtResp.DataBean.ListBean bean = (GetIndexlbtResp.DataBean.ListBean) adapter.getItem(position);
                ActivityCompat.startActivity(getContext(), ActivityDetailsActivity.getCallIntent(getContext(), bean.getId()), null);
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.getActivities(currentPageIndex);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.include_refresh_recycle_view;
    }

    @Override
    public void onRefresh() {
        currentPageIndex = 1;
        fetchData(false);
    }

    @Override
    public void onLoadMoreRequested() {
        currentPageIndex++;
        fetchData(true);
    }

    @Override
    public void setPresenter(ActivityCenterContract.OfficialActivityPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getActivitiesSuccess(List<GetIndexlbtResp.DataBean.ListBean> respList, int totalPages) {
        if (currentPageIndex == 1) {
            mAdapter.setNewData(respList);
        } else {
            mAdapter.getData().addAll(respList);
        }
        if (currentPageIndex >= totalPages) {
            mAdapter.setEnableLoadMore(false);
        } else {
            mAdapter.setEnableLoadMore(true);
        }
        mRefreshLayout.setRefreshing(false);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void getActivitiesFailure() {
        mRefreshLayout.setRefreshing(false);
        mAdapter.loadMoreComplete();
    }

}
