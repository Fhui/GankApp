package com.himan.himanpro.mvp.presenter;

import com.himan.himanpro.domain.YIDongResponse;
import com.himan.himanpro.mvp.model.LoadData;
import com.himan.himanpro.mvp.model.yidong.ILoadYDListener;
import com.himan.himanpro.mvp.model.yidong.ILoadYData;
import com.himan.himanpro.mvp.view.yidong.ISetYData;

import java.util.List;

/**
 * Created by HIMan on 16/7/18.
 */
public class LoadYDPresenter {

       private ILoadYData loadYData;

      private ISetYData setYData;

    public LoadYDPresenter(ISetYData setYData){
        this.setYData = setYData;
        loadYData = new LoadData();
    }

    public void loadYData(){
        loadYData.loadData(setYData.getUrl(), new ILoadYDListener() {
            @Override
            public void loadSuccess(List<YIDongResponse.ResultsBean> yDataList) {
                setYData.successFor(yDataList);
            }

            @Override
            public void loadError(String errorinfo) {
                setYData.errorFor(errorinfo);
            }
        });
    }

}
