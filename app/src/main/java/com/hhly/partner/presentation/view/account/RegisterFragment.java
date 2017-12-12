package com.hhly.partner.presentation.view.account;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.RegexUtils;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.jakewharton.rxbinding2.widget.RxTextView;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by dell on 2017/4/13.
 */

public class RegisterFragment extends BaseFragment implements RegisterContract.SmsCodeView {
    @BindView(R.id.inputTelNumEdit)
    EditText mInputTelNumEdit;
    @BindView(R.id.inputAuthCodeEdit)
    EditText mInputAuthCcdeEdit;
    @BindView(R.id.getAuthCodeText)
    TextView mGetAuthCodeText;
    private RegisterContract.SmsCodePresenter mPresenter;
    private boolean isCountDown;

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new SmsCodePresenterImpl(this);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        RxTextView.textChanges(mInputTelNumEdit)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {
                        if (RegexUtils.checkMobile(charSequence.toString())&&!isCountDown) {
                            mGetAuthCodeText.setEnabled(true);
                        } else {
                            mGetAuthCodeText.setEnabled(false);
                        }
                    }
                });
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register;
    }

    @OnClick({R.id.getAuthCodeText, R.id.nextStepBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.getAuthCodeText:
                if (RegexUtils.checkMobile(mInputTelNumEdit.getText().toString())) {
                    mVerificationTimer.start();
                    mPresenter.requestVerificationCode(mInputTelNumEdit.getText().toString());
                } else {
                    ToastUtil.showShort(getContext(), getString(R.string.register_phone_input_error));
                }
                break;
            case R.id.nextStepBtn:
                if (!RegexUtils.checkMobile(mInputTelNumEdit.getText().toString())) {
                    ToastUtil.showShort(getContext(), getString(R.string.register_phone_input_error));
                    return;
                }
                if (TextUtils.isEmpty(mInputAuthCcdeEdit.getText())) {
                    ToastUtil.showShort(getContext(), getString(R.string.register_auth_code_not_empty));
                    return;
                }
                mPresenter.confirmAuthCode(mInputTelNumEdit.getText().toString(), mInputAuthCcdeEdit.getText().toString());
                break;
        }
    }


    private CountDownTimer mVerificationTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            if (mGetAuthCodeText != null) {
                mGetAuthCodeText.setEnabled(false);
                mGetAuthCodeText.setText(getString(R.string.get_auth_cd, millisUntilFinished / 1000));
                isCountDown=true;
            }
        }

        @Override
        public void onFinish() {
            if (mGetAuthCodeText != null) {
                mGetAuthCodeText.setEnabled(true);
                mGetAuthCodeText.setText(getString(R.string.reget_auth_code));
                isCountDown=false;
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        mVerificationTimer.cancel();
    }

    @Override
    public void setPresenter(RegisterContract.SmsCodePresenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void onGetCodeSuccess(String msg) {
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void onGetCodeFailure(String msg) {
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void onCheckCodeSuccess(String msg) {
        if (getActivity() != null && getActivity() instanceof RegisterActivity) {
            ((RegisterActivity) getActivity()).doRegisterStepTwo(mInputTelNumEdit.getText().toString());
        }
    }

    @Override
    public void onCheckCodeFailure(String msg) {
        ToastUtil.showShort(getContext(), msg);
    }
}
