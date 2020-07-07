package com.iandroid.allclass.okhttp.interfaces;

import okhttp3.Headers;

/**
 * created by wangkm
 * on 2020-7-7
 */
public interface IHeaderProvider {
    public Headers onGetOkhttpHeaders(Headers oldHeaders);
}
