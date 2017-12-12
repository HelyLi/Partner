package com.hhly.partner.presentation.view.property.transaction;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.user.WithdrawalInfoResp;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.view.BaseFragment;

/**
 * description :
 * Created by Flynn
 * 2017/4/24
 */

public class TransactionFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, TransactionContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private TextView mAccount;
    private TextView mAccountMoney;
    private TextView mTransactionTime;


    private int pageNo = 1;
    private TransactionAdapter mAdapter;
    private TransactionContract.Presenter mPresenter;

    public static TransactionFragment newInstance() {
        Bundle args = new Bundle();
        TransactionFragment fragment = new TransactionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new TransactionPresenter(this);
        mRefreshLayout.setOnRefreshListener(this);
        initRecycleView();
        fetchData(false);
    }

    private void initRecycleView() {
        mAdapter = new TransactionAdapter(null);
        View view = View.inflate(getActivity(), R.layout.transaction_recycle_head_view, null);
        mAccount = (TextView) view.findViewById(R.id.account);
        mAccountMoney = (TextView) view.findViewById(R.id.account_money);
        mTransactionTime = (TextView) view.findViewById(R.id.transaction_time);
        mAdapter.addHeaderView(view);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = DisplayUtil.dip2px(getActivity(), 180f);
        view.setLayoutParams(lp);
        mAccount.setText(Html.fromHtml(getString(R.string.home_transaction_account, "")));
        mAccountMoney.setText(Html.fromHtml(getString(R.string.home_transaction_account_money, "")));
        mTransactionTime.setText(Html.fromHtml(getString(R.string.home_transaction_last_time, "")));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.getWithdrawalInfo();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.include_refresh_recycle_view;
    }

    @Override
    public void onRefresh() {
        pageNo = 1;
        fetchData(false);
    }

    @Override
    public void setPresenter(TransactionContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getWithdrawalInfoSuccess(WithdrawalInfoResp.DataBeanX data) {
        mRefreshLayout.setRefreshing(false);
        if (data != null && data.getUserInfo() != null && data.getUserInfo().size() > 0) {
            mAccount.setText(Html.fromHtml(getString(R.string.home_transaction_account, data.getUserInfo().get(0).getPHONE())));
            mAccountMoney.setText(Html.fromHtml(getString(R.string.home_transaction_account_money, String.format("%.2f", data.getUserInfo().get(0).getAMOUNT()))));
            mTransactionTime.setText(Html.fromHtml(getString(R.string.home_transaction_last_time, data.getUserInfo().get(0).getAPPLICATION_TIME())));
        }
        if (data != null) {
            mAdapter.setNewData(data.getData());
        }
    }

    @Override
    public void getWithdrawalInfoFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
    }
}
