package com.himan.himanpro.core;

import android.app.Application;

import com.himan.himanpro.net.GetContext;

/**
 * Created by HIMan on 16/7/5.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GetContext getContext = new GetContext(this);
    }
}
