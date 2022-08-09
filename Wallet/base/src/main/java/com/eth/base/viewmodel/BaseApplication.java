package com.eth.base.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

public class BaseApplication extends Application implements ViewModelStoreOwner {
    public static Application baseApplication = null;

    private ViewModelStore mAppViewModelStore;
    private ViewModelProvider.Factory mFactory;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication=this;
        mAppViewModelStore=new ViewModelStore();
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }

    public ViewModelProvider getAppViewModelProvider() {
        return new ViewModelProvider(this,
                getAppFactory());
    }

    public SharedViewModel getShareViewModel() {
        return getAppViewModelProvider().get(SharedViewModel.class);
    }

    private ViewModelProvider.Factory getAppFactory() {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this);
        }
        return mFactory;
    }
}
