package com.iandroid.allclass.okhttp.caller;


import com.iandroid.allclass.okhttp.observer.Observable;

/**
 * Created by david on 2019/6/25.
 */
public interface Caller<T> {

    Caller<T> request(Request request);

    Caller<T> success(Response.Success<T> success);

    Caller<T> failure(Response.Failure failure);

    Observable<?> execute();
}
