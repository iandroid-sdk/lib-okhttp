package com.iandroid.allclass.okhttp.base;

import com.iandroid.allclass.okhttp.interfaces.IHeaderProvider;

import java.util.HashMap;

import okhttp3.Headers;

public class BaseHeaderManager implements IHeaderProvider {
    private HashMap<String, String> headerMap = new HashMap<>();

    @Override
    public Headers onGetOkhttpHeaders(Headers oldHeaders) {
        return Headers.of(headerMap);
    }

    public BaseHeaderManager() {
        initHeader();
    }

    public void initHeader() {

    }

    public void updateHeader() {

    }
}