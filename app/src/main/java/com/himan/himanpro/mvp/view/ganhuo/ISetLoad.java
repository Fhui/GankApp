package com.himan.himanpro.mvp.view.ganhuo;

import com.himan.himanpro.domain.RandomData;

import java.util.List;

/**
 * Created by HIMan on 16/7/5.
 */
public interface ISetLoad {


    void showProgress();

    void hideProgress();

    void successFor(List<RandomData.ResultsBean> randomData);

    void errorFor(String errorInfo);

}
