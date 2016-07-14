package com.himan.himanpro.mvp.model.fuli;

import android.graphics.Bitmap;

import com.himan.himanpro.domain.RandomData;
import com.himan.himanpro.domain.SortData;

import java.util.List;

/**
 * Created by HIMan on 16/7/11.
 */
public interface ILoadSortListener {

    void loadSuccess(List<SortData.ResultsBean> sortData);
    void loadError(String errorinfo);


}
