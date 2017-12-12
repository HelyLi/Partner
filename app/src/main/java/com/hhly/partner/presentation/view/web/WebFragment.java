package com.hhly.partner.presentation.view.web;

import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.View;
import android.webkit.*;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.hhly.partner.R;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.rule.PartnerAgentActivity;

/**
 * description :
 * Created by Flynn
 * 2017/5/3
 */

public class WebFragment extends BaseFragment {


    @BindView(R.id.pb)
    ProgressBar mPb;
    @BindView(R.id.webView)
    WebView mWebView;

    private String mUrl;

    public static WebFragment newInstance(int type) {
        Bundle args = new Bundle();
        WebFragment fragment = new WebFragment();
        args.putInt(PartnerAgentActivity.KEY, type);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        int type = getArguments().getInt(PartnerAgentActivity.KEY, 0);
        switch (type) {
            case 0:
                mUrl = "http://m.partner.13322.com/#/growup2";
                break;
            case 1:
                mUrl = "http://m.partner.13322.com/#/income2";
                break;
        }
        initWebView();
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_web_view_layout;
    }

    private void initWebView() {
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setAllowFileAccess(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDomStorageEnabled(true);

        mWebView.setWebChromeClient(new MWebViewClient());
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

        });
        mWebView.loadUrl(Uri.parse(mUrl).toString());
    }

    private class MWebViewClient extends WebChromeClient {

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            result.confirm();
            return true;
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (mPb != null) {
                mPb.setProgress(newProgress);
                if (newProgress == 100) {
                    mPb.setVisibility(View.GONE);
                }
            }
            super.onProgressChanged(view, newProgress);
        }
    }

}
