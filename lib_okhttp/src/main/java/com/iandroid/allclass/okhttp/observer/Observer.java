package com.iandroid.allclass.okhttp.observer;


import com.iandroid.allclass.okhttp.ex.BaseException;

/**
 * Created by david on 2019/6/24.
 */
public interface Observer {

    void onError(BaseException e);

    <R> void onNext(R response);
}
