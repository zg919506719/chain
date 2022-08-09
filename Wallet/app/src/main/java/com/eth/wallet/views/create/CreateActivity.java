package com.eth.wallet.views.create;

import com.eth.base.BaseMVVMActivity;
import com.eth.wallet.R;
import com.eth.wallet.databinding.ActivityCreateBinding;

public class CreateActivity extends BaseMVVMActivity<CreateViewModel, ActivityCreateBinding> {
    @Override
    protected Class<CreateViewModel> getModelClass() {
        return CreateViewModel.class;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_create;
    }
}
