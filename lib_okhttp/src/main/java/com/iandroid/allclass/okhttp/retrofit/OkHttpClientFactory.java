package com.iandroid.allclass.okhttp.retrofit;


import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by david on 2019/6/25.
 */
public class OkHttpClientFactory {

    private static final int TIMEOUT_SECOND_API = 15;

    public static OkHttpClient buildApiClient(List<Interceptor> interceptors) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(TIMEOUT_SECOND_API, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_SECOND_API, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECOND_API, TimeUnit.SECONDS);
        if (interceptors != null && interceptors.size() > 0) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }
        return builder.build();
    }

    public static Interceptor getLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
