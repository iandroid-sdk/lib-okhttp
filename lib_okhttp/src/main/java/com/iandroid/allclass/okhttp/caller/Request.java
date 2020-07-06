package com.iandroid.allclass.okhttp.caller;


import com.iandroid.allclass.okhttp.observer.Observable;

/**
 * Created by david on 2019/6/25.
 */
public interface Request {
     Observable<?> request(HttpService s);
}
