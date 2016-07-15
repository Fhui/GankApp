package com.himan.himanpro.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.himan.himanpro.R;
import com.himan.himanpro.utils.LogUtils;
import com.himan.himanpro.view.SuperLoadingProgress;

/**
 * Created by HIMan on 16/7/5.
 */
public abstract class BaseFragment extends Fragment {

    protected boolean isVisible;
    public View view;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisible = true;
            onVisiable();
        }else{
            isVisible = false;
            onInVisiable();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(getLayout(), container, false);
            initView();
            initData();
            loadData();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }



    @Override
    public void onStart() {
        super.onStart();
    }

    public abstract int getLayout();
    public abstract void initView();
    public abstract void initData();
    public abstract void loadData();
    public abstract void onLazyLoad();
    public  void onVisiable(){
        onLazyLoad();
    }
    public  void onInVisiable(){

    }

}
