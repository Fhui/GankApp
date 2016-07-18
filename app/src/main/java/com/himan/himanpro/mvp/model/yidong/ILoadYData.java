package com.himan.himanpro.mvp.model.yidong;

import com.himan.himanpro.domain.YIDongResponse;

import java.util.List;

/**
 * Created by HIMan on 16/7/18.
 */
public interface ILoadYData {


    List<YIDongResponse.ResultsBean> loadData(String url, ILoadYDListener listener);

}
