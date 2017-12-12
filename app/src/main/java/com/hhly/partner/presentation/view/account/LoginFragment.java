package com.hhly.partner.presentation.view.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.InputSoftListenerUtil;
import com.hhly.partner.presentation.utils.RegexUtils;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.utils.UserPrefsUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;
import com.hhly.partner.presentation.view.main.MainTabActivity;
import com.jakewharton.rxbinding2.view.RxView;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import java.util.concurrent.TimeUnit;

/**
 * description:
 * Created by dell on 2017/4/13.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View, IImmersiveApply {
    @BindView(R.id.accountEidt)
    EditText mAccountEidt;
    @BindView(R.id.passwordEdit)
    EditText mPasswordEdit;
    @BindView(R.id.loginBtn)
    Button mLoginBtn;
    @BindView(R.id.forgetPasswordText)
    TextView mForgetPasswordText;
    @BindView(R.id.wechatLoginText)
    TextView mWechatLoginText;
    @BindView(R.id.weiboLoginText)
    TextView mWeiboLoginText;
    @BindView(R.id.qqLoginText)
    TextView mQqLoginText;
    @BindView(R.id.root)
    FrameLayout mRoot;
    @BindView(R.id.scrollView)
    NestedScrollView mScrollView;
    private LoginContract.Presenter mPresenter;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new LoginPresenterImpl(this);
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        RxView.clicks(mLoginBtn)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (checkInput(mAccountEidt.getText().toString(), mPasswordEdit.getText().toString())) {
                            showLoading();
                            mPresenter.login(mAccountEidt.getText().toString(), mPasswordEdit.getText().toString());
                        }
                    }
                });
        RxView.clicks(mForgetPasswordText)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        startActivity(ResetPwdActivity.getCallIntent(getActivity()));
                    }
                });
        InputSoftListenerUtil.inputSoftShowLocation(mRoot.getRootView(), mLoginBtn, mScrollView);

    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void doLoginSuccess() {
        dismissLoading();
//        startActivity(MainTabActivity.getCallIntent(getActivity()));
//        getActivity().finish();
        startActivity(LoginOrRegisterActivity.getCallIntent(getActivity()));
    }

    @Override
    public void doLoginFail(String msg) {
        dismissLoading();
        ToastUtil.showShort(getActivity(), TextUtils.isEmpty(msg) ? getString(R.string.partner_request_network_error) : msg);
    }


    //    @OnClick({R.id.loginBtn, R.id.forgetPasswordText, R.id.wechatLoginText, R.id.weiboLoginText, R.id.qqLoginText})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loginBtn:
                if (checkInput(mAccountEidt.getText().toString(), mPasswordEdit.getText().toString())) {
                    mPresenter.login(mAccountEidt.getText().toString(), mPasswordEdit.getText().toString());
                }
                break;
            case R.id.forgetPasswordText:
                startActivity(ResetPwdActivity.getCallIntent(getActivity()));
                break;
            case R.id.wechatLoginText:
                break;
            case R.id.weiboLoginText:
                break;
            case R.id.qqLoginText:
                break;
        }
    }

    /**
     * 检查账号密码输入
     *
     * @param account 账号
     * @param pwd     密码
     * @return 是否输入正确
     */
    private boolean checkInput(String account, String pwd) {
        if (TextUtils.isEmpty(account)) {//
            ToastUtil.showShort(getActivity(), R.string.please_input_telephone);
            return false;
        } else if (!RegexUtils.checkMobile(account)) {
            ToastUtil.showShort(getActivity(), R.string.login_regex_phone);
            return false;
        } else if (TextUtils.isEmpty(pwd)) {
            ToastUtil.showShort(getActivity(), R.string.please_input_password);
            return false;
        } else if (pwd.length() < 6) {
            ToastUtil.showShort(getActivity(), R.string.please_input_password_length);
            return false;
        }
        return true;
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
        return 0;
    }
}
