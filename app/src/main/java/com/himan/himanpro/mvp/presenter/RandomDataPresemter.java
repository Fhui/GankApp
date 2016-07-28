package com.himan.himanpro.mvp.presenter;

import android.text.TextUtils;
import com.himan.himanpro.domain.RandomData;
import com.himan.himanpro.mvp.model.ganhuo.ILoadData;
import com.himan.himanpro.mvp.model.ganhuo.ILoadListener;
import com.himan.himanpro.mvp.model.LoadData;
import com.himan.himanpro.mvp.view.ganhuo.ISetLoad;
import com.himan.himanpro.utils.LogUtils;

import java.util.List;

/**
 * Created by HIMan on 16/7/5.
 */
public class RandomDataPresemter implements Presenter {

    public ISetLoad setLoad;
    public ILoadData loadData;

    public RandomDataPresemter(ISetLoad setLoad) {
        this.setLoad = setLoad;
        loadData = new LoadData();
    }

    public void loadRandomData() {
        String url = setLoad.getUrl();
        setLoad.showProgress();
        if (!TextUtils.isEmpty(url)) {
            loadData.loadRandomData(url, new ILoadListener() {
                @Override
                public void loadSuccess(final List<RandomData.ResultsBean> randomData) {
                    setLoad.successFor(randomData);
                    setLoad.hideProgress();
                }
                @Override
                public void loadError(String errorinfo) {
                    setLoad.errorFor(errorinfo);
                    setLoad.hideProgress();
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
