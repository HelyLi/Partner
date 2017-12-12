package com.hhly.partner.presentation.view.product;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GameTypeDataResp;
import com.hhly.partner.presentation.view.BaseFragment;

import java.util.List;

//import io.reactivex.disposables.Disposable;

//import static com.hhly.partner.presentation.rxbus.event.ProductBannerEvent.DOWNLOAD_EVENT;

/**
 * 产品》（策略、角色、射击、冒险、动作、棋牌）
 * Created by dell on 2017/4/15.
 */

public class ProductCommonFragment extends BaseFragment implements ProductContract.CommonView {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private int mGameType;
    private BaseQuickAdapter mAdapter;
    private ProductContract.Presenter mPresenter;

    public static ProductCommonFragment newInstance(int gameType) {
        Bundle args = new Bundle();
        args.putInt("gameType", gameType);
        ProductCommonFragment fragment = new ProductCommonFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ProductCommonFragment() {
        super();
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new ProductCommonPresenterImpl(this);
        mGameType = getArguments().getInt("gameType");
        mAdapter = new ProductCommonAdapter();
        View emptyView = View.inflate(getActivity(), R.layout.widget_empty_view, null);
        mAdapter.setEmptyView(emptyView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void fetchData(boolean isLoadMore) {
        mPresenter.getGameData(mGameType);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.include_recycler_view_wrap;
    }


    @Override
    public void setPresenter(ProductContract.Presenter presenter) {
        mPresenter = presenter;
    }

    void hideRefresh() {
        if (getParentFragment() != null && getParentFragment() instanceof ProductManageFragment) {
            ((ProductManageFragment) getParentFragment()).hideRefresh();
        }
    }


    @Override
    public void getGameDataSuccess(GameTypeDataResp.DataBean dataBean) {
        hideRefresh();
        List<GameTypeDataResp.DataBean.GameTypeDataBean> list = dataBean.getGameTypeData();
        mAdapter.setNewData(dataBean.getGameTypeData());
    }

    @Override
    public void getGameDataFailure(String msg) {
        hideRefresh();
    }

    public boolean isFirst() {
        if (mRecyclerView != null
                && mRecyclerView.getLayoutManager() != null
                && ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition() != 0) {
            return false;
        }
        return true;
    }

}
