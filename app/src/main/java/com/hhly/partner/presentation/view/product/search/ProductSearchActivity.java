package com.hhly.partner.presentation.view.product.search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.hhly.partner.R;
import com.hhly.partner.presentation.view.BaseActivity;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 产品搜索
 * Created by dell on 2017/4/26.
 */

public class ProductSearchActivity extends BaseActivity implements IImmersiveApply {
    @BindView(R.id.toolbar_title_tv)
    EditText mToolbarTitleTv;
    @BindView(R.id.clear_iv)
    ImageView mClearIv;
    //进入该界面的搜索类型key（推广、产品）
    public static final String TYPE_EXTRA_KEY = "type_extra_key";
    //推广传递的extra
    public static final String EXTENSION_EXTRA_KEY = "extension_extra_key";
    private ProductSearchInitFragment mSearchInitFragment;
    private ProductSearchResultFragment mSearchResultFragment;

    public static Intent getCallIntent(Context context, @SearchType int type) {
        Intent intent = new Intent(context, ProductSearchActivity.class);
        intent.putExtra(TYPE_EXTRA_KEY, type);
        return intent;
    }

    public static Intent getCallIntent(Context context, @SearchType int type, ExtensionExtraItem item) {
        Intent intent = new Intent(context, ProductSearchActivity.class);
        intent.putExtra(TYPE_EXTRA_KEY, type);
        intent.putExtra(EXTENSION_EXTRA_KEY, item);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbarHelper.setTitle("");
        mToolbarHelper.getToolbar().setNavigationIcon(null);
        Intent intent = getIntent();
        int searchType = intent.getIntExtra(TYPE_EXTRA_KEY, SearchType.TYPE_PRODUCT_SEARCH);
        ExtensionExtraItem extensionExtraItem = intent.getParcelableExtra(EXTENSION_EXTRA_KEY);
        mSearchInitFragment = ProductSearchInitFragment.newInstance(searchType, extensionExtraItem);
        mSearchResultFragment = ProductSearchResultFragment.newInstance(searchType,extensionExtraItem);
        RxTextView.textChanges(mToolbarTitleTv).debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {
                        if (TextUtils.isEmpty(charSequence)) {
                            mClearIv.setVisibility(View.GONE);
                            switchToInitOrResultFrame(true, null);
                        } else {
                            mClearIv.setVisibility(View.VISIBLE);
                            switchToInitOrResultFrame(false, charSequence.toString().trim());
                        }
                    }
                });
    }

    /**
     * 跳转到搜索初始化或结果界面
     *
     * @param isInit    true 为跳转初始化界面
     * @param searchKey 搜索的key
     */
    private void switchToInitOrResultFrame(boolean isInit, String searchKey) {
        if (isInit) {
            showAndHideFragment(mSearchInitFragment, mSearchResultFragment);
        } else {
            showAndHideFragment(mSearchResultFragment, mSearchInitFragment);
            mSearchResultFragment.setSearchKey(searchKey);
        }
        mSearchInitFragment.setForceUpdate(isInit);
        mSearchInitFragment.setUserVisibleHint(isInit);
        mSearchResultFragment.setForceUpdate(!isInit);
        mSearchResultFragment.setUserVisibleHint(!isInit);

    }

    /**
     * 显示和隐藏fragment
     *
     * @param showFragment 要显示的
     * @param hideFragment 要隐藏的
     */
    private void showAndHideFragment(Fragment showFragment, Fragment hideFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!showFragment.isAdded()) {
            transaction.add(R.id.content_container, showFragment);
        } else {
            transaction.show(showFragment);
        }
        if (hideFragment.isAdded()) {
            transaction.hide(hideFragment);
        }
        transaction.commitAllowingStateLoss();
    }

    @OnClick({R.id.cancel_tv, R.id.clear_iv})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.cancel_tv:
                onBackPressed();
                break;
            case R.id.clear_iv:
                mToolbarTitleTv.setText("");
                break;
        }
    }

    private int[] mLocationArr;

    @Override
    protected boolean enableHideInputSoft(int x, int y) {
        if (mLocationArr == null) {
            mLocationArr = new int[2];
        }
        mClearIv.getLocationInWindow(mLocationArr);
        return !new Rect(mLocationArr[0], mLocationArr[1], mLocationArr[0] + mClearIv.getWidth(),
                mLocationArr[1] + mClearIv.getHeight()).contains(x, y);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_search;
    }

    @Override
    public boolean applyImmersive() {
        return true;
    }

    @Override
    public boolean applyScroll() {
        return false;
    }

    @Override
    public float initAlpha() {
        return 1;
    }
}
