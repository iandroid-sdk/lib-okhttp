package com.iandroid.okhttp;


import com.iandroid.allclass.okhttp.base.BaseHeaderManager;
import java.util.Locale;

import okhttp3.Headers;

/**
 * created by wangkm
 * on 2020-7-7
 */
public class HeaderManager extends BaseHeaderManager {
    private static HeaderManager intance;

    public static HeaderManager getInstance() {
        if (intance == null) {
            intance = new HeaderManager();
        }
        return intance;
    }

    @Override
    public Headers onGetOkhttpHeaders(Headers oldHeaders) {
        return super.onGetOkhttpHeaders(oldHeaders);
    }

    @Override
    public void initHeader() {
        super.initHeader();
       /* UserInfo userInfo = LocalUserInfo.getLocalUserInfo();
        Locale localLocale = Locale.getDefault();
        headerMap.put("os", "Android " + android.os.Build.VERSION.RELEASE);
        headerMap.put("mb", android.os.Build.MODEL);
        headerMap.put("ak", DeviceUtils.getUniqueId(AppGlobal.applicationContext()));
        headerMap.put("ts", String.valueOf(TimeUtils.getTime() / 1000));
        headerMap.put("tz", StringUtils.checkNull(TimeUtils.getCurrentTimeZone()));
        headerMap.put("lang", localLocale.getLanguage() + "-" + localLocale.getCountry());
        headerMap.put("vs", BuildConfig.VERSION_NAME);
        headerMap.put("nt", NetUtils.getNetworkType(AppGlobal.applicationContext()));
        headerMap.put("uk", userInfo.getToken());
        LogUtils.d(LoginStep.TAG, "uk:" + userInfo.getToken());*/
    }

    @Override
    public void updateHeader() {
        super.updateHeader();
        //UserInfo userInfo = LocalUserInfo.getLocalUserInfo();
        //headerMap.put("uk", userInfo.getToken());
        //LogUtils.d(LoginStep.TAG, "uk:" + userInfo.getToken());
    }

}
