package com.iandroid.allclass.okhttp.observer;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

/**
 * Created by david on 2019/6/24.
 */
public interface Observable<R> extends LifecycleObserver {
    void observe(Observer observer);
    Observable<R> dispose(LifecycleOwner lifecycleOwner);
}
