package com.himan.himanpro.net;

import android.content.Context;

/**
 * Created by Ttg on 2016/6/3 0003.
 */
public class GetContext {

    private static Context context;

    public GetContext(Context context){
        this.context = context;
    }

    public static Context getContext(){
        return context;
    }

}
