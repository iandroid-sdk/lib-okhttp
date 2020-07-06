package com.iandroid.allclass.okhttp.ex;

/**
 * Created by david on 2019/6/24.
 */
public class ErrorCode {
    public static final int HTTP_OK = 0;

    public static final int HTTP_ERROR = -10000;
    public static final int PARSE_ERROR = -10001;
    public static final int SYSTEM_ERROR = -10002;
    public static final int UNKNOWN_ERROR = -10001;

    //微信登录
    public static final int WX_LOGIN_CANCEL = -1002;
    public static final int WX_LOGIN_DENIED = -1003;
}
