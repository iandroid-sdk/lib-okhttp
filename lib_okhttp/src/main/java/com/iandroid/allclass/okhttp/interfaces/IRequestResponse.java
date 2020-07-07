package com.iandroid.allclass.okhttp.interfaces;

import okhttp3.Request;
import okhttp3.Response;

/**
 * created by wangkm
 * on 2020-7-7
 */
public interface IRequestResponse {
    public void onTrack(long startTime, Request request, Response response);
}
