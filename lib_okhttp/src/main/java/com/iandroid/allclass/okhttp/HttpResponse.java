package com.iandroid.allclass.okhttp;

/**
 * Created by david on 2019/6/24.
 */
public interface HttpResponse<T> {

    boolean isSuccessful();

    T getData();

    int getCode();

    String getMsg();
}
