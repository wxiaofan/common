package com.xf.base.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.xf.base.R;




/**
 * Created by niushibin on 2017/2/15 0015.
 */
public class LoadingAnim extends Dialog {


    private Context mContext;


    public LoadingAnim(Context context) {
        super(context);
        this.mContext = context;
//        setCanceledOnTouchOutside(false);
//        setCancelable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }


    private void initView() {
        setContentView(R.layout.dialog_loadings);

    }
}
