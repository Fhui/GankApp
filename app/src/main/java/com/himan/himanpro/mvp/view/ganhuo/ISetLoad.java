package com.himan.himanpro.mvp.view.ganhuo;

import android.widget.ImageView;

import com.himan.himanpro.domain.RandomData;
import com.himan.himanpro.domain.SortData;

import java.util.List;

/**
 * Created by HIMan on 16/7/5.
 */
public interface ISetLoad {

    void setUrl(String url);

    String getUrl();

    void showProgress();

    void hideProgress();

    void successFor(List<RandomData.ResultsBean> randomData);

    void errorFor(String errorInfo);

}
