package com.xf.base.presenter;


import android.content.Context;

import com.xf.base.view.IView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class BasePresenter<V extends IView> implements IPresenter {
    protected CompositeDisposable mDisposables = new CompositeDisposable();
    protected Reference<V> MvpRef;
    protected Context mContext;

    @Deprecated
    public BasePresenter(V view) {
        attachView(view);
    }

    public BasePresenter(V view, Context context) {
        attachView(view);
        mContext = context;
    }

    private void attachView(V view) {
        MvpRef = new WeakReference<V>(view);
    }

    protected V getView() {
        if (MvpRef != null) {
            return MvpRef.get();
        }
        return null;
    }

    /**
     * 主要用于判断IView的生命周期是否结束，防止出现内存泄露状况
     *
     * @return
     */
    protected boolean isViewAttach() {
        return MvpRef != null && MvpRef.get() != null;
    }

    @Override
    public void detachView() {
        cancelRequest();
        if (MvpRef != null) {
            MvpRef.clear();
            MvpRef = null;
        }
        unBindView();
    }


    /**
     * 加入订阅对象
     *
     * @param disposable
     */
    public void addDisposable(Disposable disposable) {
        mDisposables.add(disposable);
    }

    /**
     * 移除订阅对象
     *
     * @param disposable
     */
    public void removeDisposable(Disposable disposable) {
        mDisposables.remove(disposable);
    }

    /**
     * 取消所有请求
     */
    public void cancelRequest() {

        if (mDisposables != null) {
            mDisposables.clear(); // clear时网络请求会随即cancel
            mDisposables = null;
        }
    }

    public void unBindView() {
        if (MvpRef != null) {
            MvpRef.clear();
        }
    }


}
