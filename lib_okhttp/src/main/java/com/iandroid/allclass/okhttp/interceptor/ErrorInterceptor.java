package com.iandroid.allclass.okhttp.interceptor;


import com.iandroid.allclass.okhttp.ex.BaseException;

/**
 * created by wangkm
 * on 2020-7-7
 */
public interface ErrorInterceptor {
    void intercept(BaseException e);
}
