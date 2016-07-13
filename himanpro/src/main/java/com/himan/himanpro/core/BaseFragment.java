package com.himan.himanpro.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HIMan on 16/7/5.
 */
public abstract class BaseFragment extends Fragment {


     public boolean mHasLoadedOnce = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onStart() {
        super.onStart();
        initView();
        initData();
    }


    public abstract void initView();
    public abstract void initData();

}
