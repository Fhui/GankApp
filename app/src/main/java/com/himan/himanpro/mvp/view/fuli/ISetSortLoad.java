package com.himan.himanpro.mvp.view.fuli;

import android.graphics.Bitmap;

import com.himan.himanpro.domain.SortData;
import java.util.List;


/**
 * Created by HIMan on 16/7/11.
 */
public interface ISetSortLoad {


    void setUrl(String url);

    String getUrl();

    void showProgress();

    void hideProgress();

    void successFor(List<SortData.ResultsBean> sortDataList);

    void errorFor(String errorInfo);

}
