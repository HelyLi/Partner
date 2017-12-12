package com.hhly.partner.presentation.view.me.setting;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.utils.UserPrefsUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.account.LoginActivity;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;

import butterknife.BindView;
import butterknife.OnClick;
import com.hhly.partner.presentation.view.main.MainTabActivity;

/**
 * 账户设置
 * Created by dell on 2017/4/17.
 */

public class AccountSettingFragment extends BaseFragment implements IImmersiveApply, AccountSettingContract.AccountSettingView {
    private AccountSettingContract.AccountSettingPresenter mPresenter;
    @BindView(R.id.login_account_tv)
    TextView mLoginAccountTv;
    private String userName;

    public static AccountSettingFragment newInstance() {
        Bundle args = new Bundle();
        AccountSettingFragment fragment = new AccountSettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new AccountSettingPresenterImpl(this);
        userName = UserPrefsUtil.getInstance().getUserName(getContext());
        if (!TextUtils.isEmpty(userName)) {
            mLoginAccountTv.setText(userName);
        }
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }


    @OnClick({R.id.modify_psw_layout, R.id.pay_psw_layout, R.id.exit_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.modify_psw_layout://修改密码
                ((AccountSettingActivity) getActivity()).startModifyPswFragment();
                break;
            case R.id.pay_psw_layout://支付密码
                showLoading();
                mPresenter.getPayPsw();
                break;
            case R.id.exit_tv://退出
                mPresenter.exit();
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account_setting;
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
    public void setPresenter(AccountSettingContract.AccountSettingPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getPayPswSuccess(boolean isHasPayPsw) {
        dismissLoading();
        if (isHasPayPsw) {//已经设置了支付密码跳转修改支付密码，否则跳转设置支付密码
            ((AccountSettingActivity) getActivity()).startModifyPayFragment();
        } else {
            ((AccountSettingActivity) getActivity()).startSetPayPswFragment();
        }
    }

    @Override
    public void getPayPswFail(String msg) {
        dismissLoading();
        ToastUtil.showShort(getContext(), TextUtils.isEmpty(msg) ? getString(R.string.partner_request_error) : msg);
    }

    @Override
    public void exitSuccess(String msg) {
        ToastUtil.showShort(getContext(), msg);
        UserPrefsUtil.getInstance().clear(getContext());
        getActivity().finish();
//        startActivity(LoginActivity.getCallIntent(getContext()));
        startActivity(MainTabActivity.getCallIntent(getActivity(), 5));
    }

    @Override
    public void exitFailure(String msg) {
        ToastUtil.showShort(getContext(), msg);
    }
}
