package com.iandroid.allclass.okhttp.retrofit;

import com.iandroid.allclass.okhttp.HttpConfig;
import com.iandroid.allclass.okhttp.interceptor.DefaultErrorHandler;
import com.iandroid.allclass.okhttp.interceptor.DefaultInterceptor;

/**
 * Created by david on 2019/6/25.
 */
public class RetrofitUtils {

    public static void init(HttpConfig config) {
        ServiceFactory.init(config);
        DefaultInterceptor.setInterceptor(DefaultErrorHandler.get());
    }
}
