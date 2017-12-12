package com.hhly.partner.presentation.view.extension;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.game.GetGameCustomizationResp;
import com.hhly.partner.presentation.rxbus.RxBus;
import com.hhly.partner.presentation.rxbus.event.ExtensionHeaderEvent;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.product.search.ExtensionExtraItem;
import com.hhly.partner.presentation.view.product.search.ProductSearchActivity;
import com.hhly.partner.presentation.view.product.search.SearchType;

import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 自定义推广页头部fragment
 * Created by dell on 2017/5/3.
 */

public class CustomExtensionHeaderFragment extends BaseFragment implements ExtensionContract.BaseCustomExtensionView
        , CustomExtensionHeaderAdapter.OnClickCallBack {
    //独家
    public static final int PRIVATE_GAME_TYPE = 1;
    //推荐
    public static final int RECOMMEND_GAME_TYPE = 2;
    //单机
    public static final int OFFLINE_GAME_TYPE = 3;
    //网游
    public static final int ONLINE_GAME_TYPE = 4;
    public static final String GAME_TYPE_EXTRA_KEY = "game_type_extra_key";
    //添加游戏请求码
    public static final int ADD_HEADER_GAME_REQUEST_CODE = 0x19;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private CustomExtensionHeaderAdapter mAdapter;
    private int mGameType;
    private ExtensionContract.BaseCustomExtensionPresenter mPresenter;
    private Disposable mRxBusSubscribe;


    public static CustomExtensionHeaderFragment newInstance(int gameType) {
        Bundle args = new Bundle();
        args.putInt(GAME_TYPE_EXTRA_KEY, gameType);
        CustomExtensionHeaderFragment fragment = new CustomExtensionHeaderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mGameType = getArguments().getInt(GAME_TYPE_EXTRA_KEY, PRIVATE_GAME_TYPE);
        mRxBusSubscribe = RxBus.get().toObservable(ExtensionHeaderEvent.class).subscribe(new Consumer<ExtensionHeaderEvent>() {
            @Override
            public void accept(@NonNull ExtensionHeaderEvent event) throws Exception {
                if (mGameType == event.getEventType()) {
                    fetchData(false);
                }
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new BaseCustomExtensionPresenterImpl(this);
        mAdapter = new CustomExtensionHeaderAdapter();
        mAdapter.setOnClickCallBack(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mPresenter.getGameCustomization(getModelId(mGameType));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_custom_extension_header;
    }

    private int getModelId(int gameType) {
        int modelId = 0;
        switch (gameType) {
            case PRIVATE_GAME_TYPE:
                modelId = 11;
                break;
            case RECOMMEND_GAME_TYPE:
                modelId = 12;
                break;
            case OFFLINE_GAME_TYPE:
                modelId = 13;
                break;
            case ONLINE_GAME_TYPE:
                modelId = 14;
                break;
        }
        return modelId;
    }

    @Override
    public void setPresenter(ExtensionContract.BaseCustomExtensionPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void addGame(int promotionPosition) {
        ActivityCompat.startActivityForResult(getActivity(),
                ProductSearchActivity.getCallIntent(getContext(), SearchType.TYPE_EXTENSION,
                        new ExtensionExtraItem(getModelId(mGameType), promotionPosition))
                , ADD_HEADER_GAME_REQUEST_CODE, null);
    }

    @Override
    public void deleteGame(int gameId, int position) {
        showLoading();
        mPresenter.deleteGameCustomization(getModelId(mGameType), gameId, position);
    }

    @Override
    public void deleteGameCustomizationSuccess(int position) {
        dismissLoading();
        mAdapter.getData().remove(position);
        mAdapter.getData().add(new CustomExtensionHeaderAdapter.AddGameItem());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteGameCustomizationFailure(String msg) {
        dismissLoading();
        ToastUtil.showShort(getContext(), msg);
    }

    @Override
    public void getGameCustomizationSuccess(List<GetGameCustomizationResp.DataBeanX.DataBean> list) {
        mAdapter.updateData(list);
    }

    @Override
    public void getGameCustomizationFailure(String msg) {
        ToastUtil.showShort(getContext(), msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!mRxBusSubscribe.isDisposed()) {
            mRxBusSubscribe.dispose();
        }
    }
}
