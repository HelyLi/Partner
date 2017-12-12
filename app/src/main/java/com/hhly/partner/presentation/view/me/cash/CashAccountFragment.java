package com.hhly.partner.presentation.view.me.cash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.user.BankResp;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;
import com.hhly.partner.presentation.view.widget.AuthenticationDialog;
import com.jakewharton.rxbinding2.view.RxView;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by dell on 2017/4/17.
 */

public class CashAccountFragment extends BaseFragment implements IImmersiveApply, CashAccountContract.View
        , SwipeRefreshLayout.OnRefreshListener {
    public static final int ADD_BANK_CARD_REQUESTCODE = 0x11;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private BaseQuickAdapter mAdapter;
    private CashAccountContract.Presenter mPresenter;

    public static CashAccountFragment newInstance() {
        Bundle args = new Bundle();
        CashAccountFragment fragment = new CashAccountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.cash_account_header, null);
        RxView.clicks(headerView).throttleFirst(2, TimeUnit.SECONDS).subscribe(
                new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        showLoading();
                        mPresenter.checkAccountIsAuthed();
                    }
                }
        );
        mAdapter = new CashAccountAdapter();
        mAdapter.addHeaderView(headerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        new CashAccountPresenterImpl(this);
        mRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.getBankCardInfo();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cash_account;
    }

    @Override
    public void setPresenter(CashAccountContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getBankCardInfoSuccess(List<BankResp.DataBeanX.DataBean> beanList) {
        mRefreshLayout.setRefreshing(false);
        mAdapter.setNewData(beanList);
    }

    @Override
    public void getBankCardInfoFailure() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onCheckIsAuthedSuccess(boolean isAuthed) {
        dismissLoading();
        Logger.d("isAuthed:" + isAuthed);
        if (isAuthed) {//身份已验证
            ActivityCompat.startActivityForResult(getActivity(),
                    AddBankCardActivity.getCallIntent(getContext()), ADD_BANK_CARD_REQUESTCODE, null);
        } else {//未验证
            AuthenticationDialog dialog = new AuthenticationDialog();
            dialog.setOnAuthNowClickListener(new AuthenticationDialog.OnAuthNowClickListener() {
                @Override
                public void onClick() {
                    ActivityCompat.startActivity(getContext(), AuthenticationActivity.getCallIntent(getContext()), null);
                }
            });
            ActivityUtil.addFragment(getFragmentManager(), dialog);
        }
    }

    @Override
    public void onCheckIsAuthedFailure(String msg) {
        dismissLoading();
        ToastUtil.showShort(getContext(), msg);
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
    public void onRefresh() {
        fetchData(false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case ADD_BANK_CARD_REQUESTCODE:
                    fetchData(false);
                    break;
            }
        }
    }
}
