package com.eth.wallet;

import android.app.Application;
import android.util.Log;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.realm.Realm;
import timber.log.Timber;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //数据库
        Realm.init(this);
        //log
        if (BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }else {
            Timber.plant(new CrashReportingTree());
        }
    }

    private static class CrashReportingTree extends Timber.Tree{

        @Override
        protected void log(int priority, String tag, @NonNull String message, Throwable t) {

        }
    }
}
