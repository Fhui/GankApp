package com.himan.himanpro;

import com.himan.himanpro.domain.RandomData;
import com.himan.himanpro.fragment.GanHuoFragment;
import com.himan.himanpro.mvp.model.LoadData;
import com.himan.himanpro.mvp.model.ganhuo.ILoadData;
import com.himan.himanpro.mvp.model.ganhuo.ILoadListener;
import com.himan.himanpro.mvp.presenter.RandomDataPresemter;
import com.himan.himanpro.mvp.view.ganhuo.ISetLoad;
import com.himan.himanpro.test.Algorithm;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void testAdd() {
        Algorithm algorithm = new Algorithm();
        int sum = algorithm.add(1, 2);
        Assert.assertEquals(sum, 3);
    }


    @Test
    public void loadRandomData() {
        String url = "http://gank.io/api/history/content/10/1";
        ISetLoad setLoad = Mockito.mock(GanHuoFragment.class);
        RandomDataPresemter presemter = new RandomDataPresemter(setLoad);
        ILoadData data = Mockito.mock(LoadData.class);
        presemter.loadRandomData(url);
        Mockito.verify(data).loadRandomData(url, new ILoadListener() {
            @Override
            public void loadSuccess(List<RandomData.ResultsBean> randomData) {

            }

            @Override
            public void loadError(String errorinfo) {

            }
        });
    }
}