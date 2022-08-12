package com.eth.wallet.views.launch;

import android.view.View;

import com.eth.base.BaseFragment;
import com.eth.base.data_response.DataResult;
import com.eth.base.utils.ToastUtils;
import com.eth.wallet.BR;
import com.eth.wallet.R;
import com.eth.wallet.database.user.User;
import com.eth.wallet.request.UserRequest;
import com.kunminx.architecture.ui.page.DataBindingConfig;

import androidx.lifecycle.Observer;

public class LaunchFragment extends BaseFragment {
    private LaunchFraViewModel mViewModel;
    private UserRequest userRequest;

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(LaunchFraViewModel.class);
        userRequest = new UserRequest(mActivity);
        userRequest.getUserResult().observe(this, new Observer<DataResult<User>>() {
            @Override
            public void onChanged(DataResult<User> userDataResult) {
                if (!userDataResult.getResponseStatus().isSuccess()){
                    ToastUtils.showShortToast(getContext(),userDataResult.getResponseStatus().getResponseCode());
                    return;
                }
                mViewModel.userName.set(userDataResult.getResult().toString());
            }
        });
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_launch, BR.vm, mViewModel).
                addBindingParam(BR.click, new ClickProxy());
    }

    public class ClickProxy {
        public void transfer(View view) {
            userRequest.createUser("测试", "123456");
        }
    }
}
