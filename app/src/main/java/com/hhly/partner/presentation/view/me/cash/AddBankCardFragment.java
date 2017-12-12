package com.hhly.partner.presentation.view.me.cash;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ActivityUtil;
import com.hhly.partner.presentation.utils.RegexUtils;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.utils.UserPrefsUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;
import com.hhly.partner.presentation.view.widget.SingleWheelView;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 添加银行卡
 * Created by dell on 2017/4/17.
 */

public class AddBankCardFragment extends BaseFragment implements IImmersiveApply
        , AddBankCardContract.View, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.select_bank_tv)
    TextView mSelectBankTv;
    @BindView(R.id.bank_card_num_edit)
    EditText mBankCardNumEdit;
    @BindView(R.id.name_tv)
    TextView mNameTv;
    @BindView(R.id.phone_edit)
    EditText mPhoneEdit;
    @BindView(R.id.auth_code_tv)
    TextView mAuthCodeTv;
    @BindView(R.id.auth_code_edit)
    EditText mAuthCodeEdit;
    @BindView(R.id.get_auth_code_tv)
    TextView mGetAuthCodeTv;
    @BindView(R.id.complete_btn)
    Button mCompleteBtn;
    @BindView(R.id.content_layout)
    LinearLayout mContentLayout;

    private boolean isCountDown;
    private AddBankCardContract.Presenter mPresenter;

    public static AddBankCardFragment newInstance() {
        Bundle args = new Bundle();

        AddBankCardFragment fragment = new AddBankCardFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new AddBankCardPresenterImpl(this);
        mRefreshLayout.setOnRefreshListener(this);
        RxTextView.textChanges(mPhoneEdit)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(@NonNull CharSequence charSequence) throws Exception {
                        if (RegexUtils.checkMobile(charSequence.toString()) && !isCountDown) {
                            mGetAuthCodeTv.setEnabled(true);
                        } else {
                            mGetAuthCodeTv.setEnabled(false);
                        }
                    }
                });
        RxView.clicks(mGetAuthCodeTv)
                .throttleFirst(1, TimeUnit.SECONDS)
                .flatMap(new Function<Object, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(@NonNull Object o) throws Exception {
                        return Observable.interval(0, 1, TimeUnit.SECONDS).take(60);
                    }
                })
                .compose(this.<Long>bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        if (aLong == 0) {
                            mPresenter.getAuthCode(mPhoneEdit.getText().toString());
                        }
                        int cur = (int) (60 - aLong - 1);
                        if (cur > 0) {
                            isCountDown = true;
                            mGetAuthCodeTv.setEnabled(false);
                            mGetAuthCodeTv.setText(getString(R.string.get_auth_cd, cur));
                        } else {
                            mGetAuthCodeTv.setEnabled(true);
                            mGetAuthCodeTv.setText(getString(R.string.reget_auth_code));
                            isCountDown = false;
                        }
                    }
                });
        RxView.clicks(mCompleteBtn).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (checkInput()) {
                            mPresenter.addBankCard(mSelectBankTv.getText().toString(),
                                    mBankCardNumEdit.getText().toString(),
                                    mNameTv.getText().toString(),
                                    mPhoneEdit.getText().toString(),
                                    mAuthCodeEdit.getText().toString());
                        }
                    }
                });
        RxView.clicks(mSelectBankTv)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        SingleWheelView dialogFragment = new SingleWheelView();
                        dialogFragment.setDatas(getResources().getStringArray(R.array.bank_names));
                        dialogFragment.setConfrimClickLisenter(new SingleWheelView.OnConfrimClickLisenter() {
                            @Override
                            public void onConfirmClicked(String s) {
                                mSelectBankTv.setText(s);
                            }
                        });
                        ActivityUtil.addFragment(getFragmentManager(), dialogFragment);
                    }
                });
    }

    private boolean checkInput() {
        if (TextUtils.isEmpty(mSelectBankTv.getText())) {
            ToastUtil.showShort(getContext(), getString(R.string.partner_personal_account_select_bank));
            return false;
        }
        if (TextUtils.isEmpty(mBankCardNumEdit.getText())) {
            ToastUtil.showShort(getContext(), getString(R.string.partner_personal_account_input_card));
            return false;
        }
        if (mBankCardNumEdit.getText().toString().trim().length() != 16 && mBankCardNumEdit.getText().toString().trim().length() != 19) {
            ToastUtil.showShort(getContext(), getString(R.string.partner_personal_account_input_card_num));
            return false;
        }
        if (!RegexUtils.checkMobile(mPhoneEdit.getText().toString().trim()) || !UserPrefsUtil.getInstance()
                .getUserName(getContext()).equals(mPhoneEdit.getText().toString().trim())) {
            ToastUtil.showShort(getContext(), getString(R.string.partner_personal_account_input_phone));
            return false;
        }
        if (TextUtils.isEmpty(mBankCardNumEdit.getText())) {
            ToastUtil.showShort(getContext(), getString(R.string.partner_personal_account_input_auth_code));
            return false;
        }
        return true;
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mRefreshLayout.setRefreshing(!isLoadMore);
        mPresenter.getRealName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_bank_card;
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


    @Override
    public void setPresenter(AddBankCardContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getRealNameSuccess(String msg) {
        mRefreshLayout.setEnabled(false);
        mContentLayout.setVisibility(View.VISIBLE);
        mNameTv.setText(msg);
    }

    @Override
    public void getRealNameFailure(String msg) {
        mContentLayout.setVisibility(View.INVISIBLE);
        ToastUtil.showShort(getContext(), msg);
    }

    @Override
    public void getAuthCodeSuccess(String msg) {
        ToastUtil.showShort(getContext(), TextUtils.isEmpty(msg) ? getString(R.string.register_get_code_success) : msg);
    }

    @Override
    public void getAuthCodeFailure(String msg) {
        ToastUtil.showShort(getContext(), msg);
    }

    @Override
    public void addBankCardSuccess(String msg) {
        ToastUtil.showShort(getContext(), msg);
        getActivity().setResult(Activity.RESULT_OK);
        getActivity().finish();
    }

    @Override
    public void addBankCardFailure(String msg) {
        ToastUtil.showShort(getContext(), msg);
    }

    @Override
    public void onRefresh() {
        fetchData(false);
    }
}
