package com.himan.himanpro.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.himan.himanpro.R;
import com.himan.himanpro.adapter.CustomBaseAdapter;
import com.himan.himanpro.core.BaseFragment;
import com.himan.himanpro.core.ProConstant;
import com.himan.himanpro.domain.SortData;
import com.himan.himanpro.holder.GanHuoHolder;
import com.himan.himanpro.mvp.presenter.LoadSortPresenter;
import com.himan.himanpro.mvp.view.fuli.ISetSortLoad;
import com.himan.himanpro.utils.LogUtils;

import java.util.List;

/**
 * Created by HIMan on 16/7/15.
 */
public class AosFragment extends BaseFragment implements ISetSortLoad {

    private ListView lv_show_aos_content;
    private String url;
    private LoadSortPresenter presenter;
    private List<SortData.ResultsBean> sortDataList;
    private AosAdapter adapter;


    @Override
    public int getLayout() {
        return R.layout.android_fragment;
    }

    @Override
    public void initView() {
        lv_show_aos_content = (ListView) view.findViewById(R.id.lv_show_aos_content);
    }

    @Override
    public void initData() {
        url = ProConstant.getDataSort("20", "Android");
        LogUtils.i("AOS---URL" + url);
        adapter = new AosAdapter(getActivity());

    }

    @Override
    public void loadData() {
        presenter = new LoadSortPresenter(this);
        presenter.loadSortData();
    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public void setUrl(String url) {

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
        LogUtils.i("success");
        this.sortDataList = sortDataList;
        adapter.setList(sortDataList);
        lv_show_aos_content.setAdapter(adapter);
        Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void errorFor(String errorInfo) {
        LogUtils.i("error");
        Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
    }

    class AosAdapter extends CustomBaseAdapter<SortData.ResultsBean> {

        GanHuoHolder ganHuoHolder;

        public AosAdapter(Context context) {
            super(context);
        }

        @Override
        public View setConvertView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                ganHuoHolder = new GanHuoHolder();
                convertView = View.inflate(getActivity(), R.layout.ganhuo_lv_items, null);
                ganHuoHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_ganhuo_time);
                ganHuoHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_ganhuo_titme);
                convertView.setTag(ganHuoHolder);
            } else {
                convertView = (View) convertView.getTag();
            }
            ganHuoHolder.tv_time.setText("a");
            return convertView;
        }
    }
}
