package com.eth.wallet.views.launch;

import android.view.View;

import com.eth.base.BaseFragment;
import com.eth.wallet.BR;
import com.eth.wallet.R;
import com.kunminx.architecture.ui.page.DataBindingConfig;

public class LaunchFragment extends BaseFragment {
    private LaunchFraViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(LaunchFraViewModel.class);

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_launch, BR.vm, mViewModel).
        addBindingParam(BR.click, new ClickProxy());
    }

    public class ClickProxy {
        public void transfer(View view) {
            nav().navigate(R.id.action_launchFragment_to_importFragment);
//返回上一栈
//            nav().navigateUp();
        }
    }
}
