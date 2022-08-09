package com.eth.base;

import android.os.Bundle;

import com.eth.base.viewmodel.BaseApplication;
import com.eth.base.viewmodel.SharedViewModel;
import com.google.gson.Gson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author：atar
 * @date: 2020/9/14
 * @description:
 */
public abstract class BaseMVVMActivity<VM extends BaseViewModel, DB extends ViewDataBinding> extends AppCompatActivity {
    public VM viewModel;
    public SharedViewModel sharedViewModel;
    public DB binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        binding.setLifecycleOwner(this);
        initViewModel();
    }

    /**
     * 初始化ViewModel
     */

    private void initViewModel() {
        try {
            if (getModelClass() == null) {
                return;
            }
            viewModel = new ViewModelProvider(this).get(getModelClass());
            sharedViewModel = ((BaseApplication) getApplication()).getShareViewModel();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract Class<VM> getModelClass();


    public abstract int getLayoutId();

}
