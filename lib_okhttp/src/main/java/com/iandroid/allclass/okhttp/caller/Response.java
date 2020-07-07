package com.iandroid.allclass.okhttp.caller;


import com.iandroid.allclass.okhttp.ex.BaseException;

/**
 * created by wangkm
 * on 2020-7-7
 */
public interface Response {

    interface Success<T> {
        void response(T r);
    }

    interface Failure {
        void error(BaseException e);
    }
}
