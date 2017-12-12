package com.hhly.partner.presentation.view.member;

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
import com.hhly.partner.data.net.protocol.proxy.GetMembersRechargeSumResp;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.widget.RecycleViewItemDivider;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/24
 */

public class MemberFragment extends BaseFragment implements MemberContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.line)
    TextView mLine;

    private MemberContract.Presenter mPresenter;

    /**
     * 用于区分表示从哪个地方跳转过来 ,  从代理人数界面跳过来:1  从付费次数跳过来:3
     */
    private int mType = 1;
    /**
     * 从代理人数跳过来的时候 , 当前为0 , 日:1  周:2  月:3
     */
    private int mCurrentType;
    /**
     * 默认传all , 日:DAY  周:WEEK   月:MONTH
     */
    private String mCurrentFlag = "ALL";
    private MemberAdapter mAdapter;
    private PopupWindow mPopupWindow;
    private int pageNo = 1;

    public static MemberFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(MemberActivity.FROM_TYPE, type);
        MemberFragment fragment = new MemberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MemberPresenter(this);
        setHasOptionsMenu(true);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mType = getArguments().getInt(MemberActivity.FROM_TYPE, 1);
        mRefreshLayout.setOnRefreshListener(this);
        initRecycleView();
        fetchData(false);
    }

    private void initRecycleView() {
        mAdapter = new MemberAdapter(getActivity(), mType, null);
        View view = View.inflate(getActivity(), R.layout.widget_empty_view, null);
        mAdapter.setEmptyView(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mRecyclerView.addItemDecoration(new RecycleViewItemDivider().setDividerWith(DisplayUtil.dip2px(getActivity(), 12)).setDividerColor(0xFFf5f5f5));
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        getNetworkData();
    }

    private void getNetworkData() {
        if (mType == 1) {
            if (mCurrentType == 0) {//全部
                mPresenter.getMembersRechargeSum(pageNo);
            } else if (mCurrentType == 1) {//日
                mPresenter.getMemberDaySum(pageNo);
            } else if (mCurrentType == 2) {//周
                mPresenter.getMemberWeekSum(pageNo);
            } else if (mCurrentType == 3) {//月
                mPresenter.getMemberMonthSum(pageNo);
            }

        } else if (mType == 3) {
            mPresenter.getRechargeByMemberDetail(mCurrentFlag, pageNo);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_agent_recycle_view;
    }

    @Override
    public void setPresenter(MemberContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        fetchData(false);
    }

    @Override
    public void onLoadMoreRequested() {
        getNetworkData();
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
        mCurrentType = _orderBy;
        switch (_orderBy) {
            case 1:
                mCurrentFlag = "DAY";
                break;
            case 2:
                mCurrentFlag = "WEEK";
                break;
            case 3:
                mCurrentFlag = "MONTH";
                break;
        }
        onRefresh();
        mPopupWindow.dismiss();
    }

    @Override
    public void getMembersRechargeSumSuccess(List<GetMembersRechargeSumResp.DataBean> list) {
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
    public void getMembersRechargeSumFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showShort(getActivity(), msg);
    }
}
