package com.eth.wallet.views.launch;

import com.eth.base.BaseMVVMActivity;
import com.eth.wallet.R;
import com.eth.wallet.databinding.ActivityLaunchBinding;

public class LaunchActivity extends BaseMVVMActivity<LaunchViewModel, ActivityLaunchBinding> {

    @Override
    protected Class<LaunchViewModel> getModelClass() {
        return LaunchViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_launch;
    }
}
