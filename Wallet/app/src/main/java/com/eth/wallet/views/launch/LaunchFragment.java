package com.eth.wallet.views.launch;

import android.os.Bundle;
import android.view.View;

import com.eth.base.BaseFragment;
import com.eth.base.data_response.DataResult;
import com.eth.base.utils.ToastUtils;
import com.eth.wallet.BR;
import com.eth.wallet.R;
import com.eth.wallet.request.UserRequest;
import com.eth.wallet.request.bean.UserItem;
import com.kunminx.architecture.ui.page.DataBindingConfig;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

public class LaunchFragment extends BaseFragment {
    private LaunchFraViewModel mViewModel;
    private UserRequest userRequest;

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(LaunchFraViewModel.class);
        userRequest = new UserRequest(mActivity);
        userRequest.userCreateResult.observe(this, userDataResult -> {
            if (!userDataResult.getResponseStatus().isSuccess()){
                ToastUtils.showShortToast(getContext(),userDataResult.getResponseStatus().getResponseCode());
                return;
            }
            mViewModel.userName.set(userDataResult.getResult().toString());
        });

        userRequest.recycleData.observe(this, new Observer<DataResult<List<UserItem>>>() {
            @Override
            public void onChanged(DataResult<List<UserItem>> listDataResult) {
                if (!listDataResult.getResponseStatus().isSuccess()){
                    ToastUtils.showShortToast(getContext(),listDataResult.getResponseStatus().getResponseCode());
                    return;
                }
                mViewModel.linkData.set(listDataResult.getResult());
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userRequest.getAllUsers();

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
