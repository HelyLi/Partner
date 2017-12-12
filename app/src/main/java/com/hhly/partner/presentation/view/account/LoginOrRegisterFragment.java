package com.hhly.partner.presentation.view.account;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.hhly.partner.R;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.view.BaseFragment;

import butterknife.OnClick;

/**
 * description
 * Created by dell on 2017/4/13.
 */

public class LoginOrRegisterFragment extends BaseFragment {

    public static LoginOrRegisterFragment newInstance() {
        Bundle args = new Bundle();
        LoginOrRegisterFragment fragment = new LoginOrRegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void viewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void fetchData(boolean isLoadMore) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login_or_register;
    }


    @OnClick({R.id.loginBtn, R.id.registerBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.loginBtn:
                ActivityCompat.startActivity(getActivity(), LoginActivity.getCallIntent(getActivity()), null);
                break;
            case R.id.registerBtn:
                ActivityCompat.startActivity(getActivity(), RegisterActivity.getCallIntent(getActivity()), null);
                break;
        }
    }

}
