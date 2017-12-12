package com.hhly.partner.presentation.view.salary;

import com.hhly.partner.R;
import com.hhly.partner.data.net.ApiType;
import com.hhly.partner.data.net.RetrofitManager;
import com.hhly.partner.data.net.UserApi;
import com.hhly.partner.data.net.protocol.user.CommissionTypeReq;
import com.hhly.partner.data.net.protocol.user.CommissionTypeResp;
import com.hhly.partner.data.net.protocol.user.FindAllCommissionTypeReq;
import com.hhly.partner.data.net.protocol.user.FindAllCommissionTypeResp;
import com.hhly.partner.data.net.protocol.user.RegisterReq;
import com.hhly.partner.data.net.protocol.user.RegisterResp;
import com.hhly.partner.data.net.protocol.user.UpdateCommissionTypeReq;
import com.hhly.partner.data.net.protocol.user.UpdateCommissionTypeResp;
import com.hhly.partner.presentation.utils.BaseSubscriber;
import com.hhly.partner.presentation.utils.MD5;
import com.hhly.partner.presentation.utils.RxUtil;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * description :
 * Created by Flynn
 * 2017/4/26
 */

public class SalaryModelModifyPresenter implements SalaryModelModifyContract.Presenter {

    private final UserApi mUserApi;
    private SalaryModelModifyContract.View mView;

    public SalaryModelModifyPresenter(SalaryModelModifyContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mUserApi = RetrofitManager.getInstance(ApiType.USER_API).getUserApi();
    }

    @Override
    public void findAllCommissionType() {
        FindAllCommissionTypeReq req = new FindAllCommissionTypeReq();
        mUserApi.findAllCommissionType(req.params())
                .compose(mView.<FindAllCommissionTypeResp>bindToLife())
                .compose(RxUtil.<FindAllCommissionTypeResp>io_main())
                .subscribe(new BaseSubscriber<FindAllCommissionTypeResp>() {
                    @Override
                    public void onNext(FindAllCommissionTypeResp resp) {
                        if (resp.isOk()) {
                            mView.findAllCommissionTypeSuccess(resp.getData().getData());
                        } else {
                            mView.findAllCommissionTypeFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.findAllCommissionTypeFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    @Override
    public void getCommissionType() {
        CommissionTypeReq req = new CommissionTypeReq();
        mUserApi.getCommissionType(req.params())
                .compose(mView.<CommissionTypeResp>bindToLife())
                .compose(RxUtil.<CommissionTypeResp>io_main())
                .subscribe(new BaseSubscriber<CommissionTypeResp>() {
                    @Override
                    public void onNext(CommissionTypeResp resp) {

                        if (resp.isOk()) {
                            mView.getCommissionTypeSuccess(resp.getData().getData());
                        } else {
                            mView.getCommissionTypeFailure(resp.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.getCommissionTypeFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    @Override
    public void updateCommissionType(String commissionChoseInfo) {
        UpdateCommissionTypeReq req = new UpdateCommissionTypeReq();
        req.setCommissionChoseInfo(commissionChoseInfo);
        mUserApi.updateCommissionType(req.params())
                .compose(mView.<List<UpdateCommissionTypeResp>>bindToLife())
                .compose(RxUtil.<List<UpdateCommissionTypeResp>>io_main())
                .subscribe(new BaseSubscriber<List<UpdateCommissionTypeResp>>() {
                    @Override
                    public void onNext(List<UpdateCommissionTypeResp> list) {
                        if (list.get(0).isOk()) {
                            mView.updateCommissionTypeSuccess();
                        } else {
                            mView.updateCommissionTypeFailure(list.get(0).getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        Logger.e(t.getMessage());
                        mView.updateCommissionTypeFailure(mView.getContext().getString(R.string.partner_request_network_error));
                    }
                });
    }

    /**
     * 注册
     *
     * @param phone               手机号
     * @param password            密码
     * @param inviteCode          邀请码
     * @param commissionChoseInfo 选择的佣金模式信息
     */
    @Override
    public void register(String phone, String password, String inviteCode, String commissionChoseInfo) {
        RegisterReq registerReq = new RegisterReq();
        registerReq.setMobile(phone);
        registerReq.setPwd(new MD5(password).getMd5_32());
        registerReq.setCommissionChoseInfo(commissionChoseInfo);
        registerReq.setSpreadNo(inviteCode);
        mUserApi.register(registerReq.params())
                .compose(RxUtil.<RegisterResp>io_main())
                .compose(mView.<RegisterResp>bindToLife())
                .subscribe(new BaseSubscriber<RegisterResp>() {
                    @Override
                    public void onNext(RegisterResp registerResp) {
                        if (registerResp.isOk() && registerResp.getData().getResult() == 0) {
                            mView.onRegisterSuccess(mView.getContext().getString(R.string.register_success));
                        } else {
                            mView.onRegisterFail(mView.getContext().getString(R.string.register_fail));
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        mView.onRegisterFail(mView.getContext().getString(R.string.register_fail));
                    }
                });
    }

}
