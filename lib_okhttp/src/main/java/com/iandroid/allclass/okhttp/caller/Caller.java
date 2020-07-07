package com.iandroid.allclass.okhttp.caller;


import com.iandroid.allclass.okhttp.observer.Observable;

/**
 * created by wangkm
 * on 2020-7-7
 */

public interface Caller<T> {

    Caller<T> request(Request request);

    Caller<T> success(Response.Success<T> success);

    Caller<T> failure(Response.Failure failure);

    Observable<?> execute();
}
