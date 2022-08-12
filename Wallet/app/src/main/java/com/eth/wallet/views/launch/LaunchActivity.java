package com.eth.wallet.views.launch;

import android.os.Bundle;
import android.view.View;

import com.eth.base.BaseActivity;
import com.eth.wallet.BR;
import com.eth.wallet.R;
import com.kunminx.architecture.ui.page.DataBindingConfig;

import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;

public class LaunchActivity extends BaseActivity {

    private LaunchViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(LaunchViewModel.class);

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(
                R.layout.activity_launch, BR.vm, mViewModel
        ).addBindingParam(BR.click, new ClickProxy());
    }

    private void transferData() {

    }

    public class ClickProxy {
        public void transfer(View view) {
            transferData();
        }
    }
}
