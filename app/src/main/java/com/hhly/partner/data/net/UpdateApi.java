package com.hhly.partner.data.net;

import com.hhly.partner.data.net.protocol.update.CheckUpdateResp;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 版本更新
 * Created by dell on 2017/5/17.
 */

public interface UpdateApi {
    /**
     * 获取版本更新信息
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("partnerApp/getAppVersion")
    Flowable<CheckUpdateResp> getUpdateInfo(@FieldMap(encoded = true) Map<String, String> params);

    @GET
    @Streaming
    Flowable<ResponseBody> download(@Url String url);
}
