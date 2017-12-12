package com.hhly.partner.presentation.view.property.details;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.user.AccPriceInfoResp;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/27
 */

public class IncomeDetailsFragment extends BaseFragment implements IncomeDetailsContract.View, SwipeRefreshLayout.OnRefreshListener {

    public static final String KEY_TYPE = "key_type";
    public static final String KEY_DATE = "key_date";
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    /**
     * 收入明细 :
     * <p>
     * 代理收入明细 1
     * 会员收入明细 2
     */
    private int mType = 1;
    private String mStartDate = "";

    private IncomeDetailsContract.Presenter mPresenter;

    private IncomeDetailsAdapter mAdapter;

    public static IncomeDetailsFragment newInstance(Bundle bundle) {
        IncomeDetailsFragment fragment = new IncomeDetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new IncomeDetailsPresenter(this);
        mRefreshLayout.setOnRefreshListener(this);
        initData();
        initRecycleView();
        fetchData(false);
    }

    private void initData() {
        mType = getArguments().getInt(KEY_TYPE, 1);
        mStartDate = getArguments().getString(KEY_DATE);
    }

    private void initRecycleView() {
        mAdapter = new IncomeDetailsAdapter(null);
        View view = View.inflate(getActivity(), R.layout.income_details_recycle_head_view, null);
        mAdapter.addHeaderView(view);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = DisplayUtil.dip2px(getActivity(), 40);
        view.setLayoutParams(lp);
        View emptyView = View.inflate(getActivity(), R.layout.widget_empty_view, null);
        mAdapter.setEmptyView(emptyView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.getAccPriceInfo(mType, mStartDate);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.include_refresh_recycle_view;
    }

    @Override
    public void setPresenter(IncomeDetailsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRefresh() {
        fetchData(false);
    }

    @Override
    public void getAccPriceInfoSuccess(List<AccPriceInfoResp.DataBeanX.DataBean> dataBean) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(dataBean);
    }

    @Override
    public void getAccPriceInfoFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showShort(getActivity(), msg);
    }
}
