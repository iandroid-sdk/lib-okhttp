package com.iandroid.allclass.okhttp.caller;

import com.iandroid.allclass.okhttp.ex.BaseException;
import com.iandroid.allclass.okhttp.observer.Observable;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.lifecycle.LifecycleOwner;


/**
 * Created by david on 2019/6/25.
 */
public class HttpCaller<T> implements Caller<T> {

    private Request request;
    private Response.Success<T> success;
    private Response.Failure failure;

    private LifecycleOwner lifecycleOwner;
    private Type targetResponseType;

    //默认自动解析出data 当消息体需全部返回时 可设置false or new HttpCaller<T>(){}.
    private boolean autoParseHttpResponse = true;
    //缓存网络数据到本地
    private boolean needCacheHttpResponse = false;
    //都在线程中处理.
    private boolean asynchronousSchedule = false;

    private boolean isShowErrorMsg = true;

    //默认使用fastJson.
    private Serializer serializePolicy = Serializer.FastJson;

    public HttpCaller() {
        //effective when new HttpCaller<T>(){}.
        targetResponseType = getSuperClassGenericType(getClass(), 0);
    }

    @Override
    public final HttpCaller<T> request(Request request) {
        this.request = request;
        return this;
    }

    @Override
    public final HttpCaller<T> success(Response.Success<T> success) {
        this.success = success;
        return this;
    }

    @Override
    public final HttpCaller<T> failure(Response.Failure failure) {
        this.failure = failure;
        return this;
    }


    @Override
    public final Observable<?> execute() {
        if (this.request == null) throw new NullPointerException("request can not be null");
        return this.request.request(null);
    }

    public void response(T response) {
        if (this.success == null) return;
        this.success.response(response);
    }

    public void response(BaseException e) {
        if (this.failure == null) return;
        this.failure.error(e);
    }

    public final HttpCaller<T> lifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        return this;
    }

    public final HttpCaller<T> asynchronousSchedule(boolean asynchronousSchedule) {
        this.asynchronousSchedule = asynchronousSchedule;
        return this;
    }

    public final HttpCaller<T> autoParseHttpResponse(boolean autoParseHttpResponse) {
        this.autoParseHttpResponse = autoParseHttpResponse;
        return this;
    }

    public final HttpCaller<T> showErrMsg(boolean isShow){
        isShowErrorMsg = isShow;
        return this;
    }

    public final HttpCaller<T> needCacheHttpResponse(boolean needCacheHttpResponse) {
        this.needCacheHttpResponse = needCacheHttpResponse;
        return this;
    }


    public final HttpCaller<T> serializePolicy(Serializer serializePolicy) {
        this.serializePolicy = serializePolicy;
        return this;
    }

    public boolean isShowErrorMsg() {
        return isShowErrorMsg;
    }

    public final Type getTargetResponseType() {
        return targetResponseType;
    }

    public final LifecycleOwner getLifecycleOwner() {
        return lifecycleOwner;
    }

    public final boolean isAutoParseHttpResponse() {
        return autoParseHttpResponse;
    }

    public final boolean isNeedCacheHttpResponse() {
        return needCacheHttpResponse;
    }

    public final boolean isAsynchronousSchedule() {
        return asynchronousSchedule;
    }

    public final Serializer getSerializePolicy() {
        return serializePolicy;
    }

    public static Type getSuperClassGenericType(Class clazz, int index) {
        try {
            Type genType = clazz.getGenericSuperclass();
            if (!(genType instanceof ParameterizedType)) {
                return Object.class;
            }
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            if (index >= params.length || index < 0) {
                return Object.class;
            }
            return params[index];
        } catch (Exception ignored) {
        }
        return Object.class;
    }
}
