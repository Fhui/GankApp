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
    public void testAdd(){
        Algorithm algorithm = new Algorithm();
        int sum = algorithm.add(1,2);
        Assert.assertEquals(sum, 3);
    }


    @Test
    public void loadRandomData(){
        final GanHuoFragment setLoad = Mockito.mock(GanHuoFragment.class);
        RandomDataPresemter presenter = new RandomDataPresemter(setLoad);
        LoadData load = Mockito.mock(LoadData.class);
        setLoad.setUrl("http://gank.io/api/history/content/2/1");
        presenter.loadRandomData();
        Mockito.verify(load).loadRandomData("http://gank.io/api/history/content/2/1", new ILoadListener() {
            @Override
            public void loadSuccess(List<RandomData.ResultsBean> randomData) {
                    setLoad.successFor(randomData);
            }

            @Override
            public void loadError(String errorinfo) {
                setLoad.errorFor(errorinfo);
            }
        });
    }
}