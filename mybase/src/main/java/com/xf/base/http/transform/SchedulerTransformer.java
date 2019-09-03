package com.xf.base.http.transform;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 默认线程调度
 * Created by dmx on 16/12/1.
 */

public class SchedulerTransformer<T> implements ObservableTransformer<T, T> {

    public static <T> SchedulerTransformer<T> create() {
        return new SchedulerTransformer<>();
    }

    @Override
    public ObservableSource<T> apply(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
