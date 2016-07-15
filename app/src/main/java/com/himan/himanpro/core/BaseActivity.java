package com.himan.himanpro.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by HIMan on 16/7/5.
 */
public abstract class BaseActivity extends AppCompatActivity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public abstract int getLayout();

    public abstract void initView();

    public abstract void initData();



}
