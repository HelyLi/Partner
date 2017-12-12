package com.hhly.partner.application;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;

import com.hhly.partner.data.config.SystemConfig;
import com.hhly.partner.presentation.utils.TelephonyUtil;
import com.hhly.partner.reciver.NetworkConnectChangedReceiver;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * description :
 * Created by dell on 2017/4/11.
 */

public class App extends Application {
    /**
     * 全局Context
     */
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        //先初始化环境配置，NetModule会取api地址
        SystemConfig.get().initialize(this, TelephonyUtil.getEnvironment(this));

        //        NetService.init();

        //        RxPaparazzo.register(this);

        //GreenDao数据库管理初始化
        //        DaoManager.getInstance().init(getApplicationContext());
        //
        initReceiver();
        Logger.init("TAG").logLevel(LogLevel.FULL);
        ActivityStackManager.get().registerSelf(this);
        //                .logLevel(LogLevel.NONE);

        //        initBugly();
    }

    //    private void initBugly() {
    //        // 获取当前包名
    //        String packageName = mContext.getPackageName();
    //        // 获取当前进程名
    //        String processName = Utils.getProcessName(android.os.Process.myPid());
    //        // 设置是否为上报进程
    //        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(mContext);
    //        strategy.setUploadProcess(processName == null || processName.equals(packageName));
    //        // 初始化Bugly
    //        // CrashReport.initCrashReport(context, "注册时申请的APPID", isDebug, strategy);
    //        // 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
    //        CrashReport.initCrashReport(mContext, strategy);
    //    }

    public static Context getContext() {
        return mContext;
    }

    private void initReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        NetworkConnectChangedReceiver mNetworkConnectChangedReceiver = new NetworkConnectChangedReceiver();
        registerReceiver(mNetworkConnectChangedReceiver, filter);
    }

}
