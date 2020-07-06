package com.iandroid.okhttp;

import android.text.TextUtils;

import com.iandroid.allclass.okhttp.ex.ApiException;
import com.iandroid.allclass.okhttp.ex.BaseException;
import com.iandroid.allclass.okhttp.ex.ErrorCode;
import com.iandroid.allclass.okhttp.interceptor.ErrorInterceptor;
/**
 * 可以再此处统一处理错误相关信息
 * Created by david on 2019/7/4.
 */
public class DefaultErrorHandler {

    public static ErrorInterceptor get() {
        return e -> {
            if (e instanceof ApiException) {
                int errorCode = e.getErrorCode();
                //LocalUserInfo.handleError(errorCode);
            }
        };
    }

    public static String getDefaultErrorMsg(BaseException e) {
        String errorMsg = null;
        if (e instanceof ApiException) {
            errorMsg = e.getErrorMsg();
        }

        if (TextUtils.isEmpty(errorMsg)) {
            errorMsg = getErrMsg(e.getErrorCode());
        }
        return errorMsg;
    }

    public static String getErrMsg(int code){
        String errorMsg = "";
        if(code == ErrorCode.HTTP_ERROR) {
           // errorMsg = StringUtils.getString(R.string.error_net);
        }else if(code == ErrorCode.PARSE_ERROR){
           // errorMsg = StringUtils.getString(R.string.error_data);
        }else if(code == ErrorCode.SYSTEM_ERROR){
          //  errorMsg = StringUtils.getString(R.string.error_sys);
        }else {
          //  errorMsg = StringUtils.getString(R.string.error_unkown);
        }

        return errorMsg;
    }
}
