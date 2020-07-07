package com.iandroid.allclass.okhttp.retrofit;

import com.iandroid.allclass.okhttp.observer.Observable;
import com.iandroid.allclass.okhttp.observer.ObservableImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/**
 * created by wangkm
 * on 2020-7-7
 */
public class HttpCallAdapter<R> implements CallAdapter<R, Observable<R>> {

    private Type responseType;

    private HttpCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Observable<R> adapt(Call<R> call) {
        return new ObservableImpl<>(call);
    }

    public static class Factory extends CallAdapter.Factory {

        private Factory() {
        }

        @SuppressWarnings("NullableProblems")
        @Override
        public CallAdapter<?, Observable<?>> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
            Class<?> rawType = getRawType(returnType);
            if (rawType != Observable.class) {
                return null;
            }
            Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);
            return new HttpCallAdapter(responseType);
        }

        static Factory create() {
            return new Factory();
        }
    }
}
