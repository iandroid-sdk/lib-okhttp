package com.iandroid.allclass.okhttp.caller;


import com.iandroid.allclass.okhttp.observer.Observable;

/**
 * created by wangkm
 * on 2020-7-7
 */

public interface Request {
     Observable<?> request(HttpService s);
}
