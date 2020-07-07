package com.iandroid.okhttp;

import com.iandroid.allclass.okhttp.base.BaseDomainProvider;
import com.iandroid.allclass.okhttp.config.Config;

/**
 * created by wangkm
 * on 2020-7-7
 */
public class DomainProvider extends BaseDomainProvider {
    @Override
    public String getDomainUrl(String domain) {
        /*if (!StringUtils.isEmpty(domain)) {
            if (Config.DOMAIN_NONE.contains(domain)) {
                return null;
            } else if (Config.DOMAIN_PUBLIC.contains(domain)) {
                return UrlConfig.BASE_HTTP_DOMAIN;
            }
        }
        return UrlConfig.BASE_HTTP_DOMAIN;     //default DOMAIN_LIVE;*/
        return null;
    }

    @Override
    public String getBaseUrl() {
        return null;
    }
}
