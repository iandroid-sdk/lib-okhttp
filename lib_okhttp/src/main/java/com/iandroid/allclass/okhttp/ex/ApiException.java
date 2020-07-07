package com.iandroid.allclass.okhttp.ex;

/**
 * created by wangkm
 * on 2020-7-7
 */
public class ApiException extends BaseException {
    public ApiException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
