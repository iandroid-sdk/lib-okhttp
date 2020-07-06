package com.iandroid.allclass.okhttp;

import android.text.TextUtils;

import com.iandroid.allclass.okhttp.interceptor.ErrorInterceptor;
import com.iandroid.allclass.okhttp.interfaces.IDomainProvider;
import com.iandroid.allclass.okhttp.interfaces.IHeaderProvider;
import com.iandroid.allclass.okhttp.interfaces.IRequestResponse;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

/**
 * Created by david on 2019/6/25.
 */
public class HttpConfig {
    private String baseUrl;
    private List<Interceptor> okHttpInterceptors;
    private ErrorInterceptor errorInterceptor;
    private IDomainProvider iDomainProvider;
    private IHeaderProvider iHeaderProvider;
    private IRequestResponse iRequestResponse;

    public HttpConfig(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.okHttpInterceptors = builder.okHttpInterceptors;
        this.errorInterceptor = builder.errorInterceptor;
        this.iDomainProvider = builder.iDomainProvider;
        this.iHeaderProvider = builder.iHeaderProvider;
        this.iRequestResponse = builder.iRequestResponse;
    }

    public IRequestResponse getiRequestResponse() {
        return iRequestResponse;
    }

    public IDomainProvider getiDomainProvider() {
        return iDomainProvider;
    }

    public void setiDomainProvider(IDomainProvider iDomainProvider) {
        this.iDomainProvider = iDomainProvider;
    }

    public IHeaderProvider getiHeaderProvider() {
        return iHeaderProvider;
    }

    public void setiHeaderProvider(IHeaderProvider iHeaderProvider) {
        this.iHeaderProvider = iHeaderProvider;
    }

    public String getBaseUrl() {
        String url = null;
        if (!TextUtils.isEmpty(baseUrl))
            url = baseUrl;
        else if (iDomainProvider != null)
            url = iDomainProvider.getBaseUrl();
        if (TextUtils.isEmpty(url)) {
            throw new InvalidParameterException("baseUrl cannot is empty!");
        }
        return url;
    }

    public List<Interceptor> getOkHttpInterceptors() {
        return okHttpInterceptors;
    }

    public ErrorInterceptor getErrorInterceptor() {
        return errorInterceptor;
    }

    public static final class Builder {
        private String baseUrl;
        private List<Interceptor> okHttpInterceptors;
        private ErrorInterceptor errorInterceptor;
        private IDomainProvider iDomainProvider;
        private IHeaderProvider iHeaderProvider;
        private IRequestResponse iRequestResponse;

        public Builder() {
        }

        public Builder domainProvider(IDomainProvider iDomainProvider) {
            this.iDomainProvider = iDomainProvider;
            return this;
        }

        public Builder headerProvider(IHeaderProvider iHeaderProvider) {
            this.iHeaderProvider = iHeaderProvider;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder requestResponse(IRequestResponse iRequestResponse) {
            this.iRequestResponse = iRequestResponse;
            return this;
        }

        public void addInterceptor(Interceptor interceptor) {
            if (okHttpInterceptors == null) okHttpInterceptors = new ArrayList<>();
            okHttpInterceptors.add(interceptor);
        }

        public Builder okHttpInterceptor(Interceptor okHttpInterceptor) {
            addInterceptor(okHttpInterceptor);
            return this;
        }

        public Builder errorInterceptor(ErrorInterceptor errorInterceptor) {
            this.errorInterceptor = errorInterceptor;
            return this;
        }

        public HttpConfig build() {
            return new HttpConfig(this);
        }

    }
}
