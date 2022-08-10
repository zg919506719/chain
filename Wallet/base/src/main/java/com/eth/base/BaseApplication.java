package com.eth.base;

import android.app.Application;

import com.eth.base.utils.Utils;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}

