package com.iandroid.okhttp;

import com.iandroid.allclass.okhttp.base.BaseDomainProvider;
import com.iandroid.allclass.okhttp.config.Config;
import com.laoniutech.fiddate.core.config.UrlConfig;
import com.laoniutech.fiddate.utils.StringUtils;

/**
 * Created by david on 2019/6/25.
 */
public class DomainProvider extends BaseDomainProvider {
    @Override
    public String getDomainUrl(String domain) {
        if (!StringUtils.isEmpty(domain)) {
            if (Config.DOMAIN_NONE.contains(domain)) {
                return null;
            } else if (Config.DOMAIN_PUBLIC.contains(domain)) {
                return UrlConfig.BASE_HTTP_DOMAIN;
            }
        }
        return UrlConfig.BASE_HTTP_DOMAIN;     //default DOMAIN_LIVE;
    }

    @Override
    public String getBaseUrl() {
        return UrlConfig.BASE_HTTP_DOMAIN;
    }
}
