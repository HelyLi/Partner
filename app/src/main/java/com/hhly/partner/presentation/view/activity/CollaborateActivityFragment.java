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
import com.hhly.partner.data.net.protocol.user.NoticeResp;
import com.hhly.partner.presentation.view.BaseFragment;
import com.orhanobut.logger.Logger;

import java.util.List;

import butterknife.BindView;

/**
 * description
 * Created by dell on 2017/4/14.
 */

public class CollaborateActivityFragment extends BaseFragment implements ActivityCenterContract.CollaborateActivityView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private BaseQuickAdapter mAdapter;
    protected ActivityCenterContract.CollaborateActivityPresenter mPresenter;

    public static final String KEY_TYPE = "key_type";

    public static CollaborateActivityFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(KEY_TYPE, type);
        CollaborateActivityFragment fragment = new CollaborateActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new CollaborateActivityPresenterImpl(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new CollaborateActivityAdapter();
        View emptyView = View.inflate(getActivity(), R.layout.widget_empty_view, null);
        mAdapter.setEmptyView(emptyView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NoticeResp.DataBean.PagerBean.ListBean bean = (NoticeResp.DataBean.PagerBean.ListBean) adapter.getItem(position);
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
        mPresenter.getActivities(getArguments().getInt(KEY_TYPE, 2));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.include_refresh_recycle_view;
    }

    @Override
    public void setPresenter(ActivityCenterContract.CollaborateActivityPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getActivitiesSuccess(List<NoticeResp.DataBean.PagerBean.ListBean> respList) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.loadMoreComplete();
        mAdapter.setNewData(respList);
        Logger.d("getActivitiesSuccess");
    }

    @Override
    public void getActivitiesFailure() {
        mRefreshLayout.setRefreshing(false);
        mAdapter.loadMoreComplete();
        Logger.d("getActivitiesFailure");
    }

    @Override
    public void onRefresh() {
        fetchData(false);
    }
}
