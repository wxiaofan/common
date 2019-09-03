package com.xf.base.http.api;

import android.support.annotation.NonNull;
import android.util.Log;

import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.xf.base.BaseModel;
import com.xf.base.BuildConfig;
import com.xf.base.http.transform.BaseTransformer;
import com.xuexiang.xutil.XUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource INSTANCE;
    private static ApiServer sApiServer;
    public static final int DEFAULT_TIME_OUT = 60;
    private static OkHttpClient client;

//    public static OkHttpClient getHttpClient() {
//        return client;
//    }

    private OkHttpClient provideOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                if (BuildConfig.DEBUG) {
                    Log.e("网路请求", message);
                }
            }
        });
        final Map<String, String> headers = new HashMap<>();
        headers.put("ClientType", "android");
        headers.put("ClientVersionName", BuildConfig.VERSION_NAME);
        headers.put("ClientVersionCode", String.valueOf(BuildConfig.VERSION_CODE));
        headers.put("ClientBuildType", String.valueOf(BuildConfig.BUILD_TYPE));


        SetCookieCache cookieCache = new SetCookieCache();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        ClearableCookieJar cookieJar =
                new PersistentCookieJar(cookieCache, new SharedPrefsCookiePersistor(XUtil.getContext()));

        final OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new AccessTokenInterceptor(headers))
                .cookieJar(cookieJar)
                .connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .build();
        RemoteDataSource.client = client;
        return client;
    }

    private Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://www.baidu.com")
                .client(client)
                .build();
    }

    private RemoteDataSource() {
        Retrofit retrofit = provideRetrofit(provideOkHttpClient());
        sApiServer = retrofit.create(ApiServer.class);
    }

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            synchronized (RemoteDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RemoteDataSource();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void getBaiDu(BaseObserver<BaseModel> observer) {
        sApiServer.getBaiDu().compose(new BaseTransformer()).subscribe(observer);

    }




//    @Override
//    public void login(String phone, String areaCode, String password, BaseObserver<BaseModel> observer) {
//        sApiServer.login(phone, areaCode, password).compose(new BaseTransformer())
//                .subscribe(observer);
//    }


}
