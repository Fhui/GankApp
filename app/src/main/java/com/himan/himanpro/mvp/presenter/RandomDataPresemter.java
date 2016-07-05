package com.himan.himanpro.mvp.presenter;

import android.text.TextUtils;

import com.himan.himanpro.domain.RandomData;
import com.himan.himanpro.mvp.model.ILoadData;
import com.himan.himanpro.mvp.model.ILoadListener;
import com.himan.himanpro.mvp.model.LoadData;
import com.himan.himanpro.mvp.view.ISetLoad;

import java.util.List;

/**
 * Created by HIMan on 16/7/5.
 */
public class RandomDataPresemter implements  Presenter {

    public ISetLoad setLoad;
    public ILoadData loadData;

    public RandomDataPresemter(ISetLoad setLoad){
        this.setLoad = setLoad;
        loadData = new LoadData();
    }

    public void loadRandomData(){
        String url = setLoad.getUrl();
        if(!TextUtils.isEmpty(url)){
            loadData.loadRandomData(url, new ILoadListener() {
                @Override
                public void loadSuccess(List<RandomData.ResultsBean> randomData) {
                        setLoad.successFor(randomData);
                }

                @Override
                public void loadError(String errorinfo) {
                    setLoad.errorFor(errorinfo);
                }
            });
        }

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
