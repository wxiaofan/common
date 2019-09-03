package com.xf.base.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.SimpleImmersionFragment;
import com.xf.base.presenter.IPresenter;
import com.xf.base.utils.LoadingAnim;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IPresenter> extends SimpleImmersionFragment implements IView<FragmentActivity> {
    protected P MvpPre;
    protected View rootView;
    protected Context mContext;
    protected Unbinder mUnbinder;
    protected LoadingAnim loadingAnim;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MvpPre = bindPresenter();
    }

    protected abstract P bindPresenter();

    @Override
    public FragmentActivity getSelfActivity() {
        return getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = LayoutInflater.from(getActivity()).inflate(getLayoutId(), container, false);
        }
        mContext = getContext();
        mUnbinder = ButterKnife.bind(this, rootView);
        initView(rootView);
        initData();
        initListener();
        return rootView;
    }

    protected void initListener() {

    }


    protected abstract void initData();

    protected abstract void initView(View rootView);

    protected abstract int getLayoutId();


    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * 在生命周期结束时，将presenter与view之间的联系断开，防止出现内存泄露
         */
        mUnbinder.unbind();
        if (MvpPre != null) {
            MvpPre.detachView();
        }
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this).init();
    }

    public void showLoading() {
        if (loadingAnim == null) {
            loadingAnim = new LoadingAnim(mContext);
        }
        loadingAnim.show();
    }

    public void disLoading() {
        if (loadingAnim != null) {
            loadingAnim.cancel();
        }
    }

}
