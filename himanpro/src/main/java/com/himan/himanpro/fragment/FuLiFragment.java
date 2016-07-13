package com.himan.himanpro.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.himan.himanpro.R;
import com.himan.himanpro.adapter.CustomRecycleAdapter;
import com.himan.himanpro.core.BaseFragment;
import com.himan.himanpro.core.ProConstant;
import com.himan.himanpro.domain.SortData;
import com.himan.himanpro.mvp.presenter.LoadSortPresenter;
import com.himan.himanpro.mvp.view.ISetSortLoad;
import com.himan.himanpro.utils.LogUtils;

import java.util.List;

/**
 * Created by HIMan on 16/7/4.
 */
public class FuLiFragment extends BaseFragment implements ISetSortLoad{

    private View view;
    private RecyclerView recycle_view_fuli;
    CustomRecycleAdapter adapter;
    public String url;
    private LoadSortPresenter sortPresenter;
    private List<SortData.ResultsBean> sortDataList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.i("onCreateView");
        view = inflater.inflate(R.layout.fragment_fuli, container);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.i("onStart");
        url = ProConstant.getDataSort("20", "福利");
        sortPresenter = new LoadSortPresenter(this);
        sortPresenter.loadSortData();
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.i("onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i("onDestory");
    }

    @Override
    public void initView() {
        recycle_view_fuli = (RecyclerView) view.findViewById(R.id.recycle_view_fuli);
    }

    @Override
    public void initData() {

    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void successFor(List<SortData.ResultsBean> sortDataList) {
        this.sortDataList = sortDataList;
        LogUtils.i("sortDataList----->"+sortDataList.size());
        Toast.makeText(getActivity(), "load success", Toast.LENGTH_SHORT).show();
        adapter = new CustomRecycleAdapter(getActivity(), sortDataList);
//        recycle_view_fuli.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recycle_view_fuli.setAdapter(adapter);
    }


    @Override
    public void errorFor(String errorInfo) {
        Toast.makeText(getActivity(), "load error", Toast.LENGTH_SHORT).show();
    }

}
