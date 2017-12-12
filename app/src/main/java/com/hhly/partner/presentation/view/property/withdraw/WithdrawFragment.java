package com.hhly.partner.presentation.view.property.withdraw;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.hhly.partner.R;
import com.hhly.partner.data.net.protocol.user.BankResp;
import com.hhly.partner.data.net.protocol.user.GetWithdraWalResp;
import com.hhly.partner.data.net.protocol.user.PriceResp;
import com.hhly.partner.data.net.protocol.user.WithdrawalResp;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.property.selectbank.SelectBankActivity;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextSwitcher;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewAfterTextChangeEvent;
import com.orhanobut.logger.Logger;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import org.w3c.dom.Text;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * description :
 * Created by Flynn
 * 2017/4/28
 */

public class WithdrawFragment extends BaseFragment implements WithdrawContract.View {

    @BindView(R.id.rmb_tv)
    TextView mRmbTv;
    @BindView(R.id.withdraw_description)
    TextView mWithdrawDescription;
    @BindView(R.id.bank_name_tv)
    TextView mBankNameTv;
    @BindView(R.id.withdraw_money_et)
    EditText mWithdrawMoneyEt;
    @BindView(R.id.withdraw_pwd_et)
    EditText mWithdrawPwdEt;
    @BindView(R.id.withdraw_btn)
    Button mWithdrawBtn;
    @BindView(R.id.can_withdraw_money)
    TextView mCanWithdrawMoney;

    private WithdrawContract.Presenter mPresenter;

    private int money;

    public static WithdrawFragment newInstance() {
        Bundle args = new Bundle();
        WithdrawFragment fragment = new WithdrawFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new WithdrawPresenter(this);
        char symbol = 165;
        mRmbTv.setText(String.valueOf(symbol));
        mWithdrawDescription.setText(Html.fromHtml(getString(R.string.home_property_withdraw_description, "200", "1", "25")));
        mCanWithdrawMoney.setText(Html.fromHtml(getString(R.string.home_property_withdraw_can_money, "0.00")));
        initViewClick();
        fetchData(false);
    }

    private void initViewClick() {
        RxView.clicks(mBankNameTv)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        startActivityForResult(SelectBankActivity.getCallIntent(getActivity()), 100);
                    }
                });
        mWithdrawMoneyEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String temp = s.toString();
                int posDot = s.toString().lastIndexOf(".");
                if (posDot < 0) {
                    return;
                }
                if (posDot == 0) {
                    s.insert(0, "0");
                }
                if (temp.length() - posDot - 1 > 2) {
                    s.delete(posDot + 3, posDot + 4);
                }
            }
        });
        RxView.clicks(mWithdrawBtn)
                .throttleFirst(1, TimeUnit.SECONDS)
                .filter(new Predicate<Object>() {
                    @Override
                    public boolean test(@NonNull Object o) throws Exception {
                        return checkParam();
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        mWithdrawBtn.setEnabled(false);
                        mPresenter.addWithdrawal(Double.parseDouble(mWithdrawMoneyEt.getText().toString().trim()), mWithdrawPwdEt.getText().toString().trim());
                    }
                });

    }

    private boolean checkParam() {
        String canMoney = mCanWithdrawMoney.getText().toString().trim().substring(
                mCanWithdrawMoney.getText().toString().trim().lastIndexOf(":") + 1,
                mCanWithdrawMoney.getText().toString().trim().length());
        if (TextUtils.isEmpty(mWithdrawMoneyEt.getText().toString().trim())) {
            ToastUtil.showShort(getActivity(), R.string.home_property_withdraw_money_empty);
            return false;
        } else if (Double.parseDouble(mWithdrawMoneyEt.getText().toString().trim())
                > Double.parseDouble(canMoney.replace(Html.fromHtml("&nbsp;"), ""))) {
            ToastUtil.showShort(getActivity(), R.string.home_property_withdraw_money_big);
            return false;
        } else if (Double.parseDouble(mWithdrawMoneyEt.getText().toString().trim()) < (money > 0 ? money : 200)) {
            ToastUtil.showShort(getActivity(), getString(R.string.home_property_withdraw_money_litter, money > 0 ? money : 200));
            return false;
        } else if (TextUtils.isEmpty(mWithdrawPwdEt.getText().toString().trim())) {
            ToastUtil.showShort(getActivity(), R.string.home_property_withdraw_password_empty);
            return false;
        }
        return true;
    }

    @Override
    protected void fetchData(boolean isLoadMore) {
        mPresenter.getBank();
        mPresenter.getPriceData();
        mPresenter.getWithdraWal();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_withdraw;
    }

    @Override
    public void setPresenter(WithdrawContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getBankSuccess(List<BankResp.DataBeanX.DataBean> list) {
        if (list != null && list.size() > 0 && !TextUtils.isEmpty(list.get(0).getACC_NO()) && !TextUtils.isEmpty(list.get(0).getBANK_ID())) {
            mBankNameTv.setText(list.get(0).getBANK_ID()
                    + "("
                    + list.get(0).getACC_NO().substring(list.get(0).getACC_NO().length() - 4, list.get(0).getACC_NO().length())
                    + ")");
        }
    }

    @Override
    public void getBankFailure(String msg) {
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void getPriceSuccess(List<PriceResp.DataBeanX.DataBean> list) {
        if (list != null && list.size() > 0) {
            mCanWithdrawMoney.setText(Html.fromHtml(getString(R.string.home_property_withdraw_can_money, String.format("%.2f", list.get(0).getAMOUNT()))));
        }
    }

    @Override
    public void getPriceFailure(String msg) {
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void getWithdraWalSuccess(GetWithdraWalResp.CurrentWithdrawalConfBean bean) {
        if (bean != null) {
            money = bean.getLowPrice();
            mWithdrawDescription.setText(Html.fromHtml(
                    getString(
                            R.string.home_property_withdraw_description,
                            String.valueOf(bean.getLowPrice()),
                            bean.getStartDate1(),
                            bean.getEndDate1())));
        }
    }

    @Override
    public void getWithdraWalFailure(String msg) {
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void addWithdrawalSuccess(WithdrawalResp.DataBean dataBean) {
        //提现成功
        //dataBean.getOrderNo() + dataBean.getWithdrawalPrice()
        mWithdrawBtn.setEnabled(true);
        ToastUtil.showShort(getActivity(), getString(R.string.home_my_assets_withdraw_success));
        getActivity().finish();
    }

    @Override
    public void addWithdrawalFailure(String msg) {
        mWithdrawBtn.setEnabled(true);
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void findWithdraWalFailure(String msg) {
        mWithdrawBtn.setEnabled(true);
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void getPayPwdFailure(String msg) {
        mWithdrawBtn.setEnabled(true);
        ToastUtil.showShort(getActivity(), msg);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.getBank();
    }
}
