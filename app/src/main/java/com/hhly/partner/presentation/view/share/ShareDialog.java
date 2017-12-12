package com.hhly.partner.presentation.view.share;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.github.xmxu.cf.*;
import com.github.xmxu.cf.qq.QQShareHandler;
import com.github.xmxu.cf.sina.WeiboShareHandler;
import com.github.xmxu.cf.wechat.WechatShareHandler;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.update.CheckUpdateResp;
import com.hhly.partner.presentation.utils.Constant;
import com.hhly.partner.presentation.utils.DisplayUtil;
import com.hhly.partner.presentation.view.me.us.update.dialog.UpdateVersionDialog;
import com.orhanobut.logger.Logger;

/**
 * description : 分享对话框
 * Created by Flynn
 * 2017/5/26
 */

public class ShareDialog extends DialogFragment {

    Unbinder unbinder;
    private Callback<ShareResult> mOnShareClickListener;
    private Context mContext;

    //    public interface OnShareClickListener {
    //        void onClick(int type);
    //    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public static ShareDialog newInstance(ShareContent shareContent) {
        Bundle args = new Bundle();
        args.putParcelable(Constant.KEY, shareContent);
        ShareDialog fragment = new ShareDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = new Dialog(getContext(), R.style.BottomDialogStyle);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_share, null);
        //        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //        params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        //        view.setLayoutParams(params);
        view.setMinimumWidth(DisplayUtil.getScreenWidth(getContext()) - DisplayUtil.dip2px(getContext(), 30));
        unbinder = ButterKnife.bind(this, view);
        dialog.setContentView(view);
        return dialog;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mShareContent = getArguments().getParcelable(Constant.KEY);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.share_sina, R.id.share_qq, R.id.cancel})
    public void onViewClicked(View view) {
        if (mOnShareClickListener == null) {
            mOnShareClickListener = mDefaultShareResultCallback;
        }
        switch (view.getId()) {
            case R.id.share_sina:
                dismiss();
                //TODO 分享微博
                //                onWeiboShare(mShareContent);
                break;
            case R.id.share_qq:
                dismiss();
                //TODO 分享QQ空间
                //                onQQShareQZone(mShareContent);
                break;
            case R.id.cancel:
                dismiss();
                break;
        }
    }

    private Caller mCurrentCaller;
    private ShareContent mShareContent;
    private Callback<ShareResult> mShareResultCallback;

    public void setShareCallback(Callback<ShareResult> callback) {
        this.mShareResultCallback = callback;
    }


    private void onQQShareQZone(ShareContent shareContent) {
        mCurrentCaller = Cuttlefish.with(mContext).share()
                .appName(shareContent.getAppName())
                .title(shareContent.getTitle())
                .description(shareContent.getDescription())
                .content(shareContent.getContent())
                .image(shareContent.getImage())
                .link(shareContent.getLink()).callback(mShareResultCallback).to(QQShareHandler.get(QQShareHandler.QZONE));
    }

    private void onQQShareFriend(ShareContent shareContent) {
        mCurrentCaller = Cuttlefish.with(mContext).share()
                .appName(shareContent.getAppName())
                .title(shareContent.getTitle())
                .description(shareContent.getDescription())
                .content(shareContent.getContent())
                .image(shareContent.getImage())
                .link(shareContent.getLink()).callback(mShareResultCallback).to(QQShareHandler.get(QQShareHandler.QQ));
    }

    private void onWeiboShare(ShareContent shareContent) {
        mCurrentCaller = Cuttlefish.with(mContext).share()
                .appName(shareContent.getAppName())
                .title(shareContent.getTitle())
                .description(shareContent.getDescription())
                .content(shareContent.getContent())
                .image(shareContent.getImage())
                .link(shareContent.getLink()).callback(mShareResultCallback).to(WeiboShareHandler.get());
        Logger.d("mCurrentCaller" + mCurrentCaller.handler());
    }

    /**
     * success
     *
     * @param shareContent
     */
    private void onWechatSessionShare(ShareContent shareContent) {
        mCurrentCaller = Cuttlefish.with(mContext).share()
                .appName(shareContent.getAppName())
                .title(shareContent.getTitle())
                .description(shareContent.getDescription())
                .content(shareContent.getContent())
                .image(shareContent.getImage())
                .link(shareContent.getLink()).callback(mShareResultCallback)
                .to(WechatShareHandler.get());
    }

    /**
     * success
     *
     * @param shareContent
     */
    private void onWechatTimelineShare(ShareContent shareContent) {
        mCurrentCaller = Cuttlefish.with(mContext).share()
                .appName(shareContent.getAppName())
                .title(shareContent.getTitle())
                .description(shareContent.getDescription())
                .content(shareContent.getContent())
                .image(shareContent.getImage())
                .link(shareContent.getLink()).callback(mShareResultCallback)
                .to(WechatShareHandler.get(WechatShareHandler.TIMELINE));
    }

    private Callback<ShareResult> mDefaultShareResultCallback = new Callback<ShareResult>() {
        @Override
        public void onFailure(Result result) {
            if (result.getErrorCode() != Result.Code.CANCEL) {
                Toast.makeText(mContext, TextUtils.isEmpty(result.getErrorMsg()) ? mContext.getString(R.string.partner_share_failure) : result.getErrorMsg(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, R.string.partner_share_cancel, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onComplete(ShareResult result) {
            dismiss();
            Toast.makeText(mContext, R.string.partner_share_success, Toast.LENGTH_SHORT).show();
        }
    };


}
