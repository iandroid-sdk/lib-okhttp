package com.iandroid.allclass.okhttp.interceptor;


import android.text.TextUtils;

import com.iandroid.allclass.okhttp.config.Config;
import com.iandroid.allclass.okhttp.retrofit.ServiceFactory;

import java.util.Date;
import java.util.List;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpInterceptor {

    public static Interceptor get() {
        return chain -> {
            Request request = chain.request();
            Request.Builder newBuilder = request.newBuilder();

            String domainValue = null;
            List<String> domainValues = request.headers(Config.KEY_DOMAIN);
            newBuilder.removeHeader(Config.KEY_DOMAIN);
            if (domainValues != null && domainValues.size() > 0) {
                domainValue = domainValues.get(0);
            }

            String newDomain = null;
            Headers oldHeaders = request.headers();
            Headers headers = oldHeaders;

            if (ServiceFactory.sConfig != null) {
                if (ServiceFactory.sConfig.getiDomainProvider() != null) {
                    newDomain = ServiceFactory.sConfig.getiDomainProvider().getDomainUrl(domainValue);
                }
                if (ServiceFactory.sConfig.getiHeaderProvider() != null) {
                    headers = ServiceFactory.sConfig.getiHeaderProvider().onGetOkhttpHeaders(oldHeaders);
                }
            }

            if (TextUtils.isEmpty(newDomain)) {
                request = newBuilder
                        .headers(headers)
                        .build();
            } else {
                HttpUrl newBaseUrl = HttpUrl.parse(newDomain);
                HttpUrl oldHttpUrl = request.url();
                HttpUrl newFullUrl = oldHttpUrl
                        .newBuilder()
                        .scheme(newBaseUrl.scheme())
                        .host(newBaseUrl.host())
                        .port(newBaseUrl.port())
                        .build();
                request = newBuilder.url(newFullUrl)
                        .headers(headers)
                        .build();
            }

            long startTime = new Date().getTime();
            Response response = chain.proceed(request);
            track(startTime, request, response);
            return response;
        };
    }

    public static void track(long startTime, Request request, Response response) {
        if (ServiceFactory.sConfig != null) {
            if (ServiceFactory.sConfig.getiRequestResponse() != null) {
                try {
                    ServiceFactory.sConfig.getiRequestResponse().onTrack(startTime, request, response);
                } catch (Exception ignored) {
                }
            }
        }
    }
}
