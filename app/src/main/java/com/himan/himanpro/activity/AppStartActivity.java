package com.himan.himanpro.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.balysv.materialmenu.MaterialMenuIcon;
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
    private MaterialMenuIcon materialMenuIcon;
    private ActionBarDrawerToggle mDrawerToggle;
    private boolean isOpenMenu = false;

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
//        materialMenuIcon = new MaterialMenuIcon(this, Color.WHITE, MaterialMenuDrawable.Stroke.THIN);
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
