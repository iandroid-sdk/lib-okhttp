package com.iandroid.allclass.okhttp.observer;
import com.iandroid.allclass.okhttp.ex.ErrorCode;
import com.iandroid.allclass.okhttp.ex.ExceptionEngine;
import com.iandroid.allclass.okhttp.ex.SysException;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Developer Zailong Shi on 2019-06-21.
 */
public class ObservableImpl<R> implements Observable<R> {

    private final Call<R> originalCall;
    private boolean isDestroyed;
    private Lifecycle lifecycle;

    public ObservableImpl(Call<R> originalCall) {
        this.originalCall = originalCall;
    }

    @Override
    public void observe(final Observer observer) {

        originalCall.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (isDestroyed) return;
                try {
                    if (response == null || response.body() == null) {
                        onFailure(call, new SysException(ErrorCode.SYSTEM_ERROR));
                    } else {
                        if (response.isSuccessful()) {
                            observer.onNext(response.body());
                        } else {
                            onFailure(call, new SysException(ErrorCode.HTTP_ERROR));
                        }
                    }
                } catch (Exception e) {
                    onFailure(call, new SysException(ErrorCode.SYSTEM_ERROR));
                }

            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                if (isDestroyed) return;
                observer.onError(ExceptionEngine.handleException(t));
            }
        });
    }

    @Override
    public Observable<R> dispose(LifecycleOwner lifecycleOwner) {
        lifecycle = lifecycleOwner.getLifecycle();
        lifecycle.addObserver(this);
        return this;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onDestroy() {
        isDestroyed = true;
        originalCall.cancel();
        lifecycle.removeObserver(this);
        lifecycle = null;
    }
}
