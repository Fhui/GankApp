package com.himan.himanpro.mvp.model;

import com.android.volley.VolleyError;
import com.himan.himanpro.domain.RBResponse;
import com.himan.himanpro.domain.RandomData;
import com.himan.himanpro.net.HttpLoader;
import com.himan.himanpro.net.ResponseListener;

import java.util.List;

/**
 * Created by HIMan on 16/7/5.
 */
public class LoadData implements  ILoadData {

    private List<RandomData.ResultsBean> randomDataList;

    @Override
    public List<RandomData.ResultsBean> loadRandomData(String url, final ILoadListener listener) {
        HttpLoader.get(url, RandomData.class, 100, new ResponseListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, RBResponse response) {
                RandomData randomData = (RandomData) response;
                if (!randomData.isError()) {
                    randomDataList = randomData.getResults();
                    listener.loadSuccess(randomDataList);
                } else {
                    listener.loadError("sorry  load data error  ------->" + randomData.isError());
                }
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {
                listener.loadError(error.toString());
            }
        }, true);
        return randomDataList;
    }
}
