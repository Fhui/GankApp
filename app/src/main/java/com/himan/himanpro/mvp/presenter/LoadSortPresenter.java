package com.himan.himanpro.mvp.presenter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.himan.himanpro.domain.SortData;
import com.himan.himanpro.mvp.model.fuli.ILoadSortData;
import com.himan.himanpro.mvp.model.fuli.ILoadSortListener;
import com.himan.himanpro.mvp.model.LoadData;
import com.himan.himanpro.mvp.view.fuli.ISetSortLoad;

import java.util.List;

/**
 * Created by HIMan on 16/7/11.
 */
public class LoadSortPresenter implements Presenter{

    ISetSortLoad setSortLoad;
    ILoadSortData sortData;


    public LoadSortPresenter(ISetSortLoad setSortLoad){
        this.setSortLoad = setSortLoad;
        sortData = new LoadData();
    }

    public void loadSortData(){
        sortData.getSortData(setSortLoad.getUrl(), new ILoadSortListener() {
            @Override
            public void loadSuccess(List<SortData.ResultsBean> sortData) {
                setSortLoad.successFor(sortData);
            }

            @Override
            public void loadError(String errorinfo) {
                setSortLoad.errorFor(errorinfo);
            }
        });
    }

        public Bitmap loadSortImage(String url, ImageView imageView){
        return sortData.loadImg(url, imageView);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
