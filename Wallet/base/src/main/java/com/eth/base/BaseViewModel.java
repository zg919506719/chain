package com.eth.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * @author：atar
 * @date: 2020/9/14
 * @description:
 */
public class BaseViewModel extends ViewModel {

    /**
     * 当ViewModel层出现错误需要通知到Activity／Fragment
     */
    public MutableLiveData<String> error = new MutableLiveData<>();

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
