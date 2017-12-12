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
import com.hhly.partner.data.net.protocol.proxy.RechargeByUnderAgentsDetailResp;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.widget.RecycleViewItemDivider;

import java.util.List;

/**
 * description : 付费次数 ==> 我的代理
 * Created by Flynn
 * 2017/4/24
 */

public class PayAgentFragment extends BaseFragment implements PayAgentContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.line)
    TextView mLine;

    private PayAgentContract.Presenter mPresenter;

    /**
     * 默认传all , 日:DAY  周:WEEK   月:MONTH
     */
    private String mCurrentFlag = "ALL";
    private PayAgentAdapter mAdapter;
    private PopupWindow mPopupWindow;
    private int pageNo = 1;

    public static PayAgentFragment newInstance() {
        Bundle args = new Bundle();
        PayAgentFragment fragment = new PayAgentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new PayAgentPresenter(this);
        setHasOptionsMenu(true);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mRefreshLayout.setOnRefreshListener(this);
        initRecycleView();
        fetchData(false);
    }

    private void initRecycleView() {
        mAdapter = new PayAgentAdapter(getActivity(), null);
        View view = View.inflate(getActivity(), R.layout.widget_empty_view, null);
        mAdapter.setEmptyView(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecycleViewItemDivider().setDividerWith(DisplayUtil.dip2px(getActivity(), 1)).setDividerColor(0xFFf5f5f5));
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.getRechargeByUnderAgentsDetail(mCurrentFlag, pageNo);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_agent_recycle_view;
    }

    @Override
    public void setPresenter(PayAgentContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        fetchData(false);
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getRechargeByUnderAgentsDetail(mCurrentFlag, pageNo);
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
            totalMoney.setText(getString(R.string.home_member_day));
            registerTime.setText(getString(R.string.home_member_week));
            subProxy.setText(getString(R.string.home_member_month));
            totalMoney.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickPopWindow(1);
                }
            });
            registerTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickPopWindow(2);
                }
            });
            subProxy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickPopWindow(3);
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
        switch (_orderBy) {
            case 1:
                mCurrentFlag = "DAY";
                break;
            case 2:
                mCurrentFlag = "WEEK";
                break;
            case 3:
                mCurrentFlag = "MOUTH";
                break;
        }
        onRefresh();
        mPopupWindow.dismiss();
    }


    @Override
    public void getRechargeByUnderAgentsDetailSuccess(List<RechargeByUnderAgentsDetailResp.DataBean> list) {
        if (pageNo == 1) {//onRefresh
            mRefreshLayout.setRefreshing(false);
            mAdapter.setNewData(list);
            mAdapter.setEnableLoadMore(true);
            //将列表拉到第一个元素
            if (list != null && list.size() > 0 && mRecyclerView != null) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        } else {//
            if (list == null || list.size() == 0) {
//                mAdapter.setEnableLoadMore(false);
                mRefreshLayout.setEnabled(true);
                mAdapter.loadMoreComplete();
                mAdapter.loadMoreEnd(false);
                return;
            }
            mAdapter.addData(list);
            mAdapter.loadMoreComplete();
            mRefreshLayout.setEnabled(true);
        }
        pageNo++;
    }

    @Override
    public void getRechargeByUnderAgentsDetailFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showShort(getActivity(), msg);
    }
}
