package com.hhly.partner.presentation.view.property.incomedetail;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.user.AccPriceResp;
import com.hhly.partner.presentation.utils.DateUtils;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.property.details.IncomeDetailsActivity;
import com.hhly.partner.presentation.view.property.details.IncomeDetailsFragment;
import com.hhly.partner.presentation.view.widget.RecycleViewItemDivider;
import com.orhanobut.logger.Logger;

import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;

/**
 * description :
 * Created by Flynn
 * 2017/4/25
 */

public class IncomeDetailFragment extends BaseFragment implements IncomeDetailContract.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private IncomeDetailContract.Presenter mPresenter;
    private int mPageNo = 1;
    private IncomeDetailAdapter mAdapter;

    public static IncomeDetailFragment newInstance() {
        Bundle args = new Bundle();
        IncomeDetailFragment fragment = new IncomeDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new IncomeDetailPresenter(this);
        mRefreshLayout.setOnRefreshListener(this);
        initRecycleView();
        fetchData(false);
    }

    private void initRecycleView() {
        mAdapter = new IncomeDetailAdapter(getActivity(), null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(new RecycleViewItemDivider().setDividerWith(DisplayUtil.dip2px(getActivity(), 12)).setDividerColor(0xFFF5F5F5));
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString(IncomeDetailsFragment.KEY_DATE, ((AccPriceResp.DataBeanX.DataBean) adapter.getData().get(position)).getCreatime());
                switch (view.getId()) {
                    case R.id.income_agent_money:
                        bundle.putInt(IncomeDetailsFragment.KEY_TYPE, 1);
                        break;
                    case R.id.income_member_money:
                        bundle.putInt(IncomeDetailsFragment.KEY_TYPE, 2);
                        break;
                }
                startActivity(IncomeDetailsActivity.getCallIntent(getActivity() ,bundle));
            }
        });
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        long eTime = System.currentTimeMillis() - ((mPageNo - 1L) * 7 * 24 * 60 * 60 * 1000);
        long sTime = eTime - 6 * 24 * 60 * 60 * 1000L;
        String startTime = null;
        String endTime = null;
        try {
            startTime = DateUtils.longToString(sTime);
            endTime = DateUtils.longToString(eTime);
        } catch (ParseException e) {
            Logger.e(e.getMessage());
        }
        Logger.d("Flynn  sTime = " + sTime + "  eTime = " + eTime + "  startTime = " + startTime + "   endTime = " + endTime);
        mPresenter.getAccPrice(startTime, endTime);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.include_refresh_recycle_view;
    }

    @Override
    public void setPresenter(IncomeDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRefresh() {
        mPageNo = 1;
        fetchData(false);
    }

    @Override
    public void getAccPriceSuccess(AccPriceResp.DataBeanX dataBean) {

        if (dataBean == null) {
            mRefreshLayout.setRefreshing(false);
            return;
        }
        if (mPageNo == 1) {//onRefresh
            mRefreshLayout.setRefreshing(false);
            Collections.sort(dataBean.getData(), mComparator);
            mAdapter.setNewData(dataBean.getData());
            mAdapter.setEnableLoadMore(true);
        } else {//
            if (dataBean.getData() == null || dataBean.getData().size() == 0) {
                mAdapter.setEnableLoadMore(false);
                mAdapter.loadMoreEnd(false);
                return;
            }
            Collections.sort(dataBean.getData(), mComparator);
            mAdapter.addData(dataBean.getData());
            mAdapter.loadMoreComplete();
            mRefreshLayout.setEnabled(true);
        }
        mPageNo++;
    }

    private Comparator<AccPriceResp.DataBeanX.DataBean> mComparator = new Comparator<AccPriceResp.DataBeanX.DataBean>() {
        @Override
        public int compare(AccPriceResp.DataBeanX.DataBean o1, AccPriceResp.DataBeanX.DataBean o2) {
            long o1Time = 0;
            long o2Time = 0;
            try {
                o1Time = DateUtils.stringToLong(o1.getCreatime(), "yyyy-MM-dd");
                o2Time = DateUtils.stringToLong(o2.getCreatime(), "yyyy-MM-dd");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return o1Time > o2Time ? -1 : (o1Time == o2Time ? 0 : 1);
        }
    };

    @Override
    public void getAccPriceFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void onLoadMoreRequested() {
        long eTime = System.currentTimeMillis() - ((mPageNo - 1L) * 7 * 24 * 60 * 60 * 1000);
        long sTime = eTime - 6 * 24 * 60 * 60 * 1000L;
        String startTime = null;
        String endTime = null;
        try {
            startTime = DateUtils.longToString(sTime);
            endTime = DateUtils.longToString(eTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Logger.d("Flynn  sTime = " + sTime + "  eTime = " + eTime + "  startTime = " + startTime + "   endTime = " + endTime);
        mPresenter.getAccPrice(startTime, endTime);
    }
}
