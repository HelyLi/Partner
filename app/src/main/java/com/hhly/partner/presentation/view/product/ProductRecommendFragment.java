package com.hhly.partner.presentation.view.product;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GameDataResp;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;

import butterknife.BindView;

import static com.hhly.partner.presentation.view.product.ProductRecommendAdapter.RECOMMEND_TYPE_LINEAR_DISPLAY;
import static com.hhly.partner.presentation.view.product.ProductRecommendAdapter.RECOMMEND_TYPE_TITLE_DISPLAY;

/**
 * 产品》推荐
 * Created by dell on 2017/4/21.
 */

public class ProductRecommendFragment extends BaseFragment implements ProductContract.RecommendView {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ProductRecommendAdapter mAdapter;
    private ProductContract.Presenter mPresenter;

    public static ProductRecommendFragment newInstance() {
        Bundle args = new Bundle();
        ProductRecommendFragment fragment = new ProductRecommendFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new ProductRecommendPresenterImpl(this);
        mAdapter = new ProductRecommendAdapter(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemType = mAdapter.getItemViewType(position);
                return RECOMMEND_TYPE_LINEAR_DISPLAY == itemType || RECOMMEND_TYPE_TITLE_DISPLAY == itemType ? 2 : 1;
            }
        });
        mRecyclerView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.color_fff));
        mRecyclerView.setPadding(0, 0, 0, DisplayUtil.dip2px(getContext(), 15));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new ProductRecommendItemDecoration());
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mPresenter.getGameData(0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.inside_recycler_view;
    }

    @Override
    public void setPresenter(ProductContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getGameDataSuccess(GameDataResp.DataBean dataBean) {
        hideRefresh();
        mAdapter.update(dataBean);
    }

    @Override
    public void getGameDataFailure(String msg) {
        hideRefresh();
        ToastUtil.showShort(getContext(), msg);
    }

    void hideRefresh() {
        if (getParentFragment() != null && getParentFragment() instanceof ProductManageFragment) {
            ((ProductManageFragment) getParentFragment()).hideRefresh();
        }
    }
}
