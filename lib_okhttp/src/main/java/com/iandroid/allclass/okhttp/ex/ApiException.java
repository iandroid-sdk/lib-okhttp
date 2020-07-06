package com.iandroid.allclass.okhttp.ex;

/**
 * Created by david on 2019/5/16.
 */
public class ApiException extends BaseException {
    public ApiException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
