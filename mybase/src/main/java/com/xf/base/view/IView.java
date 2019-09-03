package com.xf.base.view;


public interface IView<T> {
    T getSelfActivity();
    void showLoadings();
    void disLoadings();
}
