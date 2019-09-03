package com.xf.base.http.api;


import com.xf.base.utils.ToastUtils;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import io.reactivex.Observer;

/**
 * Created by Administrator on 2017/11/22.
 */

public abstract class BaseObserver<T> implements Observer<T> {


    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            ToastUtils.showShort("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectException) {
            ToastUtils.showShort("网络中断，请检查您的网络状态");
        } else if (e instanceof ConnectTimeoutException) {
            ToastUtils.showShort("网络中断，请检查您的网络状态");
        } else if (e instanceof UnknownHostException) {
            ToastUtils.showShort("网络中断，请检查您的网络状态");
        } else if (e instanceof IllegalStateException) {
            ToastUtils.showShort("解析失败");
        } else if (e instanceof APIException) {
            ToastUtils.showShort(e.getMessage());
        } else {
            ToastUtils.showShort(e.getMessage());
        }
        onComplete();
    }


}
