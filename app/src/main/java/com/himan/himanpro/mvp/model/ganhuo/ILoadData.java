package com.himan.himanpro.mvp.model.ganhuo;

import com.himan.himanpro.domain.RandomData;

import java.util.List;

/**
 * Created by HIMan on 16/7/5.
 */
public interface ILoadData {

     List<RandomData.ResultsBean>  loadRandomData(String url, ILoadListener listener);

}
