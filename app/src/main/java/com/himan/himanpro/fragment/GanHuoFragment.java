package com.himan.himanpro.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.himan.himanpro.R;
import com.himan.himanpro.core.BaseFragment;
import com.himan.himanpro.core.ProConstant;
import com.himan.himanpro.domain.RandomData;
import com.himan.himanpro.mvp.presenter.RandomDataPresemter;
import com.himan.himanpro.mvp.view.ISetLoad;
import com.himan.himanpro.utils.LogUtils;

import java.util.List;

/**
 * Created by HIMan on 16/7/4.
 */
public class GanHuoFragment extends BaseFragment implements ISetLoad {

    private View view;
    private RandomDataPresemter randomPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        randomPresenter = new RandomDataPresemter(this);
        view = inflater.inflate(R.layout.fragment_ganhuo, null);
        return view;
    }
    public void initView(){

    }

    @Override
    public void onStart() {
        super.onStart();
        randomPresenter.loadRandomData();
    }

    @Override
    public void initData() {

    }

    @Override
    public String getUrl() {
        String url = ProConstant.getRandomData("2", "1");
        LogUtils.i("url-------->"+url);
        return url;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void successFor(List<RandomData.ResultsBean> randomData) {
        Toast.makeText(getActivity(), "load success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorFor(String errorInfo) {
        Toast.makeText(getActivity(), "load error", Toast.LENGTH_SHORT).show();
    }
}
