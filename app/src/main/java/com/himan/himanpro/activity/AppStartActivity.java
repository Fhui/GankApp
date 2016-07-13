package com.himan.himanpro.activity;



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
//                 \ \ `-. \_ __\_/__ _/ .-` / /
//   ======`-.____`\.___ \/ ___/___.-`____.-'======
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


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.himan.himanpro.R;
import com.himan.himanpro.adapter.MyFragmentAdapter;
import com.himan.himanpro.core.BaseActivity;
import com.himan.himanpro.fragment.FuLiFragment;
import com.himan.himanpro.fragment.GanHuoFragment;
import com.himan.himanpro.fragment.YiDongFragment;
import java.util.ArrayList;
import java.util.List;

public class AppStartActivity extends BaseActivity {


    private FuLiFragment fuli_fragment;
    private GanHuoFragment ganhuo_fragment;
    private YiDongFragment yidong_fragment;
    private MyFragmentAdapter adapter;
    private List<Fragment> fragment_list;
    private ViewPager vp_home;
    private RadioGroup rg_tab;
    private RadioButton rb_ganhuo;
    private RadioButton rb_fuli;
    private RadioButton rb_yidong;
    private DrawerLayout dl_left;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_start);
    }

    @Override
    public void initView() {
        vp_home = (ViewPager) findViewById(R.id.vp_home);
        rg_tab = (RadioGroup) findViewById(R.id.rg_tab);
        rb_ganhuo = (RadioButton) findViewById(R.id.rb_ganhuo);
        rb_fuli = (RadioButton) findViewById(R.id.rb_fuli);
        rb_yidong = (RadioButton) findViewById(R.id.rb_fuli);
        dl_left = (DrawerLayout) findViewById(R.id.dl_left);
        toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
    }

    public void initData(){
        fuli_fragment = new FuLiFragment();
        ganhuo_fragment = new GanHuoFragment();
        yidong_fragment = new YiDongFragment();
        fragment_list = new ArrayList<>();
        fragment_list.add(ganhuo_fragment);
        fragment_list.add(fuli_fragment);
        fragment_list.add(yidong_fragment);
        rg_tab.check(R.id.rb_ganhuo);
        toolbar.setTitle("干货");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        vp_home.setOffscreenPageLimit(3);
        vp_home.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(), fragment_list));
        vp_home.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        rg_tab.check(R.id.rb_ganhuo);
                        break;
                    case 1:
                        rg_tab.check(R.id.rb_fuli);
                        break;
                    case 2:
                        rg_tab.check(R.id.rb_yidong);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        rg_tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.rb_ganhuo:
                        vp_home.setCurrentItem(0);
                        toolbar.setTitle("干货");
                        break;
                    case R.id.rb_fuli:
                        vp_home.setCurrentItem(1);
                        toolbar.setTitle("福利");
                        break;
                    case R.id.rb_yidong:
                        vp_home.setCurrentItem(2);
                        toolbar.setTitle("移动端");
                        break;
                }
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, dl_left, toolbar, R.string.open, R.string.close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerToggle.syncState();
        dl_left.setDrawerListener(mDrawerToggle);
    }
}
