package com.hhly.partner.presentation.view.main;

import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.LoginReq;
import com.hhly.partner.data.net.protocol.user.LoginResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.MD5;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/5/12
 */

public class LoginHandle {

    private static class LoginHandleHolder {
        private static final LoginHandle INSTANCE = new LoginHandle();
    }

    private UserApi mUserApi;

    private LoginHandle() {
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    public static final LoginHandle getInstance() {
        return LoginHandleHolder.INSTANCE;
    }

    public void login(String userName, String pwd, final int count, final LoginCallBack loginCallBack) {
        final LoginReq loginReq = new LoginReq();
        loginReq.setAccount(userName);
        loginReq.setPassword(new MD5(pwd).getMd5_32());
        mUserApi.login(loginReq.params())
                .compose(RxUtil.<LoginResp>io_main())
                .subscribe(new BaseSubscriber<LoginResp>() {

                    @Override
                    public void onNext(LoginResp loginResp) {
                        if (!(loginResp != null && loginResp.isOk())) {
                            if (loginCallBack != null) {
                                loginCallBack.loginFailure(count + 1);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        if (loginCallBack != null) {
                            loginCallBack.loginFailure(count + 1);
                        }
                    }
                });
    }

    public interface LoginCallBack {
        void loginFailure(int count);
    }
}
