package com.hhly.partner.data.net;

import com.hhly.partner.data.net.protocol.game.AddBannerResp;
import com.hhly.partner.data.net.protocol.game.DeleteGameCustomizationResp;
import com.hhly.partner.data.net.protocol.game.GameCustomizationResp;
import com.hhly.partner.data.net.protocol.game.GameDataByNameResp;
import com.hhly.partner.data.net.protocol.game.GameDataInfoResp;
import com.hhly.partner.data.net.protocol.game.GameDataResp;
import com.hhly.partner.data.net.protocol.game.GameTypeDataResp;
import com.hhly.partner.data.net.protocol.game.GetBannerListByUseridResp;
import com.hhly.partner.data.net.protocol.game.GetBannerListResp;
import com.hhly.partner.data.net.protocol.game.GetGameCustomizationResp;
import com.hhly.partner.data.net.protocol.game.GetIndexlbtResp;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * description :
 * Created by Flynn
 * 2017/4/13
 */
public interface GameApi {

    /**
     * 获取游戏
     *
     * @param params GameDataReq
     * @return GameDataResp
     */
    @FormUrlEncoded
    @POST("partnerGame/gameData")
    Flowable<GameDataResp> getGameData(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 获取游戏
     *
     * @param params GameDataReq
     * @return
     */
    @FormUrlEncoded
    @POST("partnerGame/gameData")
    Flowable<GameTypeDataResp> getGameDataByType(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 游戏详情
     *
     * @param params GameDataInfoReq
     * @return GameDataInfoResp
     */
    @FormUrlEncoded
    @POST("partnerGame/gameDataInfo")
    Flowable<GameDataInfoResp> getGameDataInfo(@FieldMap(encoded = true) Map<String, String> params);


    /**
     * 游戏搜索
     *
     * @param params GameDataByNameReq
     * @return GameDataByNameResp
     */
    @FormUrlEncoded
    @POST("partnerGame/getGameDataByName")
    Flowable<GameDataByNameResp> getGameDataByName(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 首页定制化-添加游戏
     *
     * @param params GameCustomizationReq
     * @return GameCustomizationResp
     */
    @FormUrlEncoded
    @POST("partnerGame/addGameCustomization")
    Flowable<GameCustomizationResp> addGameCustomization(@FieldMap(encoded = true) Map<String, String> params);


    /**
     * 首页定制化-删除游戏
     *
     * @param params DeleteGameCustomizationReq
     * @return DeleteGameCustomizationResp
     */
    @FormUrlEncoded
    @POST("partnerGame/deleteGameCustomization")
    Flowable<DeleteGameCustomizationResp> deleteGameCustomization(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 首页定制化获取banner
     *
     * @param params GetBannerListReq
     * @return GetBannerListResp
     */
    @FormUrlEncoded
    @POST("partnerGame/getBannerList")
    Flowable<GetBannerListResp> getBannerList(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 首页定制化-添加banner
     *
     * @param params AddBannerReq
     * @return AddBannerResp
     */
    @FormUrlEncoded
    @POST("partnerGame/addBanner")
    Flowable<AddBannerResp> addBanner(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 首页定制化-查看已添加banner
     *
     * @param params GetBannerListByUseridReq
     * @return GetBannerListByUseridResp
     */
    @FormUrlEncoded
    @POST("partnerGame/getBannerListByUserid")
    Flowable<GetBannerListByUseridResp> getBannerListByUserid(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 首页定制化-查看已添加自定义游戏
     *
     * @param params GetGameCustomizationReq
     * @return GetGameCustomizationResp
     */
    @FormUrlEncoded
    @POST("partnerGame/getGameCustomization")
    Flowable<GetGameCustomizationResp> getGameCustomization(@FieldMap(encoded = true) Map<String, String> params);

    /**
     * 活动中心
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("partnerApp/getIndexlbt")
    Flowable<GetIndexlbtResp> getIndexlbt(@FieldMap(encoded = true) Map<String, String> params);
}
