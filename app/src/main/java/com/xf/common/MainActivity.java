package com.xf.common;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xf.base.BaseModel;
import com.xf.base.http.api.BaseObserver;
import com.xf.base.http.api.RemoteDataSource;
import com.xf.base.utils.LogUtils;
import com.xf.base.utils.ToastUtils;
import com.xf.base.view.status.StatusView;
import com.xuexiang.xutil.XUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    Button bt1;
    Button bt2;
    Button bt3;
    Button bt4;
    private Button button;
    private TextView textView;
    private RemoteDataSource apis;
    private StatusView statusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        XUtil.init(this);
        apis = RemoteDataSource.getInstance();
        button = findViewById(R.id.bt);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);
        textView = findViewById(R.id.tv);
        statusView = findViewById(R.id.statusview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusView.showLoadingView();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusView.showErrorView();
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusView.showEmptyView();
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusView.showContentView();
            }
        });
    }

    private void getData() {
        LogUtils.d("", apis);
        apis.getBaiDu(new BaseObserver<BaseModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseModel baseModel) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


}
