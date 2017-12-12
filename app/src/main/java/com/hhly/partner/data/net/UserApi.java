package com.hhly.partner.data.net;

import com.hhly.partner.data.net.protocol.user.*;
import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.List;
import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public interface UserApi {

    /**
     * 个人获取手机号
     *
     * @param params MobileReq
     * @return MobileResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/getMobile")
    Flowable<MobileResp> getMobile(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 个人获取银行卡信息
     *
     * @param params BankReq
     * @return BankResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/getBank")
    Flowable<BankResp> getBank(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 绑定银行卡信息
     *
     * @param params UpdateBankInfoReq
     * @return UpdateBankInfoResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/updateBankInfo")
    Flowable<UpdateBankInfoResp> updateBankInfo(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 修改密码
     *
     * @param params UpdatePasswordReq
     * @return UpdatePasswordResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/updatePassword")
    Flowable<UpdatePasswordResp> updatePassword(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 个人获取资产
     *
     * @param params PriceReq
     * @return PriceResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/getPrice")
    Flowable<PriceResp> getPrice(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 提现
     *
     * @param params WithdrawalReq
     * @return WithdrawalResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/addWithdrawal")
    Flowable<WithdrawalResp> addWithdrawal(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 交易明细
     *
     * @param params WithdrawalInfoReq
     * @return WithdrawalInfoResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/getWithdrawalInfo")
    Flowable<WithdrawalInfoResp> getWithdrawalInfo(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 是否设置资金密码
     *
     * @param params PayPwdReq
     * @return PayPwdResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/getPayPwd")
    Flowable<PayPwdResp> getPayPwd(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 设置资金密码
     *
     * @param params UpdatePayPwdReq
     * @return UpdatePayPwdResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/updatePayPwd")
    Flowable<UpdatePayPwdResp> updatePayPwd(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 修改资金密码
     *
     * @param params ResetPayPwdReq
     * @return ResetPayPwdResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/updatePayPwd2")
    Flowable<ResetPayPwdResp> resetPayPwd(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 收入明细
     *
     * @param params AccPriceReq
     * @return AccPriceResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/getAccPrice")
    Flowable<AccPriceResp> getAccPrice(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 收入明细详细
     *
     * @param params AccPriceInfoReq
     * @return AccPriceInfoResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/getAccPriceInfo")
    Flowable<AccPriceInfoResp> getAccPriceInfo(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 注册
     *
     * @param params RegisterReq
     * @return RegisterResp
     */
    @FormUrlEncoded
    @POST("reg/register")
    Flowable<RegisterResp> register(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 忘记密码
     *
     * @param params UpdatePwdByPhoneReq
     * @return UpdatePwdByPhoneResp
     */
    @FormUrlEncoded
    @POST("reg/updatePwdByPhone")
    Flowable<UpdatePwdByPhoneResp> updatePwdByPhone(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 退出登录
     *
     * @param params LogoutReq
     * @return LogoutResp
     */
    @FormUrlEncoded
    @POST("login/logout")
    Flowable<LogoutResp> logout(@FieldMap(encoded = true) Map<String, String> params);


    /**
     * 实名认证
     *
     * @param params IdCardReq
     * @return IdCardResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/getIdCard")
    Flowable<IdCardResp> getIdCard(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 是否实名认证
     *
     * @param params FindIdCardReq
     * @return FindIdCardResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/findIdCard")
    Flowable<FindIdCardResp> findIdCard(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 合作活动/合作通知
     *
     * @param params NoticeReq
     * @return NoticeResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/getNotice")
    Flowable<NoticeResp> getNotice(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 合作活动/合作通知  明细
     *
     * @param params NoticeByIdReq
     * @return NoticeByIdResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/getNoticeById")
    Flowable<NoticeByIdResp> getNoticeById(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 检测推广编码是否存在
     *
     * @param params CheckPartnerNoReq
     * @return CheckPartnerNoResp
     */
    @FormUrlEncoded
    @POST("reg/checkPartnerNo")
    Flowable<CheckPartnerNoResp> checkPartnerNo(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取真实姓名
     *
     * @param params GetRealNameReq
     * @return GetRealNameResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/getRealName")
    Flowable<GetRealNameResp> getRealName(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 手机号码验证是否存在
     *
     * @param params CheckAccountReq
     * @return CheckAccountResp
     */
    @FormUrlEncoded
    @POST("reg/isExistAccount")
    Flowable<CheckAccountResp> isExistAccount(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取验证码
     *
     * @param params SmsCodeReq
     * @return SmsCodeResp
     */
    @FormUrlEncoded
    @POST("code/smsCode")
    Flowable<SmsCodeResp> getSmsCode(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 验证验证码
     *
     * @param params SmsCodeReq
     * @return SmsCodeResp
     */
    @FormUrlEncoded
    @POST("code/validateSmsCode")
    Flowable<SmsCodeResp> validateSmsCode(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 登录
     *
     * @param params LoginReq
     * @return LoginResp
     */
    @FormUrlEncoded
    @POST("login/doLogin")
    Flowable<LoginResp> login(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取已设置的佣金方式
     *
     * @param params CommissionTypeReq
     * @return CommissionTypeResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/getCommissionType")
    Flowable<CommissionTypeResp> getCommissionType(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 修改已设置的佣金方式
     *
     * @param params UpdateCommissionTypeReq
     * @return UpdateCommissionTypeResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/updateCommissionType")
    Flowable<List<UpdateCommissionTypeResp>> updateCommissionType(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 注册-获取游戏类型
     *
     * @param params FindAllCommissionTypeReq
     * @return FindAllCommissionTypeResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/findAllCommissionType")
    Flowable<FindAllCommissionTypeResp> findAllCommissionType(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 统计-注册用户
     *
     * @param params AddSpreadReq
     * @return AddSpreadResp
     */
    @FormUrlEncoded
    @POST("reg/addSpread")
    Flowable<AddSpreadResp> addSpread(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取提现规则
     *
     * @param params GetWithdraWalReq
     * @return GetWithdraWalResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/getWithdraWal")
    Flowable<GetWithdraWalResp> getWithdraWal(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 判断当月是否提现
     *
     * @param params FindWithdraWalReq
     * @return FindWithdraWalResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/findWithdraWal")
    Flowable<FindWithdraWalResp> findWithdraWal(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取UserId
     *
     * @param params BaseReq
     * @return GetUserIdResp
     */
    @FormUrlEncoded
    @POST("partnerPersonal/getUserId")
    Flowable<GetUserIdResp> getUserId(@FieldMap(encoded = true) Map<String, String> params);

}
