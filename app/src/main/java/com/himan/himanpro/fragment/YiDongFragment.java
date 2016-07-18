package com.himan.himanpro.fragment;

import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.himan.himanpro.R;
import com.himan.himanpro.core.BaseFragment;

/**
 * Created by HIMan on 16/7/4.
 */
public class YiDongFragment extends BaseFragment {

    private FrameLayout fl_yidong;
    private RadioGroup rg_yidong;
    private RadioButton rb_android;
    private RadioButton rb_ios;
    private AosFragment aosFragment;
    private IosFragment iosFragment;



    @Override
    public int getLayout() {
        return R.layout.fragment_yidong;
    }

    public void initView(){
        fl_yidong = (FrameLayout) view.findViewById(R.id.fl_yidong);
        rg_yidong = (RadioGroup) view.findViewById(R.id.rg_yidong);
        rb_android = (RadioButton) view.findViewById(R.id.rb_android);
        rb_ios = (RadioButton) view.findViewById(R.id.rb_apple);
    }

    @Override
    public void initData() {
        aosFragment = new AosFragment();
        iosFragment = new IosFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_yidong, aosFragment).commit();
        rg_yidong.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.rb_android:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_yidong, aosFragment).commit();
                        break;
                    case R.id.rb_apple:
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_yidong, iosFragment).commit();
                        break;
                }
            }
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
