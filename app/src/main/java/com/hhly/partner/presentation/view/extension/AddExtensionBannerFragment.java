package com.hhly.partner.presentation.view.extension;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GetBannerListResp;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.common.RecyclerViewGridDivide;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;

import java.util.List;

import butterknife.BindView;

import static com.chad.library.adapter.base.BaseQuickAdapter.FOOTER_VIEW;

/**
 * 添加自定义推广页bannerFragment
 * Created by dell on 2017/5/5.
 */

public class AddExtensionBannerFragment extends BaseFragment implements IImmersiveApply,
        ExtensionContract.AddBannerView, SwipeRefreshLayout.OnRefreshListener, AddBannerFooterView.OnClickCallBack {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private AddExtensionBannerAdapter mAdapter;
    private ExtensionContract.AddBannerPresenter mPresenter;
    private AddBannerFooterView mFooterView;

    public static AddExtensionBannerFragment newInstance() {
        Bundle args = new Bundle();
        AddExtensionBannerFragment fragment = new AddExtensionBannerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new AddBannerPresenterImpl(this);
        mRefreshLayout.setOnRefreshListener(this);
        mAdapter = new AddExtensionBannerAdapter();
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GetBannerListResp.DataBeanX.DataBean item = (GetBannerListResp.DataBeanX.DataBean) adapter.getItem(position);
                if (mAdapter.getSelectedCount() == 3 && !item.isSelected()) {
                    ToastUtil.showShort(getContext(), getString(R.string.home_extension_pic_res_max_select));
                    return;
                }
                item.setSelected(!item.isSelected());
                adapter.notifyItemChanged(position);
            }
        });
        mFooterView = new AddBannerFooterView(getContext());
        mFooterView.setOnClickCallBack(this);
        mAdapter.addFooterView(mFooterView);
        mRecyclerView.setAdapter(mAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return FOOTER_VIEW == mAdapter.getItemViewType(position) ? 2 : 1;
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new RecyclerViewGridDivide(getContext(), R.color.transparent, 15));
        mRecyclerView.setBackgroundColor(getResources().getColor(R.color.color_fff));
        fetchData(false);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.getBannerList(currentPageIndex);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_extension_banner;
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

    @Override
    public void setPresenter(ExtensionContract.AddBannerPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getBannerListSuccess(List<GetBannerListResp.DataBeanX.DataBean> list) {
        mRefreshLayout.setRefreshing(false);
        if (currentPageIndex == 1) {
            mAdapter.setNewData(list);
        } else {
            mAdapter.addData(list);
        }
    }

    @Override
    public void getBannerListFail(String msg) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.loadMoreComplete();
        ToastUtil.showShort(getContext(), msg);
    }

    @Override
    public void addBannerSuccess() {
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void addBannerFail(String msg) {
        ToastUtil.showShort(getContext(), msg);
    }

    @Override
    public void onRefresh() {
        fetchData(false);
    }

    @Override
    public void onConfirmClick(View view) {
        String gameId = getGameId();
        if (TextUtils.isEmpty(gameId)) {
            ToastUtil.showShort(getContext(), getString(R.string.home_extension_select_pic_res));
            return;
        }
        mPresenter.addBanner(getGameId());
    }

    /**
     * 获取被选中的gameId
     *
     * @return 被选中的gameId
     */
    private String getGameId() {
        StringBuilder sb = new StringBuilder();
        for (GetBannerListResp.DataBeanX.DataBean bean :
                (List<GetBannerListResp.DataBeanX.DataBean>) mAdapter.getData()) {
            if (bean.isSelected()) {
                if (!TextUtils.isEmpty(sb)) {
                    sb.append(",");
                }
                sb.append(bean.getID());
            }
        }
        return sb.toString();
    }

    @Override
    public void onCancelClick(View view) {
        onBackPressed();
    }
}
