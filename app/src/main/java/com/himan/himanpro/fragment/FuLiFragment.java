package com.himan.himanpro.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.himan.himanpro.R;
import com.himan.himanpro.adapter.CustomRecycleAdapter;
import com.himan.himanpro.core.BaseFragment;
import com.himan.himanpro.core.ProConstant;
import com.himan.himanpro.domain.SortData;
import com.himan.himanpro.mvp.presenter.LoadSortPresenter;
import com.himan.himanpro.mvp.view.fuli.ISetSortLoad;
import com.himan.himanpro.utils.LogUtils;
import java.util.List;

//                                 _ooOoo_
//                               o8888888o
//                                 88" . "88
//                                  (| -_- |)
//                                  O\ = /O
//                          ____/`---'\____
//                             .   ' \\| |// `.
//                            / \\||| : |||// \
//                         / _||||| -:- |||||- \
//                            | | \\\ - /// | |
//                         | \_| ''\---/'' | |
//                        \ .-\__ `-` ___/-. /
//                     ___`. .' /--.--\ `. . '___
//                 ."" '< `.___\_<|>_/___.' >'"".
//                 | | : `- \`.;`\ _ /`;.`/ - ` : | |
//                 \ \ `-. \_ __\ /__ _/ .-` / /
//   ======`-.____`\.___ \/ _____/___.-`____.-'======
//                            `=---='
//
//         ........................................................................................
//                  佛祖镇楼                  BUG
//          佛曰:
//                  写字楼里写字间，写字间里程序员；
//                  程序人员写程序，又拿程序换酒钱。
//                  酒醒只在网上坐，酒醉还来网下眠；
//                  酒醉酒醒日复日，网上网下年复年。
//                  但愿老死电脑间，不愿鞠躬老板前；
//                  奔驰宝马贵者趣，公交自行程序员。
//                  别人笑我忒疯癫，我笑自己命太贱；
//                  不见满街漂亮妹，哪个归得程序员？

/**
 * 福利模块
 * Created by HIMan on 16/7/4.
 */
public class FuLiFragment extends BaseFragment implements ISetSortLoad, SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private RecyclerView recycle_view_fuli;
    public CustomRecycleAdapter adapter;
    public String url;
    private LoadSortPresenter sortPresenter;
    private List<SortData.ResultsBean> sortDataList;
    private SwipeRefreshLayout fuli_refresh;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    fuli_refresh.setRefreshing(false);
                    Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("onCreate");
        sortPresenter = new LoadSortPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_fuli;
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.i("onStart");
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
        fuli_refresh = (SwipeRefreshLayout) view.findViewById(R.id.fuli_refresh);
        fuli_refresh.setOnRefreshListener(this);
    }

    @Override
    public void initData() {
        adapter = new CustomRecycleAdapter(getActivity(), sortPresenter);
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void loadData() {
        url = ProConstant.getDataSort("20", "福利");
        sortPresenter.loadSortData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.i("onActivityCreated");
    }

    @Override
    public void onLazyLoad() {

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
        LogUtils.i("sortDataList----->" + sortDataList.size());
        Toast.makeText(getActivity(), "load success", Toast.LENGTH_SHORT).show();
        adapter.setSortDataList(sortDataList);
        recycle_view_fuli.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recycle_view_fuli.setAdapter(adapter);
    }


    @Override
    public void errorFor(String errorInfo) {
        Toast.makeText(getActivity(), "load error", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRefresh() {
        setUrl(ProConstant.getDataSort("30", "福利"));
        sortPresenter.loadSortData();
        Toast.makeText(getActivity(), "onRefresh", Toast.LENGTH_SHORT).show();
        Message msg = Message.obtain();
        msg.what = 2;
        handler.sendMessage(msg);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


    }
}
