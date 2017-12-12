package com.hhly.partner.data.net;

import android.util.SparseArray;

import com.hhly.partner.application.App;
import com.hhly.partner.data.config.SystemConfig;
import com.hhly.partner.data.net.cookie.PersistentCookieJar;
import com.hhly.partner.data.net.cookie.SetCookieCache;
import com.hhly.partner.data.net.cookie.SharedPrefsCookiePersistor;
import com.hhly.partner.presentation.utils.JsonUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * description :
 * <p>
 * Created by dell on 2017/4/11.
 */

public class RetrofitManager {
    private static SparseArray<RetrofitManager> sRetrofitManager = new SparseArray<>(ApiType.TYPE_COUNT);
    private static final int CONN_TIMEOUT = 15;
    private static final int READ_TIMEOUT = 20;
    private static final int WRITE_TIMEOUT = 20;
    private static volatile OkHttpClient sOkHttpClient;
    private static Retrofit sRetrofit;
    private static Retrofit sFileRetrofit;

    private UserApi userApi;
    private GameApi gameApi;
    private ProxyApi mProxyApi;
    private UpdateApi mUpdateApi;

    public RetrofitManager(@ApiType.ApiTypeChecker int apiType) {
        if (apiType != ApiType.FILE_API && sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(SystemConfig.get().getHost(apiType))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } else if (apiType == ApiType.FILE_API && sFileRetrofit == null) {
            sFileRetrofit = new Retrofit.Builder()
                    .client(getOkHttpClient())
                    .baseUrl(SystemConfig.get().getHost(apiType))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        createApi(apiType);
    }

    public static RetrofitManager getInstance(int apiType) {
        RetrofitManager retrofitManager = sRetrofitManager.get(apiType);
        if (retrofitManager == null) {
            retrofitManager = new RetrofitManager(apiType);
            sRetrofitManager.put(apiType, retrofitManager);
            return retrofitManager;
        }
        return retrofitManager;
    }

    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            // 指定缓存路径,缓存大小100Mb
            Cache cache = new Cache(new File(App.getContext().getCacheDir(), "HttpCache"),
                    1024 * 1024 * 100);
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            sOkHttpClient = new OkHttpClient.Builder().cache(cache)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(logInterceptor)
                    .cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getContext())))
                    .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }
        return sOkHttpClient;
    }

    private void createApi(@ApiType.ApiTypeChecker int apiType) {
        switch (apiType) {
            case ApiType.BANNER_API:
                //                createBannerApi(sRetrofit);
                break;
            case ApiType.FILE_API:
                //                createFileApi(sFileRetrofit);
                break;
            case ApiType.GAME_API:
                createGameApi(sRetrofit);
                break;
            case ApiType.GOODS_API:
                //                createGoodsApi(sRetrofit);
                break;
            case ApiType.UPDATE_API:
                createUpdateApi(sRetrofit);
                break;
            case ApiType.USER_API:
                createUserApi(sRetrofit);
                break;
            case ApiType.PAY_API:
                //                createPayApi(sRetrofit);
                break;
            case ApiType.PROXY_API:
                createProxyApi(sRetrofit);
                break;
            default:
                break;
        }
    }

    private void createProxyApi(Retrofit retrofit) {
        this.mProxyApi = retrofit.create(ProxyApi.class);
    }

    private void createUserApi(Retrofit retrofit) {
        this.userApi = retrofit.create(UserApi.class);
    }

    private void createGameApi(Retrofit retrofit) {
        this.gameApi = retrofit.create(GameApi.class);
    }

    private void createUpdateApi(Retrofit retrofit) {
        this.mUpdateApi = retrofit.create(UpdateApi.class);
    }


    public GameApi getGameApi() {
        return gameApi;
    }

    public UserApi getUserApi() {
        return userApi;
    }

    public ProxyApi getProxyApi() {
        return mProxyApi;
    }

    public UpdateApi getUpdateApi() {
        return mUpdateApi;
    }

    public static class HttpLogger implements HttpLoggingInterceptor.Logger {

        private StringBuilder mMessage = new StringBuilder();

        @Override
        public void log(String message) {
            // 请求或者响应开始
            if (message.startsWith("--> POST")) {
                mMessage.delete(0, mMessage.length());
            }
            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            if ((message.trim().startsWith("{") && message.trim().endsWith("}"))
                    || (message.startsWith("[") && message.endsWith("]"))) {

                message = JsonUtil.json(message);
            }
            mMessage.append(message.concat("\n"));
            // 请求或者响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                Logger.d(mMessage.toString());
                mMessage.delete(0, mMessage.length());
            }
        }
    }


}
