package com.iandroid.okhttp;


import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class FidDateUrlInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        HttpUrl url = originRequest.url();
        //LogUtils.d("FIDDATE_HTTP", "url:" + url);
        Request handledReq = handle(originRequest);
        return chain.proceed(handledReq);
    }

    protected Request handle(Request originRequest) {
        //处理文件上传
        if (originRequest.method().equals("POST")) {
            String apiName = originRequest.url().uri().getPath();
           // LogUtils.d("FIDDATE_HTTP", "apiName:" + apiName);

            if (apiName.equals("ApiName.UPLOAD_IMAGE")) {
                Buffer buffer = new Buffer();
                try {
                    originRequest.body().writeTo(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String fields = buffer.readUtf8();

                Map<String, String> sortedMap = sortedParameters(fields);
                if (sortedMap != null && sortedMap.containsKey("fileProxy")) {
                    //LogUtils.d("FIDDATE_HTTP", "file:" + sortedMap.get("fileProxy"));

                    MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
                    multipartBuilder
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("file", "image", RequestBody.create(MediaType.parse("image"), new File(sortedMap.get("fileProxy"))));

                    Set<Map.Entry<String, String>> parametersEntry = sortedMap.entrySet();
                    for (Map.Entry<String, String> entry : parametersEntry) {
                        multipartBuilder.addFormDataPart(entry.getKey(), null,
                                RequestBody.create(MediaType.parse("text/plain; charset=UTF-8"), entry.getValue()));
                    }

                    originRequest = originRequest.newBuilder().post(multipartBuilder.build()).build();
                }
            }
        }
        return originRequest;

    }

    public static Map<String, String> sortedParameters(String parameters) {
        TreeMap<String, String> sortedParameter = new TreeMap<>();
        if (TextUtils.isEmpty(parameters)) {
            return sortedParameter;
        }
        if (!parameters.contains("=")) return sortedParameter;

        String[] parameterArray = parameters.split("&");

        for (String parameter : parameterArray) {
            String[] strings = parameter.split("=");
            if (2 == strings.length) {
                try {
                    sortedParameter.put(strings[0], URLDecoder.decode(strings[1], "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return sortedParameter;
    }
}
