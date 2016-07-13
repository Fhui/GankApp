package com.himan.himanpro.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.himan.himanpro.R;
import com.himan.himanpro.core.BaseFragment;
import com.himan.himanpro.utils.LogUtils;

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
 * Created by HIMan on 16/7/4.
 */
//public class GanHuoFragment extends BaseFragment implements ISetLoad, SwipeRefreshLayout.OnRefreshListener {
public class GanHuoFragment extends BaseFragment {

    private View view;
//    private RandomDataPresemter randomPresenter;
//    private ListView ganhuo_lv;
//    private List<RandomData.ResultsBean> resultsList;
//    private GanHuoAdapter adapter;
//    private SwipeRefreshLayout swip_layout;
//    private int i = 2;
//    public String url;
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch(msg.what){
//                case 1:
//                    swip_layout.setRefreshing(false);
//                    Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//            super.handleMessage(msg);
//        }
//    };



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("onCreat");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.i("onCreateView");
        view = inflater.inflate(R.layout.fragment_ganhuo, null);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        LogUtils.i("onStart");
//        url = ProConstant.getRandomData("10", "1");
//        randomPresenter = new RandomDataPresemter(this);
//        randomPresenter.loadRandomData();
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
        LogUtils.i("onDestroy");
    }

    public void initView(){
//        ganhuo_lv = (ListView) view.findViewById(R.id.ganhuo_lv);
//        swip_layout = (SwipeRefreshLayout) view.findViewById(R.id.swip_layout);
    }

    @Override
    public void initData() {
//        adapter = new GanHuoAdapter(getActivity());
//        swip_layout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_orange_dark,
//                android.R.color.holo_green_light, android.R.color.holo_red_dark);
//        swip_layout.setOnRefreshListener(this);
    }

//    @Override
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//    @Override
//    public String getUrl() {
//        LogUtils.i("url-------->"+url);
//        return url;
//    }
//
//    @Override
//    public void showProgress() {
//        ProgressUtils.showNoteBackProgressDialog(getActivity(), "正在加载");
//    }
//
//    @Override
//    public void hideProgress() {
//        ProgressUtils.dismissDialog();
//    }
//
//    @Override
//    public void successFor(List<RandomData.ResultsBean> randomData) {
//        resultsList = randomData;
//        Toast.makeText(getActivity(), "load success", Toast.LENGTH_SHORT).show();
//        adapter.setList(resultsList);
//        ganhuo_lv.setAdapter(adapter);
//        setItemClick();
//    }
//
//    public void setItemClick(){
//        ganhuo_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                LogUtils.i( "item:"+i+"click.s");
//                Intent intent = new Intent(getActivity(), ContentActivity.class);
//                intent.putExtra("content", resultsList.get(i).getContent());
//                startActivity(intent);
//            }
//        });
//    }
//
//    @Override
//    public void errorFor(String errorInfo) {
//        Toast.makeText(getActivity(), "load error", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onRefresh() {
//         setUrl(ProConstant.getRandomData("10", i+""));
//         randomPresenter.loadRandomData();
//         i++;
//        Message msg = Message.obtain();
//        msg.what = 1;
//        handler.sendMessage(msg);
//    }
//
//
//    class GanHuoAdapter extends CustomBaseAdapter<RandomData.ResultsBean>{
//
//        GanHuoHolder ganHuoHolder;
//
//        public GanHuoAdapter(Context context) {
//            super(context);
//        }
//
//        @Override
//        public View setConvertView(int position, View convertView, ViewGroup parent) {
//            if(convertView == null){
//                ganHuoHolder = new GanHuoHolder();
//                convertView = View.inflate(getActivity(), R.layout.ganhuo_lv_items, null);
//                ganHuoHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_ganhuo_titme);
//                ganHuoHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_ganhuo_time);
//                convertView.setTag(ganHuoHolder);
//            }else{
//                ganHuoHolder = (GanHuoHolder) convertView.getTag();
//            }
//            String time = resultsList.get(position).getPublishedAt();
//            time = time.substring(0, 10);
//            ganHuoHolder.tv_title.setText(resultsList.get(position).getTitle());
//            ganHuoHolder.tv_time.setText(time);
//            return convertView;
//        }
//    }
}
