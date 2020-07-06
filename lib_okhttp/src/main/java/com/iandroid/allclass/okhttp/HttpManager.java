package com.iandroid.allclass.okhttp;


import com.iandroid.allclass.okhttp.caller.HttpCaller;
import com.iandroid.allclass.okhttp.observer.Observable;
import com.iandroid.allclass.okhttp.retrofit.RetrofitUtils;

/**
 * Created by david on 2019/6/24.
 */
public class HttpManager {

    public static void init(HttpConfig config) {
        RetrofitUtils.init(config);
    }

    /**
     * Request Sample:
     *
     * HttpManager.call(new HttpCaller<SnsLiveAnchorInfo>()
     *            .request(s ->                       //执行请求回调
     *                  ServiceFactory.get(HomeService.class).getSnsLiveInfo())
     *            .success(r -> {})                   //成功回调
     *            .failure(e -> { })                  //失败回调
     *            .asynchronousSchedule(true)         //子线程回调 默认主线程
     *            .autoParseHttpResponse(false));     //可设置false返回全部消息体 or 重载HttpCaller:new HttpCaller<T>(){}
     * @param <T>
     * @param caller
     */
    public static <T> void call(HttpCaller<T> caller) {
        if (caller == null) throw new IllegalArgumentException("http caller must be created!");

        //TODO http cache.
        //处理网络请求.
        Observable<?> observable = caller.execute();
        if (caller.getLifecycleOwner() != null)
            observable.dispose(caller.getLifecycleOwner());
        if (observable == null) throw new RuntimeException("need add request callback.");
        ProxySubscriber<T> subscriber = new ProxySubscriber<T>(caller) {};
        observable.observe(subscriber);
    }
}
