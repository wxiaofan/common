package com.xf.base.view;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.alibaba.android.arouter.launcher.ARouter;
import com.gyf.immersionbar.ImmersionBar;
import com.xf.base.R;
import com.xf.base.presenter.IPresenter;
import com.xf.base.utils.ActivityManager;
import com.xf.base.utils.LoadingAnim;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IView<Activity> {
    protected P MvpPre;
    protected Unbinder mUnbinder;
    protected int layoutId;
    public ActivityManager activityManager = ActivityManager.getActivityManager(this);

    protected LoadingAnim loadingAnim;

    public BaseActivity(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityManager.putActivity(this);
        initImmersionBar();
        setContentView(layoutId);
        MvpPre = bindPresenter();
        mUnbinder = ButterKnife.bind(this);
        ARouter.getInstance().inject(this);
        initView();
        initData();
        setListener();
    }

    protected void setListener() {

    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract P bindPresenter();

    @Override
    public Activity getSelfActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        /**
         * 在生命周期结束时，将presenter与view之间的联系断开，防止出现内存泄露
         */
        mUnbinder.unbind();
        if (MvpPre != null) {
            MvpPre.detachView();
        }
        activityManager.removeActivity(this);
        super.onDestroy();
    }

    protected void changeStatusBarTextColor(boolean isBlack) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            if (isBlack) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//设置状态栏黑色字体
            } else {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);//恢复状态栏白色字体
            }
        }
    }


    protected void initImmersionBar() {
        //设置共同沉浸式样式
        ImmersionBar.with(this).statusBarColor(R.color.white).init();
    }

    public void showLoading() {
        if (loadingAnim == null) {
            loadingAnim = new LoadingAnim(this);
        }
        loadingAnim.show();
    }

    public void disLoading() {
        if (loadingAnim != null) {
            loadingAnim.cancel();
        }
    }

    public void exit() {
        activityManager.exit();
    }

}
