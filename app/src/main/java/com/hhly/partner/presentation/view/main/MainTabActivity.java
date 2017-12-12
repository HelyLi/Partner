package com.hhly.partner.presentation.view.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import butterknife.BindView;
import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseActivity;
import com.hhly.partner.presentation.view.account.LoginOrRegisterActivity;
import com.hhly.partner.presentation.view.activity.ActivityCenterFragment;
import com.hhly.partner.presentation.view.home.HomeFragment;
import com.hhly.partner.presentation.view.me.MeFragment;
import com.hhly.partner.presentation.view.me.us.update.VersionUpdateHelper;
import com.hhly.partner.presentation.view.product.ProductManageFragment;
import com.hhly.partner.presentation.view.widget.BottomBar;
import com.hhly.partner.presentation.view.widget.FFragmentTabHost;
import com.hhly.partner.reciver.ScheduleTaskReceiver;
import com.orhanobut.logger.Logger;

/**
 * description
 * Created by dell on 2017/4/11.
 */

public class MainTabActivity extends BaseActivity implements BottomBar.OnTabSelectListener {
    @BindView(R.id.tab_host)
    FFragmentTabHost mTabHost;
    @BindView(R.id.tab_bar)
    BottomBar mBottomBar;

    public static final String TAG_HOME = "TAG_HOME";
    public static final String TAG_ACTIVITY = "TAG_ACTIVITY";
    public static final String TAG_PRODUCT = "TAG_PRODUCT";
    public static final String TAG_ME = "TAG_ME";
    public static final String EXTRA_NEED_START_MAIN = "extra_need_start_main";
    private AlarmManager mAManager;
    public static final String SCHEDULETASKACTION = "com.hhly.partner.scheduletask";
    private PendingIntent mPi;
    private Long mScheduleTime = 25 * 60 * 1000L;
    private ScheduleTaskReceiver mScheduleTaskReceiver;

    public static Intent getCallIntent(Context context) {
        Intent intent = new Intent(context, MainTabActivity.class);
        return intent;
    }

    /**
     * @param context
     * @param targetPage 0-3 表示需要跳转到主界面第几页    4 表示退出  5 表示跳到登录页
     * @return
     */
    public static Intent getCallIntent(Context context, int targetPage) {
        Intent intent = new Intent(context, MainTabActivity.class);
        intent.putExtra(EXTRA_NEED_START_MAIN, targetPage);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBottomBar.addTab(R.id.tab_home, R.drawable.ic_tab_home, R.string.tab_home);
        mBottomBar.addTab(R.id.tab_activity, R.drawable.ic_tab_activity, R.string.tab_activity);
        mBottomBar.addTab(R.id.tab_product, R.drawable.ic_tab_product, R.string.tab_product);
        mBottomBar.addTab(R.id.tab_me, R.drawable.ic_tab_personal, R.string.tab_me);
        mBottomBar.setTabSelectListener(this);
        mBottomBar.selectTabAtPosition(0);

        mTabHost.setup(getContext(), getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.addTab(mTabHost.newTabSpec(TAG_HOME).setIndicator(TAG_HOME), HomeFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAG_ACTIVITY).setIndicator(TAG_ACTIVITY), ActivityCenterFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAG_PRODUCT).setIndicator(TAG_PRODUCT), ProductManageFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAG_ME).setIndicator(TAG_ME), MeFragment.class, null);

        IntentFilter intentFilter = new IntentFilter(SCHEDULETASKACTION);
        mScheduleTaskReceiver = new ScheduleTaskReceiver();
        registerReceiver(mScheduleTaskReceiver, intentFilter);
        Intent intent = new Intent(SCHEDULETASKACTION);
        mPi = PendingIntent.getBroadcast(this, 0, intent, 0);
        mAManager = (AlarmManager) getSystemService(Service.ALARM_SERVICE);
        mAManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + mScheduleTime, mScheduleTime, mPi);

        //版本更新检查
        VersionUpdateHelper.getInstance().checkUpdate(getContext(), false);
    }

    /**
     * 用来表示是否用户主动离开此界面 比如Home键
     */
    //    private boolean isUserLeave;
    //
    //    @Override
    //    protected void onResume() {
    //        super.onResume();
    //        isUserLeave = false;
    //    }

    //    @Override
    //    protected void onUserLeaveHint() {
    //        super.onUserLeaveHint();
    //        isUserLeave = true;
    //    }
    //
    //    @Override
    //    protected void onSaveInstanceState(Bundle outState) {
    //        super.onSaveInstanceState(outState);
    //        if (!isUserLeave) {
    //            recycleAManager();
    //        }
    //        isUserLeave = false;
    //    }
    private void recycleAManager() {
        if (mAManager != null) {
            mAManager.cancel(mPi);
        }
        mAManager = null;
        if (mScheduleTaskReceiver != null) {
            unregisterReceiver(mScheduleTaskReceiver);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onTabSelected(int tabId) {
        switch (tabId) {
            case R.id.tab_home:
                mTabHost.setCurrentTabByTag(TAG_HOME);
                break;
            case R.id.tab_activity:
                mTabHost.setCurrentTabByTag(TAG_ACTIVITY);
                break;
            case R.id.tab_product:
                mTabHost.setCurrentTabByTag(TAG_PRODUCT);
                break;
            case R.id.tab_me:
                mTabHost.setCurrentTabByTag(TAG_ME);
                break;
        }
    }

    private long mLastBackEventTime = 0;
    private static final long INTERVAL = 3000;
    private int mBackCount = 0;
    private CountDownTimer mExitCd = new CountDownTimer(INTERVAL, INTERVAL) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            mBackCount = 0;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Logger.d("onKeyDown=" + keyCode);

        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (!(TAG_HOME.equals(mTabHost.getCurrentTabTag()))) {
                mBottomBar.selectTabWithId(R.id.tab_home);
                return true;
            }
            long current = System.currentTimeMillis();
            if (mBackCount == 0) {
                mLastBackEventTime = current;
                mBackCount++;
                ToastUtil.showShort(getContext(), R.string.partner_exit_app);
                mExitCd.start();
            } else {
                if (current - mLastBackEventTime <= INTERVAL) {
                    //exit
                    mExitCd.cancel();
                    super.onKeyDown(keyCode, event);
                    exit();
                } else {
                    mBackCount = 0;
                }
                mLastBackEventTime = current;
            }
            return true;
        }
        mBackCount = 0;
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Logger.d("MainTab.onNewIntent");
        int targetPage = intent.getIntExtra(EXTRA_NEED_START_MAIN, 0);
        switch (targetPage) {
            case 0:
            case 1:
            case 2:
            case 3:
                mBottomBar.selectTabAtPosition(targetPage);
                break;
            case 4://退出
                exit();
            case 5://退出账号 回到登录注册界面
                startActivity(LoginOrRegisterActivity.getCallIntent(this));
                finish();
                break;
        }
    }

    private void exit() {
        finish();
        System.exit(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Logger.d("requestCode" + requestCode + ",resultCode" + requestCode);

        for (FFragmentTabHost.TabInfo tabInfo : mTabHost.getTabs()) {
            Fragment fragment = tabInfo.getFragment();
            if (fragment != null)
                fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recycleAManager();
        Logger.d("AlarmManager cancel");
    }


}
