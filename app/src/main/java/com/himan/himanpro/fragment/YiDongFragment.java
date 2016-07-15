package com.himan.himanpro.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.himan.himanpro.R;
import com.himan.himanpro.core.BaseFragment;
import com.himan.himanpro.view.CustomDialog;
import com.himan.himanpro.view.SuperLoadingProgress;

/**
 * Created by HIMan on 16/7/4.
 */
public class YiDongFragment extends BaseFragment {

    private SuperLoadingProgress custom_progress;



    @Override
    public int getLayout() {
        return R.layout.fragment_yidong;
    }

    public void initView(){
        custom_progress = (SuperLoadingProgress) view.findViewById(R.id.custom_progress);
        custom_progress.finishFail();
    }

    @Override
    public void initData() {

    }

    @Override
    public void loadData() {

    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
