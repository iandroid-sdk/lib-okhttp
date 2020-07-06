package com.iandroid.allclass.okhttp.interfaces;

import okhttp3.Request;
import okhttp3.Response;

public interface IRequestResponse {
    public void onTrack(long startTime, Request request, Response response);
}
