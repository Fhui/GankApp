package com.himan.himanpro.core;

/**
 * Created by HIMan on 16/7/5.
 */
public class ProConstant {

    public static String RANDOM_DATA = "http://gank.io/api/history/content/2/1";


    public static String getRandomData(String number, String pager){
        return "http://gank.io/api/history/content/"+number+"/"+pager;
    }

}
