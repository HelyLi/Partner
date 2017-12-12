package com.hhly.partner.presentation.view.product.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GameDataByNameResp;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.common.RecyclerViewListDivide;
import com.hhly.partner.presentation.view.product.ProductDetailsActivity;

import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;
import static com.hhly.partner.presentation.view.product.search.ProductSearchActivity.EXTENSION_EXTRA_KEY;
import static com.hhly.partner.presentation.view.product.search.ProductSearchActivity.TYPE_EXTRA_KEY;
import static com.hhly.partner.presentation.view.product.search.SearchType.TYPE_PRODUCT_SEARCH;

/**
 * 产品搜索结果结果
 * Created by dell on 2017/4/26.
 */

public class ProductSearchResultFragment extends BaseFragment implements ProductSearchContract.ProductSearchResultView
        , SwipeRefreshLayout.OnRefreshListener {
    private ProductSearchContract.ProductSearchResultPresenter mPresenter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ProductSearchResultAdapter mAdapter;
    private String mSearchKey;
    //是否是产品搜索(另一种是推广搜索)
    private boolean isProductSearch;
    //推广搜索时传递的extra
    private ExtensionExtraItem mExtensionExtraItem;

    public static ProductSearchResultFragment newInstance(int type, @Nullable ExtensionExtraItem item) {
        Bundle args = new Bundle();
        args.putInt(TYPE_EXTRA_KEY, type);
        args.putParcelable(EXTENSION_EXTRA_KEY, item);
        ProductSearchResultFragment fragment = new ProductSearchResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new ProductSearchResultPresenterImpl(this);
        mAdapter = new ProductSearchResultAdapter();
        final Bundle bundle = getArguments();
        isProductSearch = (bundle.getInt(TYPE_EXTRA_KEY) == TYPE_PRODUCT_SEARCH) ? true : false;
        View emptyView = LayoutInflater.from(getContext()).inflate(R.layout.widget_search_empty, null);
        mAdapter.setEmptyView(emptyView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GameDataByNameResp.DataBeanX.DataBean bean = (GameDataByNameResp.DataBeanX.DataBean) adapter.getItem(position);
                if (isProductSearch) {//由产品管理进入
                    //保存搜索记录
                    mPresenter.recordSearchBehavior(bean.getNAME(), bean.getID());
                    ActivityCompat.startActivity(getContext(), ProductDetailsActivity
                            .getCallIntent(getContext(), bean.getID(), bean.getNAME()), null);
                } else {//由自定义推广页进入
                    mExtensionExtraItem = bundle.getParcelable(EXTENSION_EXTRA_KEY);
                    if (mExtensionExtraItem == null) return;
                    showLoading();
                    mPresenter.addGameCustomization(mExtensionExtraItem.getModelId(), bean.getID(),
                            mExtensionExtraItem.getPromotionPosition());
                }
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new RecyclerViewListDivide(getContext(), R.color.color_e9e9e9,
                RecyclerViewListDivide.VERTICAL_LIST, 1));
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(this);
    }

    public void setSearchKey(String searchKey) {
        mSearchKey = searchKey;
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.searchProduct(mSearchKey);
    }

    @Override
    public void onRefresh() {
        fetchData(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.include_refresh_recycle_view;
    }


    @Override
    public void searchProductSuccess(List<GameDataByNameResp.DataBeanX.DataBean> list) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(list);
    }

    @Override
    public void searchProductFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setPresenter(ProductSearchContract.BaseSearchPresenter presenter) {
        mPresenter = (ProductSearchContract.ProductSearchResultPresenter) presenter;
    }

    @Override
    public void addGameCustomizationSuccess() {
        dismissLoading();
        getActivity().setResult(RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void addGameCustomizationFail(String msg) {
        dismissLoading();
        ToastUtil.showShort(getContext(), msg);
    }

}
