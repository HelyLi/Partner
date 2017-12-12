package com.hhly.partner.presentation.view.agent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.PopupWindow;
import android.widget.TextView;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.proxy.MyProxyDataResp;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/15
 */

public class AgentFragment extends BaseFragment implements AgentContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.line)
    TextView mLine;
    private AgentContract.Presenter mPresenter;
    private AgentAdapter mAdapter;
    private int orderBy = 0;
    private int pageNo = 1;
    private PopupWindow mPopupWindow;

    public static AgentFragment newInstance() {
        Bundle args = new Bundle();
        AgentFragment fragment = new AgentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new AgentPresenter(this);
        setHasOptionsMenu(true);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mRefreshLayout.setOnRefreshListener(this);
        initRecycleView();
        fetchData(false);
    }

    private void initRecycleView() {
        mAdapter = new AgentAdapter(getActivity(), null);
        View view = View.inflate(getActivity(), R.layout.widget_empty_view, null);
        mAdapter.setEmptyView(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.getMyProxyData(orderBy, pageNo);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_agent_recycle_view;
    }

    @Override
    public void setPresenter(AgentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        fetchData(false);
    }

    @Override
    public void getMyProxyDataSuccess(List<MyProxyDataResp.DataBean> mData) {
        if (pageNo == 1) {//onRefresh
            mRefreshLayout.setRefreshing(false);
            mAdapter.setNewData(mData);
            mAdapter.setEnableLoadMore(true);
            //将列表拉到第一个元素
            if (mData != null && mData.size() > 0 && mRecyclerView != null) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        } else {//
            if (mData == null || mData.size() == 0) {
                mRefreshLayout.setEnabled(true);
                mAdapter.loadMoreComplete();
//                mAdapter.setEnableLoadMore(false);
                mAdapter.loadMoreEnd(false);
                return;
            }
            mAdapter.addData(mData);
            mAdapter.loadMoreComplete();
            mRefreshLayout.setEnabled(true);
        }
        pageNo++;
    }

    @Override
    public void getMyProxyDataFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getMyProxyData(orderBy, pageNo);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.menu);
        item.setIcon(R.drawable.ic_my_proxy_setting);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu) {

            if (mPopupWindow != null && mPopupWindow.isShowing()) {
                mPopupWindow.dismiss();
            } else {
                getPopupWindow().showAsDropDown(mLine);
            }
        }
        return super.onOptionsItemSelected(item);
    }


    private PopupWindow getPopupWindow() {
        if (mPopupWindow == null) {
            View view = View.inflate(getActivity(), R.layout.pop_agent_setting, null);
            TextView totalMoney = (TextView) view.findViewById(R.id.total_money);
            TextView registerTime = (TextView) view.findViewById(R.id.register_time);
            TextView subProxy = (TextView) view.findViewById(R.id.sub_proxy_tv);
            totalMoney.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickPopWindow(0);
                }
            });
            registerTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickPopWindow(1);
                }
            });
            subProxy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickPopWindow(2);
                }
            });
            mPopupWindow = new PopupWindow(view);
            mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setFocusable(true);
        }
        return mPopupWindow;
    }

    private void onClickPopWindow(int _orderBy) {
        orderBy = _orderBy;
        onRefresh();
        mPopupWindow.dismiss();
    }

}
