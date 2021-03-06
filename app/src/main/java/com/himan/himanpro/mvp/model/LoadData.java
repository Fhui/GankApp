package com.himan.himanpro.mvp.model;

import android.graphics.Bitmap;
import android.widget.ImageView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.himan.himanpro.R;
import com.himan.himanpro.domain.RBResponse;
import com.himan.himanpro.domain.RandomData;
import com.himan.himanpro.domain.SortData;
import com.himan.himanpro.domain.YIDongResponse;
import com.himan.himanpro.mvp.model.ganhuo.ILoadListener;
import com.himan.himanpro.mvp.model.fuli.ILoadSortData;
import com.himan.himanpro.mvp.model.fuli.ILoadSortListener;
import com.himan.himanpro.mvp.model.ganhuo.ILoadData;
import com.himan.himanpro.mvp.model.yidong.ILoadYDListener;
import com.himan.himanpro.mvp.model.yidong.ILoadYData;
import com.himan.himanpro.net.HttpLoader;
import com.himan.himanpro.net.ResponseListener;
import com.himan.himanpro.utils.LogUtils;

import java.util.List;

/**
 * Created by HIMan on 16/7/5.
 */
public class LoadData implements ILoadData, ILoadSortData, ILoadYData {

    private List<RandomData.ResultsBean> randomDataList;
    private List<SortData.ResultsBean> sortDataList;
    private List<YIDongResponse.ResultsBean> yDataList;

    @Override
    public List<RandomData.ResultsBean> loadRandomData(String url, final ILoadListener listener) {
        HttpLoader.get(url, RandomData.class, 100, new ResponseListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, RBResponse response) {
                RandomData randomData = (RandomData) response;
                if (!randomData.isError()) {
                    randomDataList = randomData.getResults();
                    listener.loadSuccess(randomDataList);
                } else {
                    listener.loadError("sorry  load data error  ------->" + randomData.isError()+"-----requestCode:100");
                }
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {
                listener.loadError(error.toString());
                LogUtils.i("--------------------------error---------------------"+error.toString());
            }
        }, true);
        return randomDataList;
    }


    @Override
    public List<SortData.ResultsBean> getSortData(String url, final ILoadSortListener listener) {
        HttpLoader.get(url, SortData.class, 200, new ResponseListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, RBResponse response) {
                SortData sortData = (SortData) response;
                if(!sortData.isError()){
                    sortDataList = sortData.getResults();
                    listener.loadSuccess(sortDataList);
                }else{
                    listener.loadError("sorry  load data error  ------->" + sortData.isError()+"-----requestCode:100");
                }
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {
                listener.loadError(error.toString());
            }
        }, true);
        return sortDataList;
    }

    @Override
    public Bitmap loadImg(String url, ImageView view) {
        ImageLoader loader = HttpLoader.getImageLoader();
        ImageLoader.ImageListener loaderListener = ImageLoader.getImageListener(view, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        ImageLoader.ImageContainer imageContainer = loader.get(url, loaderListener);
        Bitmap bitmap = imageContainer.getBitmap();
        return bitmap;
    }

    @Override
    public List<YIDongResponse.ResultsBean> loadData(String url, final ILoadYDListener listener) {
        HttpLoader.get(url, YIDongResponse.class, 300, new ResponseListener() {
            @Override
            public void onGetResponseSuccess(int requestCode, RBResponse response) {
                YIDongResponse ydResponse = (YIDongResponse) response;
                yDataList = ydResponse.getResults();
                listener.loadSuccess(yDataList);
            }

            @Override
            public void onGetResponseError(int requestCode, VolleyError error) {
                listener.loadError(error.toString()+", requestCode"+requestCode);
            }
        }, true);
        return yDataList;
    }
}
