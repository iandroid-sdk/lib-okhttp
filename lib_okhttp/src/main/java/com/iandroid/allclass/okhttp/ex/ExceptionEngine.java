package com.iandroid.allclass.okhttp.ex;

import android.net.ParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import retrofit2.HttpException;

/**
 * created by wangkm
 * on 2020-7-7
 */
public class ExceptionEngine {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static BaseException handleException(Throwable e) {
        BaseException ex;
        if (e instanceof ConnectException) {
            return new SysException(ErrorCode.HTTP_ERROR);
        } else if (e instanceof SocketTimeoutException) {
            return new SysException(ErrorCode.HTTP_ERROR);
        } else if (e instanceof TimeoutException) {
            return new SysException(ErrorCode.HTTP_ERROR);
        } else if (e instanceof HttpException) { //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new BaseException(ErrorCode.HTTP_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    break;
            }
            return ex;
        } else if (e instanceof ApiException) { //服务器返回的错误
            return (BaseException) e;
        } else if (e instanceof JSONException || e instanceof ParseException) {
            return new SysException(ErrorCode.PARSE_ERROR);//均视为解析错误
        } else if (e instanceof UnknownHostException) {
            return new SysException(ErrorCode.HTTP_ERROR);//均视为网络错误
        } else {
            return new SysException(ErrorCode.UNKNOWN_ERROR);//未知错误
        }
    }
}
