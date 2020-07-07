package com.iandroid.allclass.okhttp.interceptor;

import com.iandroid.allclass.okhttp.ex.BaseException;

/**
 * created by wangkm
 * on 2020-7-7
 */
public class DefaultInterceptor {

    private static class DefaultInterceptorHolder {
        private static final DefaultInterceptor INSTANCE = new DefaultInterceptor();
    }

    private static DefaultInterceptor getInstance() {
        return DefaultInterceptorHolder.INSTANCE;
    }

    private ErrorInterceptor errorInterceptor;

    public static void setInterceptor(ErrorInterceptor errorInterceptor) {
        DefaultInterceptor.getInstance().errorInterceptor = errorInterceptor;
    }

    public static void intercept(BaseException e, boolean showMsg) {
        ErrorInterceptor errorInterceptor = DefaultInterceptor.getInstance().errorInterceptor;
        if (errorInterceptor != null) {
            errorInterceptor.intercept(e);
        }
    }
}
