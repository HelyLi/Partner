package com.hhly.partner.presentation.view.property;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.user.PriceResp;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.me.cash.AuthenticationActivity;
import com.hhly.partner.presentation.view.me.cash.CashAccountActivity;
import com.hhly.partner.presentation.view.me.setting.AccountSettingActivity;
import com.hhly.partner.presentation.view.property.incomedetail.IncomeDetailActivity;
import com.hhly.partner.presentation.view.property.settlement.SettlementActivity;
import com.hhly.partner.presentation.view.property.transaction.TransactionActivity;
import com.hhly.partner.presentation.view.property.withdraw.WithdrawActivity;
import com.hhly.partner.presentation.view.widget.AuthenticationDialog;
import com.hhly.partner.presentation.view.widget.PayPasswordDialog;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * description : 我的资产
 * Created by Flynn
 * 2017/4/24
 */

public class MyPropertyFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, MyPropertyContract.View {

    @BindView(R.id.money)
    TextView mMoney;
    @BindView(R.id.withdraw_btn)
    Button mWithdrawBtn;
    @BindView(R.id.withdraw_account_tv)
    TextView mWithdrawAccountTv;
    @BindView(R.id.transaction_record_tv)
    TextView mTransactionRecordTv;
    @BindView(R.id.income_detail_tv)
    TextView mIncomeDetailTv;
    @BindView(R.id.settlement_tv)
    TextView mSettlementTv;

    private MyPropertyContract.Presenter mPresenter;

    public static MyPropertyFragment newInstance() {
        Bundle args = new Bundle();
        MyPropertyFragment fragment = new MyPropertyFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new MyPropertyPresenter(this);
        mRefreshLayout.setOnRefreshListener(this);
        fetchData(false);
        initViewClick();
    }

    private void initViewClick() {
        RxView.clicks(mWithdrawAccountTv)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        startActivity(CashAccountActivity.getCallIntent(getActivity()));
                    }
                });

        RxView.clicks(mTransactionRecordTv)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        startActivity(TransactionActivity.getCallIntent(getActivity()));
                    }
                });
        RxView.clicks(mIncomeDetailTv)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        startActivity(IncomeDetailActivity.getCallIntent(getActivity()));
                    }
                });
        RxView.clicks(mSettlementTv)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        startActivity(SettlementActivity.getCallIntent(getActivity(), SettlementActivity.SETTLEMENT));
                    }
                });
        RxView.clicks(mWithdrawBtn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        mPresenter.withdraw();
                    }
                });
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.getPriceData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_property;
    }

    @Override
    public void onRefresh() {
        fetchData(false);
    }

    @Override
    public void setPresenter(MyPropertyContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getPriceSuccess(List<PriceResp.DataBeanX.DataBean> list) {
        mRefreshLayout.setRefreshing(false);
        if (list != null && list.size() > 0) {
            mMoney.setText(String.format("%.2f", list.get(0).getAMOUNT()));
        }
    }

    @Override
    public void getPriceFailure(String msg) {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void findIdCardFailure() {
        AuthenticationDialog dialog = new AuthenticationDialog();
        dialog.setOnAuthNowClickListener(new AuthenticationDialog.OnAuthNowClickListener() {
            @Override
            public void onClick() {
                ActivityCompat.startActivity(getContext(), AuthenticationActivity.getCallIntent(getContext()), null);
            }
        });
        ActivityUtil.addFragment(getFragmentManager(), dialog);
    }

    @Override
    public void getPayPwdFailure() {
        PayPasswordDialog dialog = new PayPasswordDialog();
        dialog.setOnPayPwdSettingClickListener(new PayPasswordDialog.OnPayPwdSettingClickListener() {
            @Override
            public void onClick() {
                ActivityCompat.startActivity(getContext(), AccountSettingActivity.getCallIntent(getContext(), 1), null);
            }
        });
        ActivityUtil.addFragment(getFragmentManager(), dialog);
    }

    @Override
    public void withdrawSuccess() {
        startActivityForResult(WithdrawActivity.getCallIntent(getActivity()), 100);
    }

    @Override
    public void withdrawFailure(String msg) {
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.getPriceData();
    }

}
