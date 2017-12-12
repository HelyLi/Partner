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
 * 修改密码
 * Created by dell on 2017/4/20.
 */

public class ModifyPswFragment extends BaseFragment implements IImmersiveApply, AccountSettingContract.ModifyPswView {

    @BindView(R.id.old_psw_edit)
    EditText mOldPswEdit;
    @BindView(R.id.new_psw_edit)
    EditText mNewPswEdit;
    @BindView(R.id.confirm_psw_edit)
    EditText mConfirmPswEdit;
    @BindView(R.id.confirm_modify_btn)
    Button mConfirmModifyBtn;
    private AccountSettingContract.ModifyPswPresenter mPresenter;

    public static ModifyPswFragment newInstance() {
        Bundle args = new Bundle();

        ModifyPswFragment fragment = new ModifyPswFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new ModifyPswPresenterImpl(this);
        if (mToolbarHelper != null) {
            mToolbarHelper.setTitle(getString(R.string.partner_personal_account_modify_psw));
        }
        RxView.clicks(mConfirmModifyBtn).throttleFirst(300, TimeUnit.MILLISECONDS).subscribe(
                new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (checkInput()) {
                            mPresenter.modifyPsw(mOldPswEdit.getText().toString().trim(),
                                    mNewPswEdit.getText().toString().trim());
                        }
                    }
                }
        );
    }


    private boolean checkInput() {
        if (TextUtils.isEmpty(mOldPswEdit.getText().toString().trim())) {
            ToastUtil.showShort(getContext(), getString(R.string.partner_personal_account_input_old_psw));
            return false;
        }
        if (TextUtils.isEmpty(mNewPswEdit.getText().toString().trim())) {
            ToastUtil.showShort(getContext(), getString(R.string.partner_personal_account_input_new_psw));
            return false;
        }
        if (TextUtils.isEmpty(mConfirmPswEdit.getText().toString().trim())) {
            ToastUtil.showShort(getContext(), getString(R.string.partner_personal_account_input_confirm_psw));
            return false;
        }
        if (!mNewPswEdit.getText().toString().trim().equals(mConfirmPswEdit.getText().toString().trim())) {
            ToastUtil.showShort(getContext(), getString(R.string.register_confirm_psw_error));
            return false;
        }
        if (!RegexUtils.checkPassword(mNewPswEdit.getText().toString().trim()) ||
                !RegexUtils.checkPassword(mConfirmPswEdit.getText().toString().trim())) {
            ToastUtil.showShort(getContext(), getString(R.string.register_psw_length_error));
            return false;
        }
        return true;
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    public void setPresenter(AccountSettingContract.ModifyPswPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void modifyPswSuccess(String msg) {
        ToastUtil.showShort(getContext(), msg);
        getActivity().onBackPressed();
    }

    @Override
    public void modifyPswFailure(String msg) {
        ToastUtil.showShort(getContext(), msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_modify_psw;
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


}
