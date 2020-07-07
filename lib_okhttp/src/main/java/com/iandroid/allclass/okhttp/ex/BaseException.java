package com.iandroid.allclass.okhttp.ex;

/**
 * created by wangkm
 * on 2020-7-7
 */
public class BaseException extends Exception {

    protected int errorCode;
    protected String errorMsg;


    public BaseException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BaseException(int errorCode) {
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Deprecated
    @Override
    public String getMessage() {
        return getErrorMsg();
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
