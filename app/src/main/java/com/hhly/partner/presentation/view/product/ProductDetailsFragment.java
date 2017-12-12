package com.hhly.partner.presentation.view.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GameDataInfoResp;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;
import com.hhly.partner.presentation.view.rule.AgentRuleActivity;

import butterknife.BindView;

/**
 * 产品详情
 * Created by dell on 2017/4/25.
 */

public class ProductDetailsFragment extends BaseFragment implements IImmersiveApply, ProductContract.DetailsView
        , SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private BaseQuickAdapter mAdapter;
    private ProductContract.DetailsPresenter mPresenter;
    private ProductDetailsHeaderView mHeaderView;

    public static ProductDetailsFragment newInstance(int gameId, String titleName) {
        Bundle args = new Bundle();
        args.putInt("gameId", gameId);
        args.putString("titleName", titleName);
        ProductDetailsFragment fragment = new ProductDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        mToolbarHelper.setTitle(getArguments().getString("titleName"));
        new ProductDetailsPresenterImpl(this);
        mRefreshLayout.setOnRefreshListener(this);
        mHeaderView = new ProductDetailsHeaderView(getContext());
        mAdapter = new ProductDetailsAdapter();
        mAdapter.addHeaderView(mHeaderView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setBackgroundColor(getResources().getColor(R.color.color_fff));
        mRecyclerView.setAdapter(mAdapter);
        fetchData(false);
    }

    @Override
    public void onRefresh() {
        fetchData(false);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.getProductDetailsInfo(getArguments().getInt("gameId"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_details;
    }


    @Override
    public void setPresenter(ProductContract.DetailsPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getProductDetailsInfoSuccess(GameDataInfoResp.DataBeanX dataBean) {
        mRefreshLayout.setRefreshing(false);
        if (!dataBean.getData().isEmpty()) {
            mHeaderView.updateInfo(dataBean.getData().get(0));
        }
        if (!dataBean.getAmong().isEmpty()) {
            mAdapter.setNewData(dataBean.getAmong());
            ((ProductDetailsAdapter) mAdapter).addFooterView(dataBean);
        } else {
            ((ProductDetailsAdapter) mAdapter).showEmptyView(getContext());
        }
    }

    @Override
    public void getProductDetailsInfoFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showShort(getContext(), TextUtils.isEmpty(msg) ? getString(R.string.partner_request_error) : msg);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
        menu.findItem(R.id.menu).setTitle(getString(R.string.partner_home_item_proxy_rule));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu) {
            ActivityCompat.startActivity(getContext(), AgentRuleActivity.getCallIntent(getContext(),
                    AgentRuleActivity.RULE), null);
        }
        return super.onOptionsItemSelected(item);
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


}
