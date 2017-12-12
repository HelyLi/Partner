package com.hhly.partner.presentation.view.me.code;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;
import com.hhly.partner.presentation.view.immersive.IImmersiveApply;

import butterknife.BindView;

/**
 * 邀请码
 * Created by dell on 2017/5/2.
 */

public class InviteCodeFragment extends BaseFragment implements IImmersiveApply, InviteCodeContract.View {
    @BindView(R.id.invite_code_tv)
    TextView mInviteCodeTv;
    private InviteCodeContract.Presenter mPresenter;

    public static InviteCodeFragment newInstance() {

        Bundle args = new Bundle();

        InviteCodeFragment fragment = new InviteCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {
        new InviteCodePresenterImpl(this);
        mPresenter.getInviteCode();
    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_invite_code;
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
    public void setPresenter(InviteCodeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getInviteCodeSuccess(String inviteCode) {
        mInviteCodeTv.setText(inviteCode);
    }

    @Override
    public void getInviteCodeFail(String msg) {
        ToastUtil.showShort(getContext(), msg);
    }
}
