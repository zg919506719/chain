package com.eth.wallet.views.import_in;

import com.eth.base.BaseFragment;
import com.eth.wallet.BR;
import com.eth.wallet.R;
import com.kunminx.architecture.ui.page.DataBindingConfig;

import org.jetbrains.annotations.NotNull;

public class ImportFragment extends BaseFragment {
    private ImportViewModel mViewModel;

    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(ImportViewModel.class);
    }

    @NotNull
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_import, BR.vm, mViewModel);
    }

}
