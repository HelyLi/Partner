package com.hhly.partner.presentation.view.splash;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.utils.UserPrefsUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.account.LoginContract;
import com.hhly.partner.presentation.view.account.LoginOrRegisterActivity;
import com.hhly.partner.presentation.view.account.LoginPresenterImpl;
import com.hhly.partner.presentation.view.main.MainTabActivity;
import com.hhly.partner.presentation.view.widget.SimpleButton;
import com.orhanobut.logger.Logger;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

import java.util.concurrent.TimeUnit;

/**
 * description :
 * Created by Flynn
 * 2017/5/10
 */

public class SplashFragment extends BaseFragment implements LoginContract.View {

    @BindView(R.id.sb_skip)
    SimpleButton mSbSkip;

    private boolean mIsSkip = false;

    LoginContract.Presenter mPresenter;
    private Integer loginStat;
    public static final int SUCCESS = 1;
    public static final int FAILURE = 2;
    public static final int NORMAL = 3;

    private int TIME = 3;

    public static SplashFragment newInstance() {
        Bundle args = new Bundle();
        SplashFragment fragment = new SplashFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new LoginPresenterImpl(this);
        fetchData(false);
        Flowable.interval(0, 1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .compose(this.<Long>bindToLife())
                .take(TIME + 1)
                .subscribe(new BaseSubscriber<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        if (TIME == aLong.intValue()) {
                            mSbSkip.setVisibility(View.GONE);
                        }
                        mSbSkip.setText(getString(R.string.partner_skip) + " " + (TIME - aLong.intValue()));
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        _doSkip();
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                        _doSkip();
                    }
                });
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        String userName = UserPrefsUtil.getInstance().getUserName(getActivity());
        String pwd = UserPrefsUtil.getInstance().getUserPwd(getActivity());
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(pwd)) {
            mPresenter.login(userName, pwd);
        } else {
            loginStat = NORMAL;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_splash;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void doLoginSuccess() {
        loginStat = SUCCESS;
        //        _doSkip();
    }

    @Override
    public void doLoginFail(String msg) {
        loginStat = FAILURE;
        //        _doSkip();
    }

    private void _doSkip() {
        if (!mIsSkip) {
            if (loginStat == null) {
                startActivity(LoginOrRegisterActivity.getCallIntent(getActivity()));
                getActivity().finish();
                return;
            }
            mIsSkip = true;
            switch (loginStat) {
                case SUCCESS:
                    startActivity(MainTabActivity.getCallIntent(getActivity()));
                    break;
                case FAILURE:
                case NORMAL:
                    startActivity(LoginOrRegisterActivity.getCallIntent(getActivity()));
                    break;
            }
            getActivity().finish();
        }
    }

    private void skip() {
        if (loginStat == null) {
            //TODO 文字待写
            ToastUtil.showShort(getActivity(), "自动登录中...");
            return;
        }
        mIsSkip = true;
        switch (loginStat) {
            case SUCCESS:
                startActivity(MainTabActivity.getCallIntent(getActivity()));
                break;
            case FAILURE:
            case NORMAL:
                startActivity(LoginOrRegisterActivity.getCallIntent(getActivity()));
                break;
        }
    }


    @OnClick(R.id.sb_skip)
    public void onClick() {
        //        mIsSkip = true;
        //        _doSkip();
        skip();
    }
}
