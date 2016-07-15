package com.himan.himanpro.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.himan.himanpro.R;
import com.himan.himanpro.adapter.CustomBaseAdapter;
import com.himan.himanpro.core.BaseFragment;
import com.himan.himanpro.core.ProConstant;
import com.himan.himanpro.domain.SortData;
import com.himan.himanpro.mvp.presenter.LoadSortPresenter;
import com.himan.himanpro.mvp.view.fuli.ISetSortLoad;

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
        setUrl(url);
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
        this.sortDataList = sortDataList;
        adapter.setList(sortDataList);
    }

    @Override
    public void errorFor(String errorInfo) {

    }

    class AosAdapter extends CustomBaseAdapter<SortData.ResultsBean>{

        public AosAdapter(Context context) {
            super(context);
        }

        @Override
        public View setConvertView(int position, View convertView, ViewGroup parent) {
            return convertView;
        }
    }
}
