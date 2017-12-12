package com.hhly.partner.presentation.view.me.cash;

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
 * 身份验证
 * Created by dell on 2017/4/19.
 */

public class AuthenticationFragment extends BaseFragment implements IImmersiveApply ,AuthenticationContract.View{
    @BindView(R.id.name_edit)
    EditText mNameEidt;
    @BindView(R.id.id_num_edit)
    EditText mIdNumEdit;
    @BindView(R.id.submit_btn)
    Button mSubmitBtn;

    private AuthenticationContract .Presenter mPresenter;

    public static AuthenticationFragment newInstance() {
        Bundle args = new Bundle();
        AuthenticationFragment fragment = new AuthenticationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new AuthenticationPresenterImpl(this);
        RxView.clicks(mSubmitBtn).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (isCheckInputSuccess())
                        mPresenter.submitAuthentication(mNameEidt.getText().toString(),mIdNumEdit.getText().toString());
                    }
                });
    }

    private boolean isCheckInputSuccess() {
        if (TextUtils.isEmpty(mNameEidt.getText())){
            ToastUtil.showShort(getContext(),R.string.partner_personal_auth_input_name);
            return false;
        }
        if (!RegexUtils.checkIdCard(mIdNumEdit.getText().toString())){
            ToastUtil.showShort(getContext(),getString(R.string.partner_personal_auth_input_id_num));
            return false;
        }
        return  true;
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_authentication;
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
    public void setPresenter(AuthenticationContract.Presenter presenter) {
        mPresenter=presenter;
    }

    @Override
    public void submitAuthenticationSuccess(String msg) {
        ToastUtil.showShort(getContext(),msg);
        getActivity().finish();
    }

    @Override
    public void submitAuthenticationFailure(String msg) {
        ToastUtil.showShort(getContext(),msg);
    }
}
