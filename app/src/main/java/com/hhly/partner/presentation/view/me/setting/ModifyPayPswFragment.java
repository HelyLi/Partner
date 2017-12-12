package com.hhly.partner.presentation.view.me.setting;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.RegexUtils;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * 修改支付密码
 * Created by dell on 2017/4/27.
 */

public class ModifyPayPswFragment extends BaseFragment implements IImmersiveApply
        , AccountSettingContract.ModifyPayPswView {
    @BindView(R.id.inputTelNumEdit)
    EditText mInputTelNumEdit;
    @BindView(R.id.getAuthCodeText)
    TextView mGetAuthCodeText;
    @BindView(R.id.inputAuthCodeEdit)
    EditText mInputAuthCodeEdit;
    @BindView(R.id.inputPswEdit)
    EditText mInputPswEdit;
    @BindView(R.id.confirm_modify_Btn)
    Button mConfirmModifyBtn;
    //是否正在读秒
    private boolean isCountDown;
    //是否重置获取验证码textView
    private boolean isResetAuthTv;

    private AccountSettingContract.ModifyPayPswPresenter mPresenter;

    public static ModifyPayPswFragment newInstance() {
        Bundle args = new Bundle();
        ModifyPayPswFragment fragment = new ModifyPayPswFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new ModifyPayPswPresenterImpl(this);
        mToolbarHelper.setTitle(getString(R.string.partner_personal_account_modify_pay_psw));
        RxTextView.textChanges(mInputTelNumEdit)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {
                        if (RegexUtils.checkMobile(charSequence.toString()) && !isCountDown) {
                            mGetAuthCodeText.setEnabled(true);
                        } else {
                            mGetAuthCodeText.setEnabled(false);
                        }
                    }
                });
        //获取验证码
        RxView.clicks(mGetAuthCodeText)
                .throttleFirst(1, TimeUnit.SECONDS)
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        mPresenter.getMobile(mInputTelNumEdit.getText().toString().trim());
                        isResetAuthTv = false;
                    }
                })
                .flatMap(new Function<Object, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(@NonNull Object o) throws Exception {
                        return Observable.interval(0, 1, TimeUnit.SECONDS).take(60);
                    }
                })
                .filter(new Predicate<Long>() {
                    @Override
                    public boolean test(@NonNull Long aLong) throws Exception {
                        return !isResetAuthTv;
                    }
                })
                .compose(this.<Long>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        int cur = (int) (60 - aLong - 1);
                        if (cur > 0) {
                            isCountDown = true;
                            mGetAuthCodeText.setEnabled(false);
                            mGetAuthCodeText.setText(getString(R.string.get_auth_cd, cur));
                        } else {
                            resetAuthTv();
                        }
                    }
                });
        //确认修改
        RxView.clicks(mConfirmModifyBtn).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (checkInputIsSuccess()) {
                            showLoading();
                            mPresenter.modifyPayPsw(mInputTelNumEdit.getText().toString().trim(),
                                    mInputAuthCodeEdit.getText().toString().trim(),
                                    mInputPswEdit.getText().toString().trim());
                        }
                    }
                });
    }

    private boolean checkInputIsSuccess() {
        if (!RegexUtils.checkMobile(mInputTelNumEdit.getText().toString().trim())) {
            ToastUtil.showShort(getContext(), getString(R.string.login_regex_phone));
            return false;
        }
        if (TextUtils.isEmpty(mInputAuthCodeEdit.getText().toString().trim())) {
            ToastUtil.showShort(getContext(), getString(R.string.partner_account_reset_sms_code));
            return false;
        }
        if (!RegexUtils.checkPayPassword(mInputPswEdit.getText().toString().trim())) {
            ToastUtil.showShort(getContext(), getString(R.string.partner_personal_account_psw_length_error));
            return false;
        }
        return true;
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }


    @Override
    public void setPresenter(AccountSettingContract.ModifyPayPswPresenter presenter) {
        mPresenter = presenter;
    }

    private void resetAuthTv() {
        mGetAuthCodeText.setEnabled(true);
        mGetAuthCodeText.setText(getString(R.string.reget_auth_code));
        isCountDown = false;
    }

    @Override
    public void getMobileSuccess(String msg) {
        ToastUtil.showShort(getContext(), msg);
        isResetAuthTv = true;
        resetAuthTv();
    }

    @Override
    public void getMobileFailure(String msg) {
        ToastUtil.showShort(getContext(), msg);
        isResetAuthTv = true;
        resetAuthTv();
    }

    @Override
    public void getSmsCodeSuccess(String msg) {
        ToastUtil.showShort(getContext(), msg);
    }

    @Override
    public void getSmsCodeFailure(String msg) {
        ToastUtil.showShort(getContext(), msg);
    }

    @Override
    public void modifyPayPswSuccess(String msg) {
        dismissLoading();
        ToastUtil.showShort(getContext(), msg);
        onBackPressed();
    }

    @Override
    public void modifyPayPswFailure(String msg) {
        dismissLoading();
        ToastUtil.showShort(getContext(), msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_modify_pay_psw;
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
