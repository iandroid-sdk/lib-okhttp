package com.iandroid.allclass.okhttp.observer;


import com.iandroid.allclass.okhttp.ex.BaseException;

/**
 * created by wangkm
 * on 2020-7-7
 */
public interface Observer {

    void onError(BaseException e);

    <R> void onNext(R response);
}
