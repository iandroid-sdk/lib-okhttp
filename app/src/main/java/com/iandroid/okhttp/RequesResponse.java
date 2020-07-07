package com.iandroid.okhttp;

import com.iandroid.allclass.okhttp.interfaces.IRequestResponse;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * created by wangkm
 * on 2020-7-7
 */
public class RequesResponse implements IRequestResponse {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @Override
    public void onTrack(long startTime, Request request, Response response) {
        if (request == null) return;
        String url = request.url().toString();
        int urlParamFlagStartIndex = url.indexOf("?");
        if (urlParamFlagStartIndex > 0) {
            url = url.substring(0, urlParamFlagStartIndex);
        }

        ResponseBody responseBody = response.body();
        long contentLength = responseBody.contentLength();
        BufferedSource source = responseBody.source();

        try {
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            MediaType contentType = responseBody.contentType();

            Charset charset = UTF8;

            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            if (contentLength != 0) {
                String result = buffer.clone().readString(charset);
               // LogUtils.d("FIDDATE_HTTP", "url:" + url + ",result：" + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}