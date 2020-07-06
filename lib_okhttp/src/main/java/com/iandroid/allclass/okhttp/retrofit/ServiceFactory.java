package com.iandroid.allclass.okhttp.retrofit;


import com.iandroid.allclass.okhttp.HttpConfig;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by david on 2019/6/24.
 */
public class ServiceFactory {

    private static volatile Retrofit sDefaultRetrofit;
    public static HttpConfig sConfig;
    private static Map<String, Object> sServices = new HashMap<>();

    static void init(HttpConfig config) {
        sConfig = config;
        buildDefaultRetrofit();
    }

    private static void buildDefaultRetrofit() {
        if (sDefaultRetrofit == null) {
            synchronized (ServiceFactory.class) {
                if (sDefaultRetrofit == null) {
                    sDefaultRetrofit = new Retrofit.Builder()
                            .client(OkHttpClientFactory.buildApiClient(sConfig.getOkHttpInterceptors()))
                            .addConverterFactory(FastJsonConverterFactory.create())
                            .addCallAdapterFactory(HttpCallAdapter.Factory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                            .baseUrl(sConfig.getBaseUrl())
                            .build();
                }
            }
        }
    }

    public static <T> T get(Class<T> clazz) {
        buildDefaultRetrofit();

        T service = null;
        String className = clazz.getCanonicalName();
        try {
            if (sServices.containsKey(className))
                service = (T) sServices.get(className);
        } catch (Exception ignored) {
        }

        if (service == null) {
            service = sDefaultRetrofit.create(clazz);
            sServices.put(className, service);
        }
        return service;
    }
}
