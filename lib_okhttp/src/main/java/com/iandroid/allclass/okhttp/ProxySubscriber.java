package com.iandroid.allclass.okhttp;

import android.os.Handler;
import android.os.Looper;

import com.iandroid.allclass.okhttp.caller.HttpCaller;
import com.iandroid.allclass.okhttp.ex.ApiException;
import com.iandroid.allclass.okhttp.ex.BaseException;
import com.iandroid.allclass.okhttp.interceptor.DefaultInterceptor;
import com.iandroid.allclass.okhttp.observer.Observer;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

/**
 * Created by david on 2019/6/25.
 */
public class ProxySubscriber<T> implements Observer {

    static Handler handler = new Handler(Looper.getMainLooper());
    private HttpCaller<T> caller;

    public ProxySubscriber(HttpCaller<T> caller) {
        this.caller = caller;
    }


    @Override
    public void onError(BaseException e) {
        handler.post(() -> {
            DefaultInterceptor.intercept(e, caller.isShowErrorMsg());
            caller.response(e);
        });
    }

    @Override
    public <R> void onNext(R response) {
        boolean isResponseAll = isResponseAll();
        if (caller.isAsynchronousSchedule()) {
            handleResponse(response, isResponseAll);
        } else {
            handler.post(() -> handleResponse(response, isResponseAll));
        }
    }

    private boolean isResponseAll() {
        if (!caller.isAutoParseHttpResponse())
            return true;

        Class clazz = Object.class;
        Type type = caller.getTargetResponseType();
        try {
            if (type != null) clazz = getRawType(type);
            Object instance = clazz.newInstance();
            return instance instanceof HttpResponse;
        } catch (Exception ignored) {
        }

        return false;
    }


    private static Class<?> getRawType(Type type) {
        if (type instanceof Class<?>) {
            return (Class<?>) type;
        }
        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            if (!(rawType instanceof Class)) throw new IllegalArgumentException();
            return (Class<?>) rawType;
        }
        if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            return Array.newInstance(getRawType(componentType), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        }

        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or "
                + "GenericArrayType, but <" + type + "> is of type " + type.getClass().getName());
    }

    private  <R> void handleResponse(R response, boolean isResponseAll) {
        if (isResponseAll) {
            caller.response((T) response);
        } else {
            HttpResponse<T> result = (HttpResponse<T>) response;
            if (result.isSuccessful()) {
                caller.response(result.getData());
            } else {
                onError(new ApiException(result.getCode(), result.getMsg()));
            }
        }
    }
}
