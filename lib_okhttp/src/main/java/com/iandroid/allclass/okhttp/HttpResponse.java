package com.iandroid.allclass.okhttp;

/**
 * created by wangkm
 * on 2020-7-7
 */
public interface HttpResponse<T> {

    boolean isSuccessful();

    T getData();

    int getCode();

    String getMsg();
}
