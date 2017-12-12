package com.hhly.partner.presentation.view.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.RegexUtils;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.orhanobut.logger.Logger;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import java.util.concurrent.TimeUnit;

/**
 * description :
 * Created by Flynn
 * 2017/4/14
 */

public class ResetPwdFragment extends BaseFragment implements ResetPwdContract.View {

    @BindView(R.id.reset_pwd_phone)
    EditText mResetPwdPhone;
    @BindView(R.id.reset_pwd_code)
    EditText mResetPwdCode;
    @BindView(R.id.reset_pwd_get_code)
    TextView mResetPwdGetCode;
    @BindView(R.id.reset_pwd_new_pwd)
    EditText mResetPwdNewPwd;
    @BindView(R.id.reset_pwd_confirm)
    Button mResetPwdConfirm;
    private static int SECOND = 60;
    /**
     * false表示获取验证码是否已经处于倒计时中
     */
    private boolean isClick = true;

    private ResetPwdContract.Presenter mPresenter;

    public static ResetPwdFragment newInstance() {
        Bundle args = new Bundle();
        ResetPwdFragment fragment = new ResetPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ResetPwdPresenter(this);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        RxTextView.textChanges(mResetPwdPhone)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {
                        mResetPwdGetCode.setEnabled(RegexUtils.checkMobile(charSequence.toString()) && isClick);
                    }
                });
        RxView.clicks(mResetPwdConfirm)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        resetPwd();
                    }
                });

        RxView.clicks(mResetPwdGetCode)
                .throttleFirst(SECOND, TimeUnit.SECONDS)
                .compose(this.bindToLife())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        mPresenter.getSmsCode(mResetPwdPhone.getText().toString(), 4);
                        updateBtn();
                    }
                });

    }

    private void updateBtn() {
        Flowable.interval(0, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .compose(this.<Long>bindToLife())
                .take(SECOND)
                .subscribe(new BaseSubscriber<Long>() {

                    @Override
                    public void onNext(Long aLong) {
                        try {
                            RxTextView.text(mResetPwdGetCode).accept(String.valueOf(SECOND - aLong - 1) + "S");
                            RxView.enabled(mResetPwdGetCode).accept(false);
                            isClick = false;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        try {
                            RxTextView.text(mResetPwdGetCode).accept(getString(R.string.reget_auth_code));
                            RxView.enabled(mResetPwdGetCode).accept(true);
                            isClick = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void resetPwd() {
        if (checkInput()) {
            mPresenter.resetPwd(mResetPwdPhone.getText().toString(), mResetPwdCode.getText().toString(), mResetPwdNewPwd.getText().toString());
        }
    }

    /**
     * 检查账号密码输入
     *
     * @return 是否输入正确
     */
    private boolean checkInput() {
        String account = mResetPwdPhone.getText().toString();
        String pwd = mResetPwdNewPwd.getText().toString();
        String code = mResetPwdCode.getText().toString();
        if (TextUtils.isEmpty(account)) {//
            ToastUtil.showShort(getActivity(), R.string.please_input_telephone);
            return false;
        } else if (!RegexUtils.checkMobile(account)) {
            ToastUtil.showShort(getActivity(), R.string.login_regex_phone);
            return false;
        } else if (TextUtils.isEmpty(code)) {
            ToastUtil.showShort(getActivity(), R.string.please_input_auth_code);
        } else if (TextUtils.isEmpty(pwd)) {
            ToastUtil.showShort(getActivity(), R.string.please_input_password);
            return false;
        } else if (!RegexUtils.checkPassword(pwd)) {
            ToastUtil.showShort(getContext(), getString(R.string.register_psw_length_error));
            return false;
        }
        //
        //            if (pwd.length() < 6) {
        //            ToastUtil.showShort(getActivity(), R.string.please_input_password_length);
        //            return false;
        //        }
        return true;
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_reset_password;
    }


    @OnClick({R.id.reset_pwd_get_code, R.id.reset_pwd_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.reset_pwd_get_code:
                break;
            case R.id.reset_pwd_confirm:
                break;
        }
    }

    @Override
    public void setPresenter(ResetPwdContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void resetPwdSuccess() {
        getActivity().finish();
    }

    @Override
    public void resetPwdFailure(String msg) {
        ToastUtil.showShort(getActivity(),msg);
    }

    @Override
    public void getSmsCodeSuccess() {
        ToastUtil.showShort(getActivity(), R.string.reset_pwd_get_code);
    }

    @Override
    public void getSmsCodeFailure(String msg) {
        ToastUtil.showShort(getActivity(), msg);
    }
}
