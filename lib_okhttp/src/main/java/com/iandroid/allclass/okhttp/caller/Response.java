package com.iandroid.allclass.okhttp.caller;


import com.iandroid.allclass.okhttp.ex.BaseException;

/**
 * Created by david on 2019/6/25.
 */
public interface Response {

    interface Success<T> {
        void response(T r);
    }

    interface Failure {
        void error(BaseException e);
    }
}
