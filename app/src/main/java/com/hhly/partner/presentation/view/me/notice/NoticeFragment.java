package com.hhly.partner.presentation.view.me.notice;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.user.NoticeResp;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.activity.ActivityDetailsActivity;
import com.hhly.partner.presentation.view.common.RecyclerViewListDivide;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;

import java.util.List;

import butterknife.BindView;

/**
 * 通知
 * Created by dell on 2017/5/2.
 */

public class NoticeFragment extends BaseFragment implements IImmersiveApply, NoticeContract.View
        , SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private BaseQuickAdapter mAdapter;
    private NoticeContract.Presenter mPresenter;

    public static NoticeFragment newInstance() {
        Bundle args = new Bundle();
        NoticeFragment fragment = new NoticeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new NoticePresenterImpl(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getContext(), R.color.color_e9e9e9,
                RecyclerViewListDivide.VERTICAL_LIST, 1));
        mAdapter = new NoticeAdapter();
        View emptyView = View.inflate(getActivity(), R.layout.widget_empty_view, null);
        mAdapter.setEmptyView(emptyView);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NoticeResp.DataBean.PagerBean.ListBean item =
                        (NoticeResp.DataBean.PagerBean.ListBean) adapter.getItem(position);
                ActivityCompat.startActivity(getContext(),
                        ActivityDetailsActivity.getCallIntent(getContext(), item.getId()), null);
            }
        });
        fetchData(false);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.getNotice();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notice;
    }

    @Override
    public void setPresenter(NoticeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getNoticeSuccess(List<NoticeResp.DataBean.PagerBean.ListBean> list, int totalPages) {
        mRefreshLayout.setRefreshing(false);
        if (currentPageIndex == 1) {
            mAdapter.setNewData(list);
        } else {
            mAdapter.getData().addAll(list);
        }
        mAdapter.setEnableLoadMore(currentPageIndex >= totalPages ? false : true);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void getNoticeFailure(String msg) {
        ToastUtil.showShort(getContext(), msg);
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
    public void onRefresh() {
        currentPageIndex = 1;
        fetchData(false);
    }

    @Override
    public void onLoadMoreRequested() {
        currentPageIndex++;
        fetchData(true);
    }
}
