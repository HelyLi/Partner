package com.hhly.partner.presentation.view.extension;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.github.xmxu.cf.Callback;
import com.github.xmxu.cf.Result;
import com.github.xmxu.cf.ShareResult;
import com.hhly.partner.R;
import com.hhly.partner.data.config.SystemConfig;
import com.hhly.partner.data.net.protocol.user.MobileResp;
import com.hhly.partner.presentation.utils.*;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.share.ShareContent;
import com.hhly.partner.presentation.view.share.ShareDialog;
import com.jakewharton.rxbinding2.view.RxView;
import com.orhanobut.logger.Logger;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

import java.io.File;
import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/5/3
 */

public class ExtensionFragment extends BaseFragment implements ExtensionContract.View {


    @BindView(R.id.extension_tv)
    TextView mExtensionTv;
    @BindView(R.id.extension_iv)
    ImageView mExtensionIv;
    @BindView(R.id.custom_page)
    Button mCustomPage;

    private String mUrl = "http://m.partner.13322.com/";

    public String sBasePartnerUrl = "http://m.partner.13322.com/"; //http://mpartner.1332255.com
    public String sBaseGameUrl = "http://m.game.13322.com/"; // http://mgame.1332255.com/

    private ExtensionContract.Presenter mPresenter;

    public static ExtensionFragment newInstance(int type, int id) {
        Bundle args = new Bundle();
        args.putInt(ExtensionActivity.KEY_TYPE, type);
        args.putInt(ExtensionActivity.KEY_ID, id);
        ExtensionFragment fragment = new ExtensionFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        new ExtensionPresenter(this);
        if (SystemConfig.get().isTest()) {
            sBasePartnerUrl = "http://mpartner.1332255.com/";
            sBaseGameUrl = "http://mgame.1332255.com/";
        }
        mExtensionTv.setSelected(true);
        if (getArguments().getInt(ExtensionActivity.KEY_TYPE, ExtensionActivity.EXTENSION_AGENT) == ExtensionActivity.EXTENSION_H5) {
            mCustomPage.setVisibility(View.VISIBLE);
        }
        RxView.clicks(mCustomPage).subscribe(new Consumer<Object>() {
            @Override
            public void accept(@NonNull Object o) throws Exception {
                ActivityCompat.startActivity(getContext(), CustomExtensionActivity.getCallIntent(
                        getContext()), null);
            }
        });
        fetchData(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.menu);
        item.setIcon(R.drawable.ic_proxy_generalize_share);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu) {
            //TODO 分享内容
            ToastUtil.showShort(getActivity(), "分享待开放\n" + mUrl);
            ShareContent shareContent = new ShareContent();
            ShareDialog dialog = ShareDialog.newInstance(shareContent);
            shareContent.setAppName(getString(R.string.app_name));
            //            shareContent.setImage("http://public.13322.com/23c192a0.png");
                        shareContent.setLink(mUrl);
            //            shareContent.setInviteCode(mInviteCode);
            //            shareContent.setContent(getString(R.string.lyy_third_invite, mInviteCode));
            //            shareContent.setDescription(getString(R.string.lyy_third_invite, mInviteCode));
            //            shareContent.setTitle(getString(R.string.lyy_wyx));
            ActivityUtil.addFragment(getFragmentManager(), dialog);
        }
        return super.onOptionsItemSelected(item);
    }

    private void createQRcode(String name) {
        final String filePath = getFileRoot(getActivity()) + File.separator
                + "qr_" + name + ".jpg";
        Logger.d("Flynn == > " + filePath);
        File file = new File(filePath);
        if (file.exists()) {
            mExtensionIv.setImageBitmap(BitmapFactory.decodeFile(filePath));
            return;
        }
        //二维码图片较大时，生成图片、保存文件的时间可能较长，因此放在新线程中
        Flowable.just(filePath)
                .compose(RxUtil.<String>io_io())
                .compose(this.<String>bindToLife())
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return QRCodeUtil.createQRImage(mUrl, 800, 800,
                                null,
                                filePath);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber<String>() {
                    @Override
                    public void onNext(String s) {
                        mExtensionIv.setImageBitmap(BitmapFactory.decodeFile(filePath));
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                    }
                });
        //        new Thread(new Runnable() {
        //            @Override
        //            public void run() {
        //                boolean success = QRCodeUtil.createQRImage(mUrl, 800, 800,
        //                        null,
        //                        filePath);
        //
        //                if (success) {
        //                    getActivity().runOnUiThread(new Runnable() {
        //                        @Override
        //                        public void run() {
        //                            mExtensionIv.setImageBitmap(BitmapFactory.decodeFile(filePath));
        //                        }
        //                    });
        //                }
        //            }
        //        }).start();
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        switch (getArguments().getInt(ExtensionActivity.KEY_TYPE, ExtensionActivity.EXTENSION_AGENT)) {
            case ExtensionActivity.EXTENSION_AGENT:
            case ExtensionActivity.EXTENSION_PRODUCT:
                mPresenter.getMobile();
                break;
            case ExtensionActivity.EXTENSION_H5:
                mPresenter.getUserId();
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_extension;
    }

    //文件存储根目录
    private String getFileRoot(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File external = context.getExternalFilesDir(null);
            if (external != null) {
                return external.getAbsolutePath();
            }
        }

        return context.getFilesDir().getAbsolutePath();
    }

    @Override
    public void setPresenter(ExtensionContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getMobileSuccess(List<MobileResp.DataBeanX.DataBean> list) {
        if (list != null && list.size() > 0 && !TextUtils.isEmpty(list.get(0).getPARTNER_NO())) {

            switch (getArguments().getInt(ExtensionActivity.KEY_TYPE, ExtensionActivity.EXTENSION_AGENT)) {
                case ExtensionActivity.EXTENSION_AGENT:
                    mUrl = sBasePartnerUrl + "#/register?partnerNo=" + list.get(0).getPARTNER_NO();
                    break;
                case ExtensionActivity.EXTENSION_PRODUCT:
                    mUrl = sBaseGameUrl + "#/gamelist/:" + getArguments().getInt(ExtensionActivity.KEY_ID, 0) + "?cid=" + list.get(0).getPARTNER_NO();
                    break;
            }
            mExtensionTv.setText(mUrl);
            createQRcode(list.get(0).getPARTNER_NO());
        } else {
            ToastUtil.showShort(getActivity(), R.string.partner_request_network_error);
        }
    }

    @Override
    public void getMobileFailure(String msg) {
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void getUserIdSuccess(String userId) {
        if (!TextUtils.isEmpty(userId)) {
            mUrl = sBaseGameUrl + "?partnerNo=" + userId;
            mExtensionTv.setText(mUrl);
            createQRcode(userId);
        }
    }

    @Override
    public void getUserIdFailure(String msg) {
        ToastUtil.showShort(getActivity(), msg);
    }
}
