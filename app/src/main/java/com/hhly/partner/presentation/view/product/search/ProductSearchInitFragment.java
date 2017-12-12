package com.hhly.partner.presentation.view.product.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GameDataResp;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.product.ProductDetailsActivity;

import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;
import static com.hhly.partner.presentation.view.product.search.ProductSearchActivity.EXTENSION_EXTRA_KEY;
import static com.hhly.partner.presentation.view.product.search.ProductSearchActivity.TYPE_EXTRA_KEY;
import static com.hhly.partner.presentation.view.product.search.SearchType.TYPE_PRODUCT_SEARCH;

/**
 * 产品搜索初始化界面
 * Created by dell on 2017/4/26.
 */

public class ProductSearchInitFragment extends BaseFragment implements ProductSearchContract.ProductSearchInitView
        , SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private BaseQuickAdapter mAdapter;
    private View mHeaderView;
    private ProductSearchContract.ProductSearchInitPresenter mPresenter;
    //是否是产品搜索(另一种是推广搜索)
    private boolean isProductSearch;
    //推广搜索时传递的extra
    private ExtensionExtraItem mExtensionExtraItem;

    public static ProductSearchInitFragment newInstance(int type, @Nullable ExtensionExtraItem item) {
        Bundle args = new Bundle();
        args.putInt(TYPE_EXTRA_KEY, type);
        args.putParcelable(EXTENSION_EXTRA_KEY, item);
        ProductSearchInitFragment fragment = new ProductSearchInitFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new ProductSearchInitPresenterImpl(this);
        mAdapter = new ProductSearchHotAdapter();
        Bundle bundle = getArguments();
        isProductSearch = (bundle.getInt(TYPE_EXTRA_KEY) == TYPE_PRODUCT_SEARCH) ? true : false;
        if (isProductSearch) {
            mHeaderView = new ProductSearchInitHeaderView(getContext());
        } else {
            mHeaderView = new ExtensionSearchInitHeaderView(getContext());
            mExtensionExtraItem = bundle.getParcelable(EXTENSION_EXTRA_KEY);
        }
        mAdapter.addHeaderView(mHeaderView);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GameDataResp.DataBean.HotGameBean gameBean = (GameDataResp.DataBean.HotGameBean) adapter.getData().get(position);
                if (isProductSearch) {
                    ActivityCompat.startActivity(getContext(), ProductDetailsActivity.getCallIntent(getContext(),
                            gameBean.getID(), gameBean.getNAME()), null);
                } else {
                    showLoading();
                    mPresenter.addGameCustomization(mExtensionExtraItem.getModelId(), gameBean.getID(), mExtensionExtraItem.getPromotionPosition());
                }
            }
        });
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.getHotProduct();
        if (isProductSearch) {
            mPresenter.getRecordSearchProduct();
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.include_refresh_recycle_view;
    }

    @Override
    public void getRecordSearchProductSuccess(List<String> list) {
        if (isProductSearch) {
            ((ProductSearchInitHeaderView) mHeaderView).updateRecordTags(list);
        }
    }

    @Override
    public void getHotProductSuccess(List<GameDataResp.DataBean.HotGameBean> list) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(list);
    }

    @Override
    public void getHotProductFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
        ToastUtil.showShort(getContext(), !TextUtils.isEmpty(msg) ? msg : getString(R.string.partner_request_error));
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

    @Override
    public void onRefresh() {
        fetchData(false);
    }

    @Override
    public void setPresenter(ProductSearchContract.BaseSearchPresenter presenter) {
        mPresenter = (ProductSearchContract.ProductSearchInitPresenter) presenter;
    }
}
