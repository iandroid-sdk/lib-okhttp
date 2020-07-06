package com.iandroid.allclass.okhttp.interceptor;


import com.iandroid.allclass.okhttp.ex.BaseException;

/**
 * Created by david on 2019/7/4.
 */
public interface ErrorInterceptor {
    void intercept(BaseException e);
}
