package com.himan.himanpro.mvp.model.ganhuo;

import com.himan.himanpro.domain.RandomData;
import com.himan.himanpro.domain.SortData;

import java.util.List;

/**
 * Created by HIMan on 16/7/5.
 */
public interface ILoadListener {

    void loadSuccess(List<RandomData.ResultsBean> randomData);
    void loadError(String errorinfo);
}
