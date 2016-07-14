package com.himan.himanpro.mvp.model.fuli;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.himan.himanpro.domain.SortData;

import java.util.List;

/**
 * Created by HIMan on 16/7/11.
 */
public interface ILoadSortData {

    List<SortData.ResultsBean> getSortData(String url, ILoadSortListener listener);

    Bitmap loadImg(String url, ImageView view);

}
