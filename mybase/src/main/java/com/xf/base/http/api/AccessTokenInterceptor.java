package com.xf.base.http.api;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AccessTokenInterceptor implements Interceptor {

    private static final Map<String, String> mHeaderMap = new ConcurrentHashMap<>();


    public AccessTokenInterceptor(Map<String, String> headers) {
        this.mHeaderMap.putAll(headers);
    }


    public static void addFormToken(String text) {
        mHeaderMap.put("formtoken", text);
    }

    public Map<String, String> getHeaders() {
        return mHeaderMap;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest = request.newBuilder().headers(Headers.of(mHeaderMap)).build();
        Headers headers = newRequest.headers();
        Response response = chain.proceed(newRequest);
        return response;
    }
}
