package com.hhly.partner.presentation.view.me.us.update.dialog;

import android.Manifest;
import android.app.Dialog;
import android.app.DownloadManager;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.update.CheckUpdateResp;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.main.MainTabActivity;
import com.hhly.partner.presentation.view.me.us.update.VersionUpdateHelper;
import com.hhly.partner.presentation.view.me.us.update.business.DownloadWorker;
import com.hhly.partner.presentation.view.me.us.update.callback.IDownloadCallBack;
import com.hhly.partner.presentation.view.me.us.update.receiver.DownloadCompleteReceiver;
import com.hhly.partner.presentation.view.me.us.update.receiver.DownloadProgressObserver;
import com.hhly.partner.presentation.view.widget.HorizontalProgressBarWithNumber;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;

/**
 * 下载更新dialog
 * Created by dell on 2017/5/17.
 */

public class UpdateVersionDialog extends DialogFragment implements IDownloadCallBack {
    private static final String EXTRA_KEY = "extra_key";
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.cancel_tv)
    TextView mCancelTv;
    @BindView(R.id.description_tv)
    TextView mDescriptionTv;
    Unbinder unbinder;
    @BindView(R.id.loading_progress)
    HorizontalProgressBarWithNumber mLoadingProgress;
    private boolean isMustUpdate;
    private String mApkUrl;

    private DownloadCompleteReceiver mDownloadCompleteReceiver;
    private DownloadProgressObserver mDownloadProgressObserver;

    public static UpdateVersionDialog newInstance(CheckUpdateResp resp) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_KEY, resp);
        UpdateVersionDialog fragment = new UpdateVersionDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDownloadCompleteReceiver = new DownloadCompleteReceiver(getContext(), this);
        mDownloadProgressObserver = new DownloadProgressObserver(getContext(), this);
        registerObserver();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.update_version_dialog_layout, null);
        contentView.setMinimumWidth(DisplayUtil.getScreenWidth(getContext())
                - DisplayUtil.dip2px(getContext(), 30));
        unbinder = ButterKnife.bind(this, contentView);
        CheckUpdateResp resp = (CheckUpdateResp) getArguments().getSerializable(EXTRA_KEY);
        initView(resp);
        return contentView;
    }

    private void initView(CheckUpdateResp resp) {
        mLoadingProgress.setMax(100);
        if (resp == null || resp.getData() == null) return;
        CheckUpdateResp.DataBean dataBean = resp.getData();
        isMustUpdate = dataBean.getUpdateType() == 1;
        mApkUrl = dataBean.getUrl();
        if (isMustUpdate) {
            mCancelTv.setText(getString(R.string.partner_update_exit));
            getDialog().setCanceledOnTouchOutside(false);
            setCancelable(false);
        } else {
            mCancelTv.setText(getString(R.string.partner_cancel));
        }
        mTitleTv.setText(dataBean.getTitle());
        mDescriptionTv.setText(dataBean.getContent());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        unRegisterObserver();
    }

    @OnClick({R.id.cancel_tv, R.id.download_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancel_tv:
                if (isMustUpdate) {
                    startActivity(MainTabActivity.getCallIntent(getContext(), 4));
                } else {
                    dismiss();
                }
                break;
            case R.id.download_tv:
                new RxPermissions(getActivity())
                        .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(@io.reactivex.annotations.NonNull Boolean granted) throws Exception {
                                if (!granted) {
                                    ToastUtil.showShort(getContext(), getString(R.string.partner_update_no_write_cd_permission));
                                } else {
                                    mLoadingProgress.setVisibility(View.VISIBLE);
                                    VersionUpdateHelper.getInstance().startDownload(mApkUrl, getContext());
                                }
                            }
                        });
                break;
        }
    }


    @Override
    public void onDownloadError(String msg) {
        DownloadWorker.getInstance().setRunning(false);
        ToastUtil.showShort(getContext(), msg);
        dismiss();
    }

    @Override
    public void onDownloadComplete() {
        DownloadWorker.getInstance().setRunning(false);
        if (!isMustUpdate) {
            dismiss();
        }
    }

    @Override
    public void onDownloading(long currentBytes, long totalBytes, boolean isCompleted) {
        Logger.d("onDownloading:  currentBytes:" + currentBytes + ",totalBytes:" + totalBytes);
        float percent = (float) currentBytes / (float) totalBytes;
        int progress = Math.round(percent * 100);
        Logger.d("progress:" + progress);
        mLoadingProgress.setProgress(progress);
    }

    private void registerObserver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        getContext().registerReceiver(mDownloadCompleteReceiver, intentFilter);
        getContext().getContentResolver().registerContentObserver(
                Uri.parse("content://downloads/my_downloads"), false, mDownloadProgressObserver);
    }

    private void unRegisterObserver() {
        getContext().unregisterReceiver(mDownloadCompleteReceiver);
        getContext().getContentResolver().unregisterContentObserver(mDownloadProgressObserver);
    }
}
