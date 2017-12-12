package com.hhly.partner.presentation.view.account;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.RegexUtils;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.salary.SalaryModelModifyActivity;
import com.hhly.partner.presentation.view.salary.SalaryModelModifyFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册第二步
 * Created by dell on 2017/4/13.
 */

public class RegisterStepTwoFragment extends BaseFragment implements RegisterStepTwoContract.View {
    //手机号
    public static final String EXTRA_PHONE = "extra_phone";
    //密码
    public static final String EXTRA_PASSWORD = "extra_password";
    //邀请码
    public static final String EXTRA_INVITE_CODE = "extra_invite_code";

    private RegisterStepTwoContract.Presenter mPresenter;


    @BindView(R.id.password_edit)
    EditText mPasswordEdit;
    @BindView(R.id.reinput_password_edit)
    EditText mReinputPasswordEdit;
    @BindView(R.id.generalize_code_edit)
    EditText mGeneralizeCodeEdit;
    @BindView(R.id.complete_btn)
    Button mCompleteBtn;
    //手机号
    private String mPhone;


    public static RegisterStepTwoFragment newInstance(String phone) {
        Bundle args = new Bundle();
        args.putString(EXTRA_PHONE, phone);
        RegisterStepTwoFragment fragment = new RegisterStepTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        mPhone = bundle.getString(EXTRA_PHONE);
        new RegisterStepTwoPresenter(this);
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_register_step_two;
    }

    @OnClick(R.id.complete_btn)
    public void onViewClicked() {

        if (isValidPsw(mPasswordEdit.getText().toString(), mReinputPasswordEdit.getText().toString())) {

            if (TextUtils.isEmpty(mGeneralizeCodeEdit.getText().toString().trim())) {

                Bundle bundle = new Bundle();
                bundle.putInt(SalaryModelModifyFragment.KEY_TYPE, SalaryModelModifyFragment.REGISTER);
                bundle.putString(EXTRA_PHONE, mPhone);
                bundle.putString(EXTRA_PASSWORD, mPasswordEdit.getText().toString().trim());
                bundle.putString(EXTRA_INVITE_CODE, mGeneralizeCodeEdit.getText().toString().trim());
                ActivityCompat.startActivity(getActivity(), SalaryModelModifyActivity.getCallIntent(getContext(), bundle), null);
                getActivity().finish();
            } else {
                showLoading();
                mPresenter.checkPartnerNo(mPasswordEdit.getText().toString(), mGeneralizeCodeEdit.getText().toString().trim());
            }
        }
    }

    /**
     * 验证输入的密码是否正确
     *
     * @param psw
     * @param confirmPsw
     * @return
     */
    private boolean isValidPsw(String psw, String confirmPsw) {
        if (TextUtils.isEmpty(psw) || TextUtils.isEmpty(confirmPsw)) {
            ToastUtil.showShort(getContext(), getString(R.string.please_input_password));
            return false;
        }
        if (!RegexUtils.checkPassword(mPasswordEdit.getText().toString().trim())) {
            ToastUtil.showShort(getContext(), getString(R.string.register_psw_length_error));
            return false;
        }
        if (!psw.equals(confirmPsw)) {
            ToastUtil.showShort(getContext(), getString(R.string.register_confirm_psw_error));
            return false;
        }
        return true;
    }

    @Override
    public void setPresenter(RegisterStepTwoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void checkPartnerNoSuccess(String pwd, String partnerNo) {
        dismissLoading();
        Bundle bundle = new Bundle();
        bundle.putInt(SalaryModelModifyFragment.KEY_TYPE, SalaryModelModifyFragment.REGISTER);
        bundle.putString(EXTRA_PHONE, mPhone);
        bundle.putString(EXTRA_PASSWORD, pwd);
        bundle.putString(EXTRA_INVITE_CODE, partnerNo);
        ActivityCompat.startActivity(getActivity(), SalaryModelModifyActivity.getCallIntent(getContext(), bundle), null);
        getActivity().finish();
    }

    @Override
    public void checkPartnerNoFailure(String msg) {
        dismissLoading();
        ToastUtil.showShort(getActivity(), msg);
    }
}
