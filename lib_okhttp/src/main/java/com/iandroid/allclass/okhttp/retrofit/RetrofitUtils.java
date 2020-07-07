package com.iandroid.allclass.okhttp.retrofit;

import com.iandroid.allclass.okhttp.HttpConfig;
import com.iandroid.allclass.okhttp.interceptor.DefaultErrorHandler;
import com.iandroid.allclass.okhttp.interceptor.DefaultInterceptor;

/**
 * created by wangkm
 * on 2020-7-7
 */
public class RetrofitUtils {

    public static void init(HttpConfig config) {
        ServiceFactory.init(config);
        DefaultInterceptor.setInterceptor(DefaultErrorHandler.get());
    }
}
