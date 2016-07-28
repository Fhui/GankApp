package com.himan.himanpro.mvp.view.yidong;

import com.himan.himanpro.domain.YIDongResponse;

import java.util.List;

/**
 * Created by HIMan on 16/7/18.
 */
public interface ISetYData {

    String getUrl();

    void showProgress();

    void hideProgress();

    void successFor(List<YIDongResponse.ResultsBean> yDataList);

    void errorFor(String errorInfo);

}
