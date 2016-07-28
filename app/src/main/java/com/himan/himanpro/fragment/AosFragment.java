package com.himan.himanpro.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.himan.himanpro.R;
import com.himan.himanpro.adapter.CustomBaseAdapter;
import com.himan.himanpro.core.BaseFragment;
import com.himan.himanpro.core.ProConstant;
import com.himan.himanpro.domain.YIDongResponse;
import com.himan.himanpro.holder.YDHolder;
import com.himan.himanpro.mvp.presenter.LoadYDPresenter;
import com.himan.himanpro.mvp.view.yidong.ISetYData;
import com.himan.himanpro.utils.LogUtils;

import java.util.List;

/**
 * Created by HIMan on 16/7/20.
 */
public class AosFragment extends BaseFragment implements ISetYData {

    private String url;
    private List<YIDongResponse.ResultsBean> yDataList;
    private ListView lv_show_aos_content;
    private AosAdapter adapter;
    private LoadYDPresenter presenter;


    @Override
    public int getLayout() {
        return R.layout.android_fragment ;
    }

    @Override
    public void initView() {
        lv_show_aos_content = (ListView) view.findViewById(R.id.lv_show_aos_content);
    }

    @Override
    public void initData() {
        adapter =  new AosAdapter(getActivity());
    }

    @Override
    public void loadData() {
        url = ProConstant.getDataSort("20", "Android");
        LogUtils.i("AOS-URL:"+url);
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
        adapter.setList(this.yDataList);
        lv_show_aos_content.setAdapter(adapter);
        LogUtils.i("success");
    }

    @Override
    public void errorFor(String errorInfo) {
        LogUtils.i("errorInfo:"+errorInfo);
    }

    class AosAdapter extends CustomBaseAdapter<YIDongResponse.ResultsBean>{

        YDHolder holder;

        public AosAdapter(Context context) {
            super(context);
        }

        @Override
        public View setConvertView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                holder = new YDHolder();
                convertView = View.inflate(getActivity(), R.layout.yidong_lv_items, null);
                holder.tv_time = (TextView) convertView.findViewById(R.id.tv_yd_time);
                holder.tv_title = (TextView) convertView.findViewById(R.id.tv_yd_title);
                holder.tv_who = (TextView) convertView.findViewById(R.id.tv_yd_who);
                convertView.setTag(holder);
            }else{
                holder = (YDHolder) convertView.getTag();
            }
            String time = yDataList.get(position).getCreatedAt();
            time = time.substring(0,10);
            holder.tv_time.setText(time);
            holder.tv_title.setText("Android");
            holder.tv_who.setText(yDataList.get(position).getWho());
            return convertView;
        }
    }
}
