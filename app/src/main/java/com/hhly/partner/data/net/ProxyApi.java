package com.hhly.partner.data.net;

import com.hhly.partner.data.net.protocol.proxy.*;
import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.util.Map;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public interface ProxyApi {
    /**
     * 首页
     *
     * @param params IndexDataReq
     * @return IndexDataResp
     */
    @FormUrlEncoded
    @POST("partnerApp/indexData")
    Flowable<IndexDataResp> getIndexData(@FieldMap(encoded = true) Map<String, String> params);


    /**
     * 首页-今日获得金额-收入统计
     *
     * @param params GetTodayRechargeSumReq
     * @return GetTodayRechargeSumResp
     */
    @FormUrlEncoded
    @POST("partnerApp/getTodayRechargeSum")
    Flowable<GetTodayRechargeSumResp> getTodayRechargeSum(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 首页--代理人数
     *
     * @param params GetProxyNumsDataReq
     * @return GetProxyNumsDataResp
     */
    @FormUrlEncoded
    @POST("partnerApp/getProxyNumsData")
    Flowable<GetProxyNumsDataResp> getProxyNumsData(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 下级代理 曲线图
     *
     * @param params AppUnderAgentsNumByTimeareaReq
     * @return AppUnderAgentsNumByTimeareaResp
     */
    @FormUrlEncoded
    @POST("partnerApp/queryAppUnderAgentsNumByTimearea")
    Flowable<AppUnderAgentsNumByTimeareaResp> queryAppUnderAgentsNumByTimearea(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 下下级代理 曲线图
     * 由 AppUunderAgentsNumByTimeareaReq 改为  AppUnderAgentsNumByTimeareaReq
     * 由 AppUunderAgentsNumByTimeareaResp 改为  AppUnderAgentsNumByTimeareaResp
     *
     * @param params AppUunderAgentsNumByTimeareaReq
     * @return AppUunderAgentsNumByTimeareaResp
     */
    @FormUrlEncoded
    @POST("partnerApp/queryAppUunderAgentsNumByTimearea")
    Flowable<AppUnderAgentsNumByTimeareaResp> queryAppUunderAgentsNumByTimearea(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 直属会员 曲线图
     * 由 AppMemberNumByTimeAreaReq 改为 AppUnderAgentsNumByTimeareaReq
     * 由 AppMemberNumByTimeAreaResp 改为 AppUnderAgentsNumByTimeareaResp
     *
     * @param params AppMemberNumByTimeAreaReq
     * @return AppMemberNumByTimeAreaResp
     */
    @FormUrlEncoded
    @POST("partnerApp/queryAppMemberNumByTimeArea")
    Flowable<AppUnderAgentsNumByTimeareaResp> queryAppMemberNumByTimeArea(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 首页--代理人数--我的代理
     *
     * @param params MyProxyDataReq
     * @return MyProxyDataResp
     */
    @FormUrlEncoded
    @POST("partnerApp/getMyProxyData")
    Flowable<MyProxyDataResp> getMyProxyData(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 首页--代理人数--直属会员 [日]
     * <p>
     * GetMemberDaySumReq  换成 GetMembersRechargeSumReq
     * GetMemberDaySumResp 换成 GetMembersRechargeSumResp
     *
     * @param params GetMemberDaySumReq
     * @return GetMemberDaySumResp
     */
    @FormUrlEncoded
    @POST("partnerApp/getMemberDaySum")
    Flowable<GetMembersRechargeSumResp> getMemberDaySum(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 首页--代理人数--直属会员 [周]
     * <p>
     * GetMemberWeekSumReq  换成 GetMembersRechargeSumReq
     * GetMemberWeekSumResp 换成 GetMembersRechargeSumResp
     *
     * @param params GetMemberWeekSumReq
     * @return GetMemberWeekSumResp
     */
    @FormUrlEncoded
    @POST("partnerApp/getMemberWeekSum")
    Flowable<GetMembersRechargeSumResp> getMemberWeekSum(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 首页--代理人数--直属会员 [月]
     * <p>
     * GetMemberMonthSumReq  换成 GetMembersRechargeSumReq
     * GetMemberMonthSumResp 换成 GetMembersRechargeSumResp
     *
     * @param params GetMemberMonthSumReq
     * @return GetMemberMonthSumResp
     */
    @FormUrlEncoded
    @POST("partnerApp/getMemberMonthSum")
    Flowable<GetMembersRechargeSumResp> getMemberMonthSum(@FieldMap(encoded = true) Map<String, String> params);


    /**
     * [所有-注册日期也用这个]
     *
     * @param params GetMembersRechargeSumReq
     * @return GetMembersRechargeSumResp
     */
    @FormUrlEncoded
    @POST("partnerApp/getMembersRechargeSum")
    Flowable<GetMembersRechargeSumResp> getMembersRechargeSum(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 首页-付费次数
     *
     * @param params MembersAgentsRechargeNumReq
     * @return MembersAgentsRechargeNumResp
     */
    @FormUrlEncoded
    @POST("partnerApp/getMembersAgentsRechargeNum")
    Flowable<MembersAgentsRechargeNumResp> getMembersAgentsRechargeNum(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 曲线图
     * <p>
     * 由 MembersAgentsRechargeNumGraphResp 改为 AppUnderAgentsNumByTimeareaResp
     *
     * @param params MembersAgentsRechargeNumGraphReq
     * @return MembersAgentsRechargeNumGraphResp
     */
    @FormUrlEncoded
    @POST("partnerApp/getMembersAgentsRechargeNumGraph")
    Flowable<AppUnderAgentsNumByTimeareaResp> getMembersAgentsRechargeNumGraph(@FieldMap(encoded = true) Map<String, String> params);


    /**
     * 首页-付费次数-我的代理
     *
     * @param params RechargeByUnderAgentsDetailReq
     * @return RechargeByUnderAgentsDetailResp
     */
    @FormUrlEncoded
    @POST("partnerApp/getRechargeByUnderAgentsDetail")
    Flowable<RechargeByUnderAgentsDetailResp> getRechargeByUnderAgentsDetail(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 首页-付费次数-直属会员
     * <p>
     * RechargeByMemberDetailResp 改成 GetMembersRechargeSumResp
     *
     * @param params RechargeByMemberDetailReq
     * @return RechargeByMemberDetailResp
     */
    @FormUrlEncoded
    @POST("partnerApp/getRechargeByMemberDetail")
    Flowable<GetMembersRechargeSumResp> getRechargeByMemberDetail(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 代理产品
     *
     * @param params AppAgentProductRechargeTopNineReq
     * @return AppAgentProductRechargeTopNineResp
     */
    @FormUrlEncoded
    @POST("partnerApp/queryAppAgentProductRechargeTopNine")
    Flowable<AppAgentProductRechargeTopNineResp> queryAppAgentProductRechargeTopNine(@FieldMap(encoded = true) Map<String, String> params);


    /**
     * queryAppAgentProductRechargeGraph
     *
     * @param params AppAgentProductRechargeGraphReq
     * @return AppAgentProductRechargeGraphResp
     */
    @FormUrlEncoded
    @POST("partnerApp/queryAppAgentProductRechargeGraph")
    Flowable<AppAgentProductRechargeGraphResp> queryAppAgentProductRechargeGraph(@FieldMap(encoded = true) Map<String, String> params);


}
