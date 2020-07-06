package com.iandroid.allclass.okhttp.interceptor;

import com.iandroid.allclass.okhttp.retrofit.ServiceFactory;

/**
 * 可以再此处统一处理错误相关信息
 * Created by david on 2019/7/4.
 */
public class DefaultErrorHandler {

    public static ErrorInterceptor get() {
        return e -> {
            if (ServiceFactory.sConfig != null
                    && ServiceFactory.sConfig.getErrorInterceptor() != null)
                ServiceFactory.sConfig.getErrorInterceptor().intercept(e);
        };
    }
}
