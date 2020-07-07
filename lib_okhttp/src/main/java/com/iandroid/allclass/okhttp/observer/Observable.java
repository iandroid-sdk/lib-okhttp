package com.iandroid.allclass.okhttp.observer;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * created by wangkm
 * on 2020-7-7
 */
public interface Observable<R> extends LifecycleObserver {
    void observe(Observer observer);
    Observable<R> dispose(LifecycleOwner lifecycleOwner);
}
