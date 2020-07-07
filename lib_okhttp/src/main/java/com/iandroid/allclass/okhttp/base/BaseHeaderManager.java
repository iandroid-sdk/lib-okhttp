package com.iandroid.allclass.okhttp.base;

import com.iandroid.allclass.okhttp.interfaces.IHeaderProvider;

import java.util.HashMap;

import okhttp3.Headers;

/**
 * created by wangkm
 * on 2020-7-7
 */

public class BaseHeaderManager implements IHeaderProvider {
    protected HashMap<String, String> headerMap = new HashMap<>();

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
