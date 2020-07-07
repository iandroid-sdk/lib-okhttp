package com.iandroid.okhttp;

import com.iandroid.allclass.okhttp.config.Config;
import com.iandroid.allclass.okhttp.observer.Observable;

import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * created by wangkm
 * on 2020-7-7
 */
public interface DataService {
    //开屏登录信息刷新
    @Headers(Config.DOMAIN_PUBLIC)
    @POST("/profile/info")
    Observable<HttpResult> getMyAccountInfo();
}
