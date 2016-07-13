package com.himan.himanpro.mvp.model;

import com.himan.himanpro.domain.SortData;

import java.util.List;

/**
 * Created by HIMan on 16/7/11.
 */
public interface ILoadSortData {

    List<SortData.ResultsBean> getSortData(String url, ILoadSortListener listener);

}
