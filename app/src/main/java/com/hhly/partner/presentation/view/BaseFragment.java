package com.hhly.partner.presentation.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.hhly.partner.R;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;
import com.hhly.partner.presentation.view.immersive.ToolbarHelper;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description :
 * Created by Flynn
 * 2017/4/11.
 */
public abstract class BaseFragment extends RxFragment implements BaseLoadingView {
    protected final static Handler mHandler = new Handler(Looper.getMainLooper());
    protected Unbinder mUnbinder;
    protected ProgressDialog mProgressDialog;
    protected View mRootView = null;
    protected ToolbarHelper mToolbarHelper;

    private boolean isViewCreated = false;
    private boolean isVisibleToUser = false;
    private boolean isDataInitiated = false;

    protected int currentPageIndex = 1;
    protected int pageSize = 10;
    protected SwipeRefreshLayout mRefreshLayout;
    //是否请求刷新数据
    protected boolean mIsForceUpdate;

    public BaseFragment() {
        if (getArguments() == null) {
            setArguments(new Bundle());
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            mUnbinder = ButterKnife.bind(this, mRootView);
            initToolbarView(mRootView);
            initRefreshLayout(mRootView);
        }
        if (this instanceof IImmersiveApply) {
            if (mToolbarHelper == null) {
                mToolbarHelper = new ToolbarHelper();
            }
            mToolbarHelper.attach((IImmersiveApply) this, mRootView);
        }
        if (mToolbarHelper != null) {
            mToolbarHelper.onRestore(getArguments());
        }
        return mRootView;
    }

    private void initRefreshLayout(View contentView) {
        mRefreshLayout = (SwipeRefreshLayout) contentView.findViewById(R.id.refresh_layout);
        if (mRefreshLayout != null) {
            mRefreshLayout.setColorSchemeResources(R.color.color_a965f4, R.color.color_3c4444, R.color.color_f60808);
        }
    }

    protected void initToolbarView(View view) {
        if (!enableSetActionBar()) {
            return;
        }
        if (getActivity() != null) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolBar);
            if (toolbar != null) {
                //                toolbar.setNavigationIcon(R.drawable.ic_navigation_up);
                activity.setSupportActionBar(toolbar);
                ActionBar actionBar = activity.getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setTitle("");
                    if (enableHomeAsUp()) {
                        actionBar.setDisplayHomeAsUpEnabled(true);
                        actionBar.setDisplayShowCustomEnabled(true);
                    } else {
                        actionBar.setDisplayHomeAsUpEnabled(false);
                        actionBar.setDisplayShowCustomEnabled(false);
                    }
                }
            }
        }
    }

    @Override
    public final void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!isViewCreated) {
            isViewCreated = true;
            viewCreated(view, savedInstanceState);
            prepareFetchData();
        }
        if (mToolbarHelper != null) {
            mToolbarHelper.onRestore(getArguments());
        }
    }

    protected abstract void viewCreated(View view, Bundle savedInstanceState);

    @Override
    public void onResume() {
        super.onResume();
        if (mToolbarHelper != null) {
            mToolbarHelper.onRestore(getArguments());
        }
        //        StatisticalUtil.onPageStart(getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mToolbarHelper != null) {
            mToolbarHelper.onSave(getArguments());
        }
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
        //        StatisticalUtil.onPageEnd(getClass().getSimpleName());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mToolbarHelper != null) {
            mToolbarHelper.detach();
            mToolbarHelper = null;
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
    }

    protected abstract void fetchData(boolean isLoadMore);

    protected void prepareFetchData() {
        prepareFetchData(mIsForceUpdate, false);
    }

    protected void prepareFetchData(boolean isForceUpdate, boolean isLoadMore) {
        if (isVisibleToUser && isViewCreated && (!isDataInitiated || isForceUpdate)) {
            isDataInitiated = true;
            fetchData(isLoadMore);
        }
    }

    public void setForceUpdate(boolean forceUpdate) {
        mIsForceUpdate = forceUpdate;
    }

    protected abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void post(Runnable runnable) {
        mHandler.post(runnable);
    }

    protected void postDelay(Runnable runnable, long delay) {
        mHandler.postDelayed(runnable, delay);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mHandler.removeCallbacksAndMessages(null);
    }

    protected void onBackPressed() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    protected boolean enableHomeAsUp() {
        return true;
    }

    protected boolean enableSetActionBar() {
        return true;
    }

    @Override
    public void showMsg(int resId) {
        //        ToastUtil.showTip(getActivity(), resId);
    }

    @Override
    public void showMsg(String msg) {
        Logger.d("msg=" + msg + "fragment=" + this);
        if (TextUtils.isEmpty(msg))
            return;
        //        ToastUtil.showTip(getActivity(), msg);
    }

    @MainThread
    @Override
    public void showLoading() {
        showProgress(null);
    }

    @MainThread
    @Override
    public void showLoading(int resId) {
        showProgress(getString(resId));
    }

    @MainThread
    @Override
    public void showLoading(String msg) {
        showProgress(msg);
    }

    @MainThread
    protected void showProgress(String msg) {
        if (getActivity().isFinishing())
            return;
        if (mProgressDialog == null)
            return;
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        if (TextUtils.isEmpty(msg)) {
            mProgressDialog.setMessage(getString(R.string.default_progress_tip));
        } else {
            mProgressDialog.setMessage(msg);
        }
        mProgressDialog.show();
    }

    @MainThread
    @Override
    public void dismissLoading() {
        dismissProgress();
    }

    @MainThread
    protected void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }
}
