package com.himan.himanpro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import com.himan.himanpro.R;
import com.himan.himanpro.core.BaseActivity;
import com.himan.himanpro.domain.SortData;
import com.himan.himanpro.mvp.presenter.LoadSortPresenter;
import com.himan.himanpro.mvp.view.fuli.ISetSortLoad;
import java.util.List;

/**
 * Created by HIMan on 16/7/14.
 */
public class FuliContentActivity extends BaseActivity implements ISetSortLoad {

    private ImageView im_fuli_content;
    private LoadSortPresenter presenter;
    private Toolbar custom_toolbar;


    @Override
    public int getLayout() {
        return R.layout.activity_fuli_content;
    }

    @Override
    public void initView() {
        im_fuli_content = (ImageView) findViewById(R.id.im_fuli_content);
        custom_toolbar = (Toolbar) findViewById(R.id.custom_toolbar);
    }

    @Override
    public void initData() {
        presenter = new LoadSortPresenter(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra("imgURL");
        String title = intent.getStringExtra("title");
        custom_toolbar.setTitle(title);
        presenter.loadSortImage(url, im_fuli_content);
    }

    @Override
    public void setUrl(String url) {

    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void successFor(List<SortData.ResultsBean> sortDataList) {

    }

    @Override
    public void errorFor(String errorInfo) {

    }
}
