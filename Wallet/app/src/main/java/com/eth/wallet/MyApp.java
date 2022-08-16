package com.eth.wallet;

import com.eth.base.BaseApplication;

import timber.log.Timber;

public class MyApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
