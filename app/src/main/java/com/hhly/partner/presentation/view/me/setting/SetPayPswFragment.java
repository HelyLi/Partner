package com.hhly.partner.presentation.view.me.setting;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.RegexUtils;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 设置支付密码
 * Created by dell on 2017/4/20.
 */

public class SetPayPswFragment extends BaseFragment implements IImmersiveApply, AccountSettingContract.SetPayPswView {
    @BindView(R.id.new_psw_edit)
    EditText mNewPswEdit;
    @BindView(R.id.confirm_psw_edit)
    EditText mConfirmPswEdit;
    @BindView(R.id.confirm_set_btn)
    Button mConfirmSetBtn;
    private AccountSettingContract.SetPayPswPresenter mPresenter;


    public static SetPayPswFragment newInstance() {
        Bundle args = new Bundle();

        SetPayPswFragment fragment = new SetPayPswFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new SetPayPswPresenterImpl(this);
        if (mToolbarHelper != null) {
            mToolbarHelper.setTitle(getString(R.string.partner_personal_account_set_pay_psw));
        }
        RxView.clicks(mConfirmSetBtn).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (isCheckInputSuccess()) {
                            showProgress(getString(R.string.partner_submitting));
                            mPresenter.setPayPsw(mNewPswEdit.getText().toString());
                        }
                    }
                });
    }

    private boolean isCheckInputSuccess() {
        if (TextUtils.isEmpty(mNewPswEdit.getText().toString().trim())) {
            ToastUtil.showShort(getContext(), getString(R.string.please_input_password));
            return false;
        }
        if (!RegexUtils.checkPayPassword(mNewPswEdit.getText().toString().trim())){
            ToastUtil.showShort(getContext(), getString(R.string.partner_personal_account_psw_length_error));
            return false;
        }
        if (TextUtils.isEmpty(mConfirmPswEdit.getText().toString().trim())) {
            ToastUtil.showShort(getContext(), getString(R.string.please_reinput_password));
            return false;
        }
        if (!mNewPswEdit.getText().toString().equals(mConfirmPswEdit.getText().toString())) {
            ToastUtil.showShort(getContext(), getString(R.string.please_verify_password));
            return false;
        }
        return true;
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_set_pay_psw;
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
    public void setPresenter(AccountSettingContract.SetPayPswPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setPayPswSuccess(String msg) {
        dismissProgress();
        ToastUtil.showShort(getContext(), msg);
        getActivity().onBackPressed();
    }

    @Override
    public void setPayPswFailure(String msg) {
        dismissProgress();
        ToastUtil.showShort(getContext(), msg);
    }

}
