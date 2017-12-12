package com.hhly.partner.presentation.view.me.setting;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.UpdatePasswordReq;
import com.hhly.partner.data.net.protocol.user.UpdatePasswordResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.MD5;
import com.hhly.partner.presentation.utils.RxUtil;
import com.hhly.partner.presentation.utils.ToastUtil;
import com.hhly.partner.presentation.utils.UserPrefsUtil;
import com.hhly.partner.presentation.view.main.LoginHandle;
import com.orhanobut.logger.Logger;

/**
 * 修改密码presenterImpl
 * Created by dell on 2017/5/2.
 */

public class ModifyPswPresenterImpl implements AccountSettingContract.ModifyPswPresenter {
    private AccountSettingContract.ModifyPswView mView;
    private UserApi mUserApi;

    public ModifyPswPresenterImpl(AccountSettingContract.ModifyPswView view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void modifyPsw(String oldPassword, final String newPassword) {
        UpdatePasswordReq req = new UpdatePasswordReq();
        req.setPwd(new MD5(oldPassword).getMd5_32());
        final String userName = UserPrefsUtil.getInstance().getUserName(mView.getContext());
        req.setMobile(userName);
        req.setNewPwd(new MD5(newPassword).getMd5_32());
        mUserApi.updatePassword(req.params())
                .compose(RxUtil.<UpdatePasswordResp>io_main())
                .compose(mView.<UpdatePasswordResp>bindToLife())
                .subscribe(new BaseSubscriber<UpdatePasswordResp>() {
                    @Override
                    public void onNext(UpdatePasswordResp resp) {
                        if (resp != null && resp.isOk() && resp.getData() != null && 1 == resp.getData().getData()) {
                            UserPrefsUtil.getInstance().saveUserNamePwd(mView.getContext(), userName, newPassword);
                            mView.modifyPswSuccess(mView.getContext().getString(
                                    R.string.partner_personal_account_modify_psw_success));
                        } else {
                            boolean isPswError = resp != null && resp.getData() != null && 0 == resp.getData().getData();
                            mView.modifyPswFailure(isPswError ? mView.getContext().getString(R.string.partner_personal_account_error_old_psw)
                                    : mView.getContext().getString(R.string.partner_personal_account_modify_psw_fail));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e("onError:" + t.getMessage());
                        ToastUtil.showShort(mView.getContext(), mView.getContext().getString(R.string.partner_request_error));
                    }
                });
    }
}
