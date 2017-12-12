package com.hhly.partner.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.hhly.partner.presentation.utils.UserPrefsUtil;
import com.hhly.partner.presentation.view.main.LoginHandle;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by Flynn
 * 2017/5/12
 */

public class ScheduleTaskReceiver extends BroadcastReceiver {

    private LoginHandle.LoginCallBack mLoginCallBack = new LoginHandle.LoginCallBack() {
        @Override
        public void loginFailure(final int count) {
            if (count <= 3) {
                Handler handler = new Handler(Looper.myLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(pwd)) {
                            LoginHandle.getInstance().login(userName, pwd, count, mLoginCallBack);
                        }
                    }
                }, count * 60 * 1000);
            }
        }
    };
    private String userName;
    private String pwd;

    @Override
    public void onReceive(Context context, Intent intent) {
        Logger.d("Flynn == > ScheduleTaskReceiver");
        int count = 0;
        userName = UserPrefsUtil.getInstance().getUserName(context);
        pwd = UserPrefsUtil.getInstance().getUserPwd(context);
        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(pwd)) {
            LoginHandle.getInstance().login(userName, pwd, count, mLoginCallBack);
        }
    }
}
