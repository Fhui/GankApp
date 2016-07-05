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

/**
 * Created by HIMan on 16/7/4.
 */
public class FuLiFragment extends BaseFragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fuli, null);
        return view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
    }

}
