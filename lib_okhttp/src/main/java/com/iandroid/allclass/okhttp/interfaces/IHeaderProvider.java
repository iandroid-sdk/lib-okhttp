package com.iandroid.allclass.okhttp.interfaces;

import okhttp3.Headers;

public interface IHeaderProvider {
    public Headers onGetOkhttpHeaders(Headers oldHeaders);
}
