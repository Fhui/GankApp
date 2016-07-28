package com.himan.himanpro.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.himan.himanpro.R;
import com.himan.himanpro.adapter.CustomBaseAdapter;
import com.himan.himanpro.core.BaseFragment;
import com.himan.himanpro.core.ProConstant;
import com.himan.himanpro.domain.RandomData;
import com.himan.himanpro.domain.SortData;
import com.himan.himanpro.domain.YIDongResponse;
import com.himan.himanpro.holder.GanHuoHolder;
import com.himan.himanpro.holder.YDHolder;
import com.himan.himanpro.mvp.presenter.LoadSortPresenter;
import com.himan.himanpro.mvp.presenter.LoadYDPresenter;
import com.himan.himanpro.mvp.view.fuli.ISetSortLoad;
import com.himan.himanpro.mvp.view.yidong.ISetYData;
import com.himan.himanpro.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HIMan on 16/7/15.
 */
public class IosFragment extends BaseFragment implements ISetYData {

    private ListView lv_ios;
    private IOSAdapter adapter;
    public String url;
    public List<YIDongResponse.ResultsBean> yDataList;
    private LoadYDPresenter presenter;


    @Override
    public int getLayout() {
        return R.layout.ios_fragment;
    }

    @Override
    public void initView() {
        lv_ios = (ListView) view.findViewById(R.id.lv_ios);
    }

    @Override
    public void initData() {
        adapter = new IOSAdapter(getActivity());
    }

    @Override
    public void loadData() {
        url = ProConstant.getDataSort("20", "iOS");
        LogUtils.i("IOS-URL:"+url);
        presenter = new LoadYDPresenter(this);
        presenter.loadYData();
    }

    @Override
    public void onLazyLoad() {

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
    public void successFor(List<YIDongResponse.ResultsBean> yDataList) {
        this.yDataList = yDataList;
        adapter.setList(yDataList);
        lv_ios.setAdapter(adapter);
        LogUtils.i("success");
    }

    @Override
    public void errorFor(String errorInfo) {
        LogUtils.i("errorInfo:"+errorInfo);
    }


    class IOSAdapter extends CustomBaseAdapter<YIDongResponse.ResultsBean> {

        YDHolder holder;

        public IOSAdapter(Context context) {
            super(context);
        }

        @Override
        public View setConvertView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(getActivity(), R.layout.yidong_lv_items, null);
                holder = new YDHolder();
                holder.tv_who = (TextView) convertView.findViewById(R.id.tv_yd_who);
                holder.tv_time = (TextView) convertView.findViewById(R.id.tv_yd_time);
                holder.tv_title = (TextView) convertView.findViewById(R.id.tv_yd_title);
                convertView.setTag(holder);
            } else {
                holder = (YDHolder) convertView.getTag();
            }
            String time = yDataList.get(position).getCreatedAt();
             time = time.substring(0,10);
            holder.tv_time.setText(time);
            holder.tv_who.setText("author : "+yDataList.get(position).getWho());
            holder.tv_title.setText(yDataList.get(position).getType());
            return convertView;
        }
    }
}
